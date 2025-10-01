package com.example.dungeon.core;

import com.example.dungeon.model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final GameState state = new GameState();
    private final Map<String, Command> commands = new LinkedHashMap<>();

    static {
        WorldInfo.touch("Game");
    }

    public Game() {
        registerCommands();
        bootstrapWorld();
    }

    private void registerCommands() {
        commands.put("help", (ctx, a) -> System.out.println("Команды: " + String.join(", ", commands.keySet())));
        commands.put("gc-stats", (ctx, a) -> {
            Runtime rt = Runtime.getRuntime();
            long free = rt.freeMemory(), total = rt.totalMemory(), used = total - free;
            System.out.println("Память: used=" + used + " free=" + free + " total=" + total);
        });
        commands.put("look", (ctx, a) -> System.out.println(ctx.getCurrent().describe()));
        
        commands.put("move", (ctx, a) -> {
            Set<String> Exits = ctx.getCurrent().getNeighbors().keySet();
            if (a.isEmpty()){
                throw new InvalidCommandException("Введите направление движения (см. возможные выходы - " + Exits + ")");
            }
            else if(a.size()>1){
                throw new InvalidCommandException("Введите одно направление движения (см. возможные выходы - " + Exits + ")");
            }
            else if(!Exits.contains(a.get(0))){
                throw new InvalidCommandException("Введите доступное направление движения (см. возможные выходы - " + Exits + ")");
            }
            else{
                Room nextRoom = ctx.getCurrent().getNeighbors().get(a.get(0));
                System.out.print("Вы передвигаетесь в направлении " + a.get(0) + " и попадаете в локацию ");
                System.out.println(nextRoom.getName());
                ctx.setCurrent(nextRoom);
            }

        });
        
        commands.put("take_all", (ctx, a) -> {
            List<Item> Items = ctx.getCurrent().getItems();
            List<Item> toRemove = new ArrayList<>();
            for(Item item:Items){
                if (item!=null)
                    {   
                        ctx.getPlayer().getInventory().add(item);
                        System.out.println(item.getName() + "добавлен в инвентарь");
                        toRemove.add(item);
                    }
            }
            ctx.getCurrent().getItems().removeAll(toRemove);
        });

        commands.put("take", (ctx, a) -> {
            String aStr = a.stream().collect(Collectors.joining(" "));
            List<Item> Items = ctx.getCurrent().getItems();
            List<String> strItems = new ArrayList<>();
            for (Item item:Items){
                strItems.add(item.getName());
            }
            if (Items.isEmpty()){
                throw new InvalidCommandException("В данной локации нет предметов, Вы ничего не можете взять");
            }
            else if (a.isEmpty()){
                throw new InvalidCommandException("Введите предмет, который нужно взять ( " + strItems + ")");
            }
            else if(!strItems.contains(aStr)){
                System.out.println(strItems);
                System.out.println();
                throw new InvalidCommandException("Введите предмет, который находится в данной локации: " + strItems + ")");
            }
            else{
                int counter = 0;
                for (Item item:Items){
                    if(!item.getName().equals(aStr)){counter = counter+1;}
                    else{break;}
                }
                System.out.println(a.get(0) + " добавлен в инвентарь");
                ctx.getPlayer().getInventory().add(Items.get(counter));
                ctx.getCurrent().getItems().remove(Items.get(counter));

        }});
        
        commands.put("inventory", (ctx, a) -> {
            if (ctx.getPlayer().getInventory().isEmpty()){
                System.out.println("Инвентарь пуст");
            }
            else{
                System.out.println("Weapon:");
                System.out.println(
                    ctx.getPlayer().getInventory().stream()
                        .filter(x->x.getClass().getSimpleName().equals("Weapon"))
                        .map(x->x.getName())
                        .sorted()
                        .toList());
            
                System.out.println("Potion:");
                System.out.println(
                    ctx.getPlayer().getInventory().stream()
                        .filter(x->x.getClass().getSimpleName().equals("Potion"))
                        .map(x->x.getName())
                        .sorted()
                        .toList());
                System.out.println("Key:");
                System.out.println(
                    ctx.getPlayer().getInventory().stream()
                        .filter(x->x.getClass().getSimpleName().equals("Key"))
                        .map(x->x.getName())
                        .sorted()
                        .toList());
            }


        });

        commands.put("open_door", (ctx, a) -> {
            if (ctx.getCurrent().getDoor()==null)
                {
                    throw new InvalidCommandException("В этой локации нет двери");
                }
            else
                {   
                Door servDoor = ctx.getCurrent().getDoor();
                String aStr = a.stream().collect(Collectors.joining(" "));
                if (servDoor.isCorrectKey(aStr))
                    {   Item treasure  = new Potion("Живая вода",50);
                        ctx.getCurrent().getItems().add(treasure);
                        System.out.println("Вы нашли клад - " + treasure.getName());
                    }
            }
        });

        commands.put("use", (ctx, a) -> {
            String aStr = a.stream().collect(Collectors.joining(" "));
            
            List<Item> Items = ctx.getPlayer().getInventory();
            List<String> strItems = new ArrayList<>();
            for (Item item:Items){
                strItems.add(item.getName());
            }

            if (Items.isEmpty()){
                System.out.println("Инвентарь пуст");
            }
            else if (a.isEmpty()){
                throw new InvalidCommandException("Введите предмет, который нужно использовать (инвентарь: " + strItems + ")");
            }
            else if(!strItems.contains(aStr)){
                throw new InvalidCommandException("Введите предмет, который находится в инвентаре: " + strItems + ")");
            }
            else{
                System.out.println();
                System.out.println("Текущие характеристики:");
                ctx.getPlayer().getInfo();
                System.out.println();
                
                int counter = 0;
                for (Item item:Items){
                    if(!item.getName().equals(aStr)){counter = counter+1;}
                    else{break;}
                }
            Items.get(counter).apply(ctx);
            System.out.println();
            System.out.println("Обновленные характеристики:");
            ctx.getPlayer().getInfo();

            }
        });

        commands.put("fight", (ctx, a) -> {
            if (ctx.getCurrent().getMonster()==null)
                {
                    throw new InvalidCommandException("В этой локации нет монстра");
                }
            else if (ctx.getCurrent().getMonster().getHp()==0) {
                throw new InvalidCommandException("В этой локации монстр уже побежден");
            }
            else
            {   Random random = new Random();
                int powerPlayer = random.nextInt(3)-1 + ctx.getPlayer().getAttack();
                int powerMonstr = random.nextInt(3)-1 + ctx.getCurrent().getMonster().getLevel();

                System.out.println("Вы бьете " + ctx.getCurrent().getMonster().getName() + " на " + powerPlayer + ".");
                
                ctx.getCurrent().getMonster().setHp (Integer.max(ctx.getCurrent().getMonster().getHp() - powerPlayer,0));
                System.out.println("HP монстра " + ctx.getCurrent().getMonster().getHp()+".");
                
                if (ctx.getCurrent().getMonster().getHp()==0){
                    System.out.println("Монстр побежден, ура!");
                    Item caveKey  = new Key("Ключ от сундука в пещере");
                    Item oldKey  = new Key("Старый ржавый ключ");
                    ctx.getCurrent().getItems().add(caveKey);
                    System.out.println("Выпал следующий предмет:" + caveKey.getName());
                    ctx.getCurrent().getItems().add(oldKey);
                    System.out.println("Выпал следующий предмет:" + oldKey.getName());
                    caveKey.apply(ctx);

                }
                else{
                    System.out.println("Монстр отвечает на " + powerMonstr+".");
                    ctx.getPlayer().setHp( Integer.max(ctx.getPlayer().getHp() - powerMonstr,0));
                    System.out.println("Ваше здоровье - " + ctx.getPlayer().getHp());
                    if (ctx.getPlayer().getHp()==0){System.out.println("Вы побеждены");}
                }
            }

        });

        commands.put("save", (ctx, a) -> SaveLoad.save(ctx));
        commands.put("load", (ctx, a) -> SaveLoad.load(ctx));
        commands.put("scores", (ctx, a) -> SaveLoad.printScores());
        commands.put("exit", (ctx, a) -> {
            System.out.println("Пока!");
            System.exit(0);
        });
    }

    private void bootstrapWorld() {
        Player hero = new Player("Герой", 20, 5);
        state.setPlayer(hero);

        Room square = new Room("Площадь", "Каменная площадь с фонтаном.");
        Room lake = new Room("Озеро", "Озеро с прозрачной водой и мечом, торчащим из камня");
        Room forest = new Room("Лес", "Шелест листвы и птичий щебет.");
        Room cave = new Room("Пещера", "Темно и сыро.");
        square.getNeighbors().put("north", forest);
        square.getNeighbors().put("east", lake);
        forest.getNeighbors().put("south", square);
        forest.getNeighbors().put("east", cave);
        cave.getNeighbors().put("west", forest);
        cave.getNeighbors().put("south", lake);
        lake.getNeighbors().put("west", square);
        lake.getNeighbors().put("north", cave);


        forest.getItems().add(new Potion("Малое зелье", 5));
        lake.getItems().add(new Weapon("Меч", 3));
        lake.getItems().add(new Weapon("Весло", 1));
        lake.getItems().add(new Weapon("Ромашка", 0));
        lake.getItems().add(new Potion("Амброзия", 10));
        lake.getItems().add(new Potion("Яд", -5));
        forest.setMonster(new Monster("Волк", 7, 12));
        Door caveDoor = new Door ("Загадочный сундук в пещере");
        cave.getItems().add(caveDoor);
        caveDoor.setCorrectKey("Ключ от сундука в пещере");
        cave.setDoor(caveDoor);

        state.setCurrent(square);
    }

    public void run() {
        System.out.println("DungeonMini (TEMPLATE). 'help' — команды.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "cp866"))) {
            while (true) {
                System.out.print("> ");
                String line = in.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;
                List<String> parts = Arrays.asList(line.split("\s+"));
                String cmd = parts.get(0).toLowerCase(Locale.ROOT);
                List<String> args = parts.subList(1, parts.size());
                Command c = commands.get(cmd);
                try {
                    if (c == null) throw new InvalidCommandException("Неизвестная команда: " + cmd);
                    c.execute(state, args);
                    state.addScore(1);
                } catch (InvalidCommandException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Непредвиденная ошибка: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }
}
