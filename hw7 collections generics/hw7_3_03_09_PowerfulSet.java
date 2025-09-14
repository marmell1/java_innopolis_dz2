package hw7_03_09_abstract_collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class hw7_3_03_09_PowerfulSet {
    public <T> Set<T> set_from_list (ArrayList<T> arr){
        Set<T> set = new LinkedHashSet<T>();
        for (T serv_elem: arr)
        {set.add(serv_elem);}
        return set;
    };

    public <T> Set<T> intersection(Set<T> set1, Set<T> set2){
        Set<T> int_set = new LinkedHashSet<>();

        Iterator<T> iterator1 = set1.iterator();
        Iterator<T> iterator2 = set2.iterator();
        while (iterator1.hasNext()) {
            T i1 = iterator1.next();
            while (iterator2.hasNext()) {
                T i2 = iterator2.next();
                if (i1.equals(i2)) {
                    int_set.add(i1);
                    break;
                };
            }
        }
        return int_set;
    }

    public <T> Set<T> union(Set<T> set1, Set<T> set2){
        Set<T> union_set = new LinkedHashSet<>();

        Iterator<T> iterator1 = set1.iterator();
        Iterator<T> iterator2 = set2.iterator();
        while (iterator1.hasNext()) {
            T i1 = iterator1.next();
            union_set.add(i1);
        }

        while (iterator2.hasNext()) {
            T i2 = iterator2.next();
            union_set.add(i2); 
        }
        return union_set;
    };

    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2){
        Set<T> rel_set = new LinkedHashSet<>();

        Iterator<T> iterator1 = set1.iterator();
        Iterator<T> iterator2 = set2.iterator();
        while (iterator1.hasNext()) {
            int i = 1;
            T i1 = iterator1.next();
            while (iterator2.hasNext()) {
                T i2 = iterator2.next();
                if (i1.equals(i2)) {
                    i=0;
                    break;
                };
            }
            if (i==1){rel_set.add(i1);}
        }
        return rel_set;
    }
    
}