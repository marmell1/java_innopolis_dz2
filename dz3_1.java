import java.util.Scanner;
public class dz3_1 {

    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in, "cp866");
        System.out.println("Введите Ваше имя");
        String name1 = scanner1.nextLine();

        System.out.println("Привет, "+ name1);
        scanner1.close();
    }

}
