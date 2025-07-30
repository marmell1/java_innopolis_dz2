import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TV oTelevizor1 = new TV ();
   
        Scanner scanner1 = new Scanner(System.in, "cp866");
        oTelevizor1.TV("empty");
        String name1 = scanner1.nextLine();
        String[] words = name1.split(" ");

        System.out.println();
        if (words.length ==1){
            oTelevizor1.TV(name1);
        } else if (words.length==5){
            oTelevizor1.TV (words[0],Integer.valueOf(words[1]),words[2],words[3],Integer.valueOf(words[4]));
            oTelevizor1.bCheck=false;
        } else {
            System.out.println("Введите корректное количество параметров (5)");
            oTelevizor1.bCheck=false;
        }
        
        
            System.out.println();

        if (oTelevizor1.bCheck != false){
            System.out.println("У телевизора есть функция smart?");
            String name2 = scanner1.nextLine();
        
            oTelevizor1.isSmart(name2);
        }
        scanner1.close();

    }
}
