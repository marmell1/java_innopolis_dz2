package hw7_03_09_abstract_collections;
import java.util.ArrayList;

public class hw7_1_03_09<T> {
    protected static ArrayList <String> str_arr = new ArrayList<>(){{
        add("aa");add("bb");add("cc");
        add("aa");add("bb");add("cc");
        add("dd");
    }};
    protected static ArrayList <Integer> int_arr = new ArrayList<>(){{
        add(1);add(2);add(3);add(4);
        add(1);add(2);add(3);add(4);
        add(5);add(5);add(6);add(6);
    }};
    
    public static void main(String[] args) {
        
        hw7_1_get_unique_elements arr = new hw7_1_get_unique_elements ();
        arr.hw7_1_get_unique_elements(str_arr);
        arr.hw7_1_get_unique_elements(int_arr);
        }
    
}


// Задание 1:
// Реализовать метод, который на вход принимает ArrayList<T>, а возвращает набор
// уникальных элементов этого массива. Решить, используя коллекции