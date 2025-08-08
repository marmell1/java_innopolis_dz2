package Attestation01;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("Внесите список покупателей с соответствующим бюдежтом, формат приведен ниже");
        System.out.println("Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10");
        System.out.println();
        Scanner scanner1 = new Scanner(System.in, "cp866");
        String sCustomers = scanner1.nextLine();
    
        Person oCustomers = new Person();

        String[] listCustomers = sCustomers.split("; ");
        for(int i=0;i<listCustomers.length;i++){
            String[] Cust_name_money = listCustomers[i].split(" = ");
            oCustomers.Person(Cust_name_money[0], Integer.valueOf(Cust_name_money[1]));
            }
        System.out.println();
        System.out.println("Введите список продуктов в формате, аналогичном списку покупателей");
        System.out.println();
        Scanner scanner3 = new Scanner(System.in, "cp866");
        String sProducts = scanner3.nextLine();
        System.out.println();

        String[] listProducts = sProducts.split("; ");
        Product oProducts_list = new Product();
        for(int i=0;i<listProducts.length;i++){
            String[] Prod_name_price = listProducts[i].split(" = ");
            oProducts_list.Product(Prod_name_price[0], Integer.valueOf(Prod_name_price[1]));
            }

        //oProducts_list.Print_product_list();
        //System.out.println();

        System.out.println("Внесите покупку в формате ниже, по окончании введите END");
        System.out.println("Иван - Молоко");
        System.out.println();
        
        Scanner scanner2 = new Scanner(System.in, "cp866");
        
        for (int i = 0;i<=10;i++){
            String sPurchase = scanner2.nextLine();
            if (sPurchase.equals("END")){
                i=11;
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

    // HashMap<String, Integer> dictCustomers = new HashMap <String,Integer>();
    // public static HashMap<String, Integer> Split_customer_list (String s){


    //     return dictCustomers;
    // }
}



