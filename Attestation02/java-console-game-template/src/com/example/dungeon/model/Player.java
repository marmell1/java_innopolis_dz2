package com.example.dungeon.model;

import java.util.*;

public class Player extends Entity {
    private int attack;
    private final List<Item> inventory = new ArrayList<>();

    public Player(String name, int hp, int attack) {
        super(name, hp);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void getInfo()
    {
        System.out.println("Имя игрока: " + this.getName());
        System.out.println("hp игрокa: " + this.getHp());
        System.out.println("Урон игрока: " + this.getAttack());
        System.out.println("Инвентарь игрока: ");
        if (this.getInventory().isEmpty()){
                System.out.println("Инвентарь пуст");
        }
        else{
            for(Item inv:this.getInventory()){System.out.println(inv.getName());}
        }
    }

}
