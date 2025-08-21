package homework7;

import java.util.ArrayList;

public class Product {
    protected String name;
    protected int price;
    protected static ArrayList<String[] > Product_list = new ArrayList<>();
    //добавление продуктов в список
    public void Product (String pName, int pPrice) {
        this.name = pName;
        this.price= pPrice;
        Checking(pName, pPrice);
        // System.out.println(pName);
        AddToProduct_list(pName,pPrice);
    }

    public void Checking(String pName, int pPrice){
        //проверка на ошибки ввода, выведение в отдельный метод для доступа из дочернего класса
        if (this.name.length()==0){System.out.println(pName +" - Название продукта не может быть пустым");System.exit(0);}
        if (this.name.length()<3){System.out.println(pName + " - Название продукта не может быть короче трех букв");System.exit(0);}
        if (this.price<=0){System.out.println(pName+" - Стоимость продукта не может быть отрицательной или нулевым");System.exit(0);}
        boolean only_digits = this.name.matches("[a-zA-Zа-яА-я -]+"); 
        //Задача: Название продукта не должно содержать только цифры
        //В рамках логики задания считаем, что только знаки ппрепинания и спец. символы так же не могут составлять название продукта.
        //В ином слуаче их так же можно добавить в рег. выражение.
        if (only_digits==false){System.out.println(pName+" - Название продукта не должно содержать только цифры");System.exit(0);}
    }

    public void AddToProduct_list(String pName, int pPrice){
        //добавление в список, единый с дочерней 
        final String [] serv_list = new String[2];
        serv_list[0] = pName; 
        serv_list[1] = String.valueOf(pPrice);
        Product_list.add(serv_list);
    }

    //вывести список продуктов, при необходимости
    public void Print_product_list(){
        for (int i=0;i<Product_list.size();i++) {
            System.out.println(Product_list.get(i)[0]+" - "+ Product_list.get(i)[1]);
        }
        System.out.println();
    }

    //получение цены по названию
    public int getPrice (String s){
        for (int i=0; i<Product_list.size();i++){
            if (Product_list.get(i)[0].equals(s)){
                return Integer.valueOf(Product_list.get(i)[1]);
            }
       }
        return 0;
    }
    //Переопределение методов
    public void setPrice(int i){
        this.price = i;
    }
    @Override  
    public String toString() {  
        return "Product{" + "name='" + name + '\''+ ", price=" + price + '}';  
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