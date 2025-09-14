package hw7_03_09_abstract_collections;

import java.util.ArrayList;
import java.util.Scanner;

public class hw7_2_03_09 {
    public static void main(String[] args) {
        System.out.println("Введите два слова для сравнения");
        Scanner scanner1 = new Scanner(System.in, "cp866");
        String name1 = scanner1.nextLine().toLowerCase();
        Scanner scanner2 = new Scanner(System.in, "cp866");
        String name2 = scanner1.nextLine().toLowerCase();
        char c;
        
        ArrayList <Character> arr1 = new ArrayList<>();
        for (int i = 0; i < name1.length(); i++)
        {
            c = name1.charAt(i);
            arr1.add(c);
        };
        arr1.sort(null);

        ArrayList <Character> arr2 = new ArrayList<>();
        for (int i = 0; i < name2.length(); i++)
        {
            c = name2.charAt(i);
            arr2.add(c);
        };
        arr2.sort(null);

        boolean check = arr1.equals(arr2);

        System.out.println("Результат проверки:");
        System.out.println(check);

        scanner1.close();
        scanner2.close();
    }
    
}

// Задание 2:
// С консоли на вход подается две строки s и t. Необходимо вывести true, если одна
// строка является валидной анаграммой другой строки, и false – если это не так.
// Анаграмма – это слово, или фраза, образованная путем перестановки букв другого
// слова или фразы, обычно с использованием всех исходных букв ровно один раз.
// Для проверки:
// ● Бейсбол – бобслей
// ● Героин – регион
// ● Клоака – околка