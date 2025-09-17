package homework011_StreamAPI;

public class Car {
    private String number;
    private String model;
    private String colour;
    private int mileage;
    private int price;

    public void Car(String s){
        String[] servList = s.split("\\|");
        this.number = servList[0];
        this.model = servList[1];
        this.colour = servList[2];
        this.mileage = Integer.valueOf(servList[3]);
        this.price = Integer.valueOf(servList[4]);

    }

    public String get_number(){ return this.number; }
    public String get_model(){ return this.model; }
    public String get_colour(){ return this.colour; }
    public Integer get_mileage(){ return this.mileage; }
    public Integer get_price(){ return this.price; }

    public void set_number(String number){ this.number=number; }
    public void set_model(String model){ this.model=model; }
    public void set_colour(String colour){ this.colour=colour; }
    public void set_mileage(Integer mileage){ this.mileage=mileage; }
    public void set_price(Integer price){ this.price=price; }


    
    @Override  
    public String toString() {  
        return "Car{" + "number = '" + number + ", model = " + model + ", colour = '" + colour + ", mileage = " + mileage +", price = " + price +'}';  
    } 




    
}