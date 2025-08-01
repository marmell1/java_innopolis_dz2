import java.util.Scanner;

public class dz5_1 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in, "cp866");
        System.out.println("Введите искомую букву");
        String sLetter = scanner1.nextLine();

        String sABC = "qwertyuiopasdfghjklzxcvbnm";
        int nIndex = sABC.indexOf(sLetter);
        int nLeftIndex = (nIndex-1+sABC.length())%sABC.length();
        char sLeftLetter = sABC.charAt(nLeftIndex);
        System.out.println(sLeftLetter);
        scanner1.close();
    }
}
/*
Задача 1. 
Для введенной с клавиатуры буквы английского алфавита нужно вывести слева стоящую букву 
на стандартной клавиатуре. При этом клавиатура замкнута, т.е. справа от буквы «p» стоит 
буква «a», а слева от "а" буква "р", также соседними считаются буквы «l» и буква «z», 
а буква «m» с буквой «q».
Входные данные: строка входного потока содержит один символ — маленькую букву английского алфавита.
Выходные данные: следует вывести букву стоящую слева от заданной буквы, с учетом замкнутости клавиатуры.
 */