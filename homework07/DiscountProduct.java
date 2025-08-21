package homework7;
import java.util.Date;

public class DiscountProduct extends Product{
    private int discount;
    private Date start_discount; 
    private Date finish_discount;
    
    public void DiscountProduct(String pName, String pPrice) {
        this.name = pName;
        String[] discount_list = pPrice.split(" ");
        this.price = Integer.valueOf(discount_list[0]);
        this.discount = Integer.valueOf(discount_list[2]);
        if(this.price<=this.discount){System.out.println("Скидка должна быть меньше цены");System.exit(0);}
        Date current = new Date();
        //формат - с 20.08.2025 по 25.08.2025
        String[] serv_list = String.valueOf(discount_list[4]).split("\\.");
        start_discount = new Date(Integer.valueOf(serv_list[2])-1900, Integer.valueOf(serv_list[1])-1, Integer.valueOf(serv_list[0]));

        serv_list = discount_list[6].split("\\.");
        finish_discount = new Date(Integer.valueOf(serv_list[2])-1900, Integer.valueOf(serv_list[1])-1, Integer.valueOf(serv_list[0]));

        if (current.getTime()>=start_discount.getTime() & current.getTime()<=finish_discount.getTime()){
            this.price = this.price - discount;
        }
        Checking(pName, this.price);
        AddToProduct_list(pName, this.price);
    }
}