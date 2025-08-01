import java.util.Scanner;
import java.util.Arrays;

public class dz5_3 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in, "cp866");
        System.out.println();
        System.out.println("Введите строку на латиннице, разделенную одним пробелом");
        String sSymbols = scanner1.nextLine().toLowerCase();
        String[] aWords = sSymbols.split(" ");
        if (aWords.length!=2){System.out.println("Некорректная строка, должен быть один пробел, разделяющий символы"); }
        else {
            for (int i=0;i<=1;i++){
                int[] numbers = new int[aWords[i].length()];

                for (int j = 0; j< aWords[i].length();j++){
                    numbers[j]=(int) aWords[i].charAt(j);
                }
                Arrays.sort(numbers);
                for (int j = 0; j< aWords[i].length();j++){
                    System.out.print((char) numbers[j]);
                }
                System.out.print(" ");
            }
            scanner1.close();
        }
    }
}

/* Задача 3*. Задана строка, состоящая из букв английского алфавита, разделенных одним пробелом. 
Необходимо каждую последовательность символов упорядочить по возрастанию и вывести слова в нижнем регистре.
Входные данные: в единственной строке последовательность символов представляющее два слова.
Выходные данные: упорядоченные по возрастанию буквы в нижнем регистре.
 */