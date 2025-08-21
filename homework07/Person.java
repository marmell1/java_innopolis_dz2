package homework7;
import java.util.HashMap;
import java.util.Set;

public class Person {
    private String name;
    private int money;
    HashMap<String, Integer> customers_money = new HashMap <String,Integer>();
    HashMap<String, String[]> customers_bag = new HashMap <String,String[]>();

    //чтение данных, добавление в словарь
    public void Person(String pName, int pMoney) {
        this.name = pName;
        this.money= pMoney;
        if (this.name.length()==0){System.out.println("Имя не может быть пустым");System.exit(0);}
        else if (this.name.length()<=3){System.out.println("Имя не может быть короче трех символов");System.exit(0);};
        if (this.money<0){System.out.println("Деньги не могут быть отрицительными");System.exit(0);}
        
        //в комментариях в ТГ преподаватель писал, что можно сделать массив на 10 единиц, этого хватит.
        //вместо того, чтобы пересоздавать каждый раз при добавлении нового элемента, т.к. массивы не динамичексие
        String[] serv_array = new String[10];
        customers_money.put(this.name, this.money);
        customers_bag.put(this.name, serv_array);
    }

    //обработка покупки
    public void Buying (String custName,String prodName,int price){
        // тут запрашиваем цену из списка продуктов
        if (customers_money.get(custName)>=price){
            //добавляем в пакет покупку
            for (int i = 0;i<customers_bag.get(custName).length;i++){
                if (customers_bag.get(custName)[i] == null){
                    customers_bag.get(custName)[i] = prodName;
                    i=11;
                }
            }
            
            //уменьшаем бюджет на стоимость покупки
            int new_budget= customers_money.get(custName)-price;
            customers_money.put (custName,new_budget);

            System.out.println(custName + " купил продукт " + prodName +". Оставшийся бюджет - " +  String.valueOf(customers_money.get(custName)));
            
        }else{
            System.out.println(custName + " не может себе позволить " + prodName);
        }
    }

    //выводим список с результатами
    public void Print_results(){
        
        Set<String> keys = customers_money.keySet();  
        for (String custName : keys) {  
            boolean bCheck = false;
            if (customers_bag.get(custName)[0] !=null){
                System.out.print(custName + " - ");
            }
            for (int i = 0;i<customers_bag.get(custName).length;i++){
                
                if (customers_bag.get(custName)[i] !=null){
                    if (i!=0){System.out.print(", ");}
                    System.out.print(customers_bag.get(custName)[i]);
                    bCheck = true;
                }
             }
            if (bCheck==false){System.out.println(custName + " - Ничего не куплено");}
            System.out.println();
        }
    }


    //Переопределение методов
    @Override  
    public String toString() {  
        return "Person{" + "name='" + name + '\''+ ", money=" + money + '}';  
    }  
    
    @Override
    public boolean equals (Object o) {
        if (o == this) {return true;}
        if (o == null || o.getClass() != this.getClass()) {return false;}

    }
    @Override
    public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());  
    return result;
    }    
}


