package homework7;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //Обозначение задачи и формата входящих данных
        System.out.println();
        System.out.println("Внесите список покупателей с соответствующим бюдежтом, формат приведен ниже");
        System.out.println("Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10");
        System.out.println();
        Scanner scanner1 = new Scanner(System.in, "cp866");
        String sCustomers = scanner1.nextLine();
    
        //Чтение списка покупателей и продуктов
        Person oCustomers = new Person();
        String[] listCustomers = sCustomers.split("; ");
        for(int i=0;i<listCustomers.length;i++){
            String[] Cust_name_money = listCustomers[i].split(" = ");
            oCustomers.Person(Cust_name_money[0], Integer.valueOf(Cust_name_money[1]));
        }
        System.out.println();
        System.out.println("Введите список продуктов в формате, аналогичном списку покупателей, скидку указать следующим образом:");
        System.out.println("Мука = 100 скидка 20 с 20.08.2025 по 25.08.2025");
        System.out.println();
        Scanner scanner3 = new Scanner(System.in, "cp866");
        String sProducts = scanner3.nextLine();
        System.out.println();

        String[] listProducts = sProducts.split("; ");
        Product oProducts_list = new Product();
        DiscountProduct oDiscountProducts_list = new DiscountProduct();
        for(int i=0;i<listProducts.length;i++){
            String[] Prod_name_price = listProducts[i].split(" = ");
            if (Prod_name_price[1].indexOf("скидка")>0){
                oDiscountProducts_list.DiscountProduct(Prod_name_price[0], Prod_name_price[1]);
            } else{
                oProducts_list.Product(Prod_name_price[0], Integer.valueOf(Prod_name_price[1]));
            }
        }

        oProducts_list.Print_product_list();

        //Чтение покупок,обработка внутри классов Product и Person
        System.out.println("Внесите покупку в формате ниже, по окончании введите END");
        System.out.println("Иван - Молоко");
        System.out.println();
        
        Scanner scanner2 = new Scanner(System.in, "cp866");
        
        for (int i = 0;i<=100;i++){
            String sPurchase = scanner2.nextLine();
            if (sPurchase.equals("END")){
                i=101;
            } else{
                String serv_customer = sPurchase.split(" - ")[0];
                String serv_product = sPurchase.split(" - ")[1];
                int serv_price = oProducts_list.getPrice(serv_product);
                oCustomers.Buying(serv_customer,serv_product,serv_price);
            }
        }
        
        oCustomers.Print_results();
        
        scanner1.close();
        scanner2.close();
        scanner3.close();
    }
}



