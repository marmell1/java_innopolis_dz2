import java.util.HashMap;
import java.util.Random;

public class dz3_2 {
    public static void main(String[] args) {
        for (int i = 1;i<=10;i++){
            System.out.println();

            Random r = new Random();
            int Petya = r.nextInt(3);
            int Vasya = r.nextInt(3);

            HashMap<Integer, String> kmn = new HashMap <Integer,String>();
            kmn.put(0, "камень");
            kmn.put (1,"ножницы");
            kmn.put (2,"бумага");
            
            System.out.println("Петин выбор: " + kmn.get(Petya));
            System.out.println("Васин выбор: " + kmn.get(Vasya));

            int delta  = Petya - Vasya;
            //System.out.println(delta);
            if (delta==-1||delta==2) {
                System.out.println("Петя победил");
            } else if (delta == 1 ||delta==-2) {
                System.out.println("Вася победил");
            } else if (delta == 0 ){
                System.out.println("Ничья");
            }

        }

    }
    }



    //камень-0, ножницы-1, бумага-2.

 /*Вася и Петя играют в игру “Камень, ножницы, бумага”.
Каждый из них показывает свою фигуру камень-0, ножницы-1, бумага-2.
Программа определяет, кто из них выиграл.
Выбор каждого участника формируется случайным образом.

*/