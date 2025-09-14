package hw7_03_09_abstract_collections;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class hw7_1_get_unique_elements <T>{

    public <T> void hw7_1_get_unique_elements (ArrayList<T> arr) {
        System.out.print("Исходный массив ");
        System.out.print(arr);
        System.out.println(" содержит следующие уникальные элементы: ");
        
        Set<T> servSet = new LinkedHashSet<T>();
        for (T serv_elem: arr)
        { servSet.add(serv_elem); }

        System.out.println(servSet);
    }
    
}