package hw7_03_09_abstract_collections;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class hw7_3_03_09<T> {
    protected static ArrayList <Integer> arr1 = new ArrayList<>(){{ add(1);add(2);add(3); }};
    protected static ArrayList <Integer> arr2 = new ArrayList<>(){{ add(0);add(1);add(4);add(2); }};
    protected static ArrayList <String> arr3 = new ArrayList<>(){{ add("aa");add("bb");add("cc"); }};
    protected static ArrayList <String> arr4 = new ArrayList<>(){{ add("dd");add("ee");add("aa");add("bb");}};

    protected static Set<Integer> set1i = new LinkedHashSet<>();
    protected static Set<Integer> set2i = new LinkedHashSet<>();
    protected static Set<String> set3 = new LinkedHashSet<>();
    protected static Set<String> set4 = new LinkedHashSet<>();
    
    public static void main(String[] args) {
        hw7_3_03_09_PowerfulSet serv = new hw7_3_03_09_PowerfulSet();
        set1i = serv.set_from_list(arr1);
        set2i = serv.set_from_list(arr2);
        set3 = serv.set_from_list(arr3);
        set4 = serv.set_from_list(arr4);
       
        Set<Integer> set_intersections_Integers = serv.intersection(set1i,set2i);
        Set<String> set_intersections_strings = serv.intersection(set3,set4);

        Set<Integer> set_union_Integers = serv.union(set1i,set2i);
        Set<String> set_union_strings = serv.union(set3,set4);

        Set<Integer> set_rel_Integers = serv.relativeComplement(set1i,set2i);
        Set<String> set_rel_strings = serv.relativeComplement(set3,set4);

        System.out.println("Множества: " + set1i + set2i);
        System.out.println("Пересечение: " + set_intersections_Integers);
        System.out.println("Объединение: " + set_union_Integers);
        System.out.println("Уникальные элементы первого набора: " + set_rel_Integers);

        System.out.println("Множества: " + set3 + set4);
        System.out.println("Пересечение: " + set_intersections_strings);
        System.out.println("Объединение: " + set_union_strings);
        System.out.println("Уникальные элементы первого набора: " + set_rel_strings);
    };
    };
// Задание 3:
// Реализовать класс PowerfulSet, в котором должны быть следующие методы:
// ● public <T> Set<T> intersection(Set<T> set1, Set<T> set2) – возвращает
// пересечение двух наборов.
// Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {1, 2}
// ● public <T> Set<T> union(Set<T> set1, Set<T> set2) – возвращает
// объединение двух наборов
// Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {0, 1, 2, 3, 4}
// ● public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) –
// возвращает элементы первого набора без тех, которые находятся также и
// во втором наборе.
// Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {3}