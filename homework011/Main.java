package homework011_StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.smartcardio.CardTerminals;

;

public class Main {
    @SuppressWarnings("unlikely-arg-type")
    public static void main(String[] args) {
        int iCounter = 0;
        int iKey = 0;
        ArrayList<Integer> servArr = new ArrayList<>();
        ArrayList<String> totalArr = new ArrayList<>();  
        Scanner scanner1 = new Scanner(System.in, "cp866");
        System.out.println("Введите список автомобилей");

        HashMap<Integer, String> dicNumber = new HashMap <Integer,String>();
        HashMap<Integer, String> dicModel = new HashMap <Integer,String>();
        HashMap<Integer, String> dicColour = new HashMap <Integer,String>();
        HashMap<Integer, Integer> dicMileage = new HashMap <Integer,Integer>();
        HashMap<Integer, Integer> dicPrice = new HashMap <Integer,Integer>();


        while (iCounter==0){
            String iCar = scanner1.nextLine();
            if (iCar==""){iCounter =1;break;};
            
            Car servCar = new Car();
            servCar.Car(iCar);
            dicNumber.put(iKey, servCar.get_number());
            dicModel.put(iKey, servCar.get_model());
            dicColour.put(iKey, servCar.get_colour());
            dicMileage.put(iKey, servCar.get_mileage());
            dicPrice.put(iKey, servCar.get_price());
            totalArr.add(iCar);
            servArr.add(iKey);
            iKey++;
            
        }

        // System.out.println("dicNumber" + dicNumber);
        // System.out.println("dicModel" + dicModel);
        // System.out.println("dicColour" + dicColour);
        // System.out.println("dicMileage" + dicMileage);
        // System.out.println("dicPrice" + dicPrice);

        // 1) Номера всех автомобилей, имеющих заданный в переменной цвет
        // colorToFind или нулевой пробег mileageToFind.

        String colorToFind ="black";
        System.out.println("Автомобили цвета " + colorToFind +":");
        System.out.println();
        List<String> result1 = servArr.stream()
            .filter(x -> dicColour.get(x).toLowerCase().equals(colorToFind.toLowerCase()))
            .map(x -> get_toString(totalArr.get(x)))
            .peek(x->System.out.println(x))
        .toList();
        System.out.println();

        int mileageToFind =0;
        System.out.println("Автомобили с пробегом " + mileageToFind +":");
        System.out.println();
        List<String> result2 = servArr.stream()
            .filter(x -> dicMileage.get(x)== mileageToFind)
            .map(x -> get_toString(totalArr.get(x)))
            .peek(x->System.out.println(x))
        .toList();
        System.out.println();

        // 2) Количество уникальных моделей в ценовом диапазоне от n до m тыс.
        int startPrice =700000;
        int endPrice =800000;
        System.out.println("Уникальные модели в диапазоне цен от " + startPrice +" до "+ endPrice  + " включительно");
        System.out.println();
        long result3 = servArr.stream()
            .filter(x -> (dicPrice.get(x)>= startPrice) && (dicPrice.get(x)<= endPrice))
            .map(x->dicModel.get(x))
            .distinct()
            .peek(x->System.out.println(x))
        .count();
        System.out.println("Итого: "+result3);
        
        // 3) Вывести цвет автомобиля с минимальной стоимостью.
        System.out.println();
        System.out.print("Цвет автомобиля с минимальной стоимостью: ");
        int firstCheck = dicPrice.get(0);
        Optional<Integer> result4 = servArr.stream()
            .map(x->Integer.valueOf(dicPrice.get(x)))
        .min(Comparator.comparing(Integer::valueOf));
        
        servArr.stream()
            .filter(x -> (dicPrice.get(x)<=result4.get()))
            .map(x->dicColour.get(x))
            .peek(x->System.out.println(x))
        .min(Comparator.comparing(Integer::valueOf));

        servArr.stream()
            .filter(x -> (dicPrice.get(x)<=result4.get()))
            .map(x -> get_toString(totalArr.get(x)))
            .peek(x->System.out.println(x))
        .min(Comparator.comparing(Integer::valueOf));

        System.out.println();

        // 4) Среднюю стоимость искомой модели modelToFind
        String modelToFind ="Volvo";
        System.out.print("Автомобили модели " + modelToFind +" в среднем стоят ");

        IntSummaryStatistics result5 = servArr.stream()
            .filter(x -> dicModel.get(x).toLowerCase().equals(modelToFind.toLowerCase()))
            .map(x->dicPrice.get(x))
            .mapToInt(Integer::intValue)
        .summaryStatistics();
        System.out.println(result5.getAverage()+", включая:");

        List<String> result6 = servArr.stream()
            .filter(x -> dicModel.get(x).toLowerCase().equals(modelToFind.toLowerCase()))
            .map(x -> get_toString(totalArr.get(x)))
            .peek(x->System.out.println(x))
        .toList();


        //     .map(x -> get_toString(totalArr.get(x)))
        //     .peek(x->System.out.println(x))
        // .toList();
        // System.out.println();


//         IntSummaryStatistics stats = Arrays.asList(1,2,3,4)
//     .stream()
//     .mapToInt(Integer::intValue)
//     .summaryStatistics();
// stats.getSum();
// stats.getCount();
// stats.getAverage();
        






    scanner1.close();
    }

    public static String get_toString (String s){
        Car serv = new Car();
        serv.Car(s);
        String ss = serv.toString();
        return ss;
    }
}



// Домашнее задание по теме «Java Collections. Stream API»
// Формулировка задания:
// 1. Реализовать класс Автомобиль. У класса есть поля, свойства и
// методы.
// Поля класса:
// а) Номер автомобиля;
// б) Модель;
// в) Цвет;
// г) Пробег;
// д) Стоимость.
// Обратить внимание на переопределение метода toString, на сеттеры и
// геттеры, модификаторы доступа полей.
// 2. Проверить работу в классе Main, методе main.
// 3. Создать объект Java Collections со списком автомобилей.
// 4. Используя Java Stream API, вывести (можно сделать любые 2 пункта
// из 4):
// 1) Номера всех автомобилей, имеющих заданный в переменной цвет
// colorToFind или нулевой пробег mileageToFind.
// 2) Количество уникальных моделей в ценовом диапазоне от n до m тыс.
// 3) Вывести цвет автомобиля с минимальной стоимостью.
// 4) Среднюю стоимость искомой модели modelToFind