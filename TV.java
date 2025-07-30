import java.util.HashMap;
import java.lang.String;



public class TV {
    private static final String sType = "Телевизор";
    private boolean bSmart = false;
    private int nPrice;
    private int nDiagolal;
    private String sModel;
    private String sCompany;
    private String sCountry;
    public boolean bCheck = true;
    

    public void TV (String s_Model){
        String [][] arrModels = {
            {"sm25","25", "Samsung", "South Korea"},{"sm40","40", "Samsung", "South Korea"},
            {"lg20","20", "LG", "South Korea"},{"lg100","100", "LG", "South Korea"},
            {"xi30","30", "Xiaomi", "China"},{"xi50","50", "Xiaomi", "China"}
        };

        final HashMap<String, String[]> MODELCOMPANY = new HashMap <String,String[]>();
        MODELCOMPANY.put(arrModels[0][0], arrModels[0]);
        MODELCOMPANY.put(arrModels[1][0], arrModels[1]);
        MODELCOMPANY.put(arrModels[2][0], arrModels[2]);
        MODELCOMPANY.put(arrModels[3][0], arrModels[3]);
        MODELCOMPANY.put(arrModels[4][0], arrModels[4]);
        MODELCOMPANY.put(arrModels[5][0], arrModels[5]);
        if (s_Model=="empty"){
            System.out.println();
            System.out.println("Ниже перечислены модели.");
            System.out.println("Выберете одну и свойства подтянутся из базы, либо запоните свойства нового объекта вручную:");
            System.out.println("модель, диагональ, компания-производитель, страна-производитель, цена");
            
            for (int i = 0;i<=5;i++){System.out.println(arrModels[i][0]);}
            System.out.println();
        }

        if (s_Model!="empty"){ 
            if (MODELCOMPANY.containsKey(s_Model)){
                this.sModel = s_Model;
                this.nDiagolal = Integer.valueOf(MODELCOMPANY.get(this.sModel)[1]);
                this.nPrice = this.nDiagolal*1000;
                this.sCompany = MODELCOMPANY.get(this.sModel)[2];
                this.sCountry = MODELCOMPANY.get(this.sModel)[3];
                
                System.out.println("Модель телевизора: " + this.sModel);
                System.out.println("Диагональ телевизора: " + this.nDiagolal);
                System.out.println("Цена телевизора: " + this.nPrice);
                System.out.println("Компания-производитель телевизора: " + this.sCompany);
                System.out.println("Страна-производитель телевизора: " + this.sCountry);
            }   
            
                else {System.out.println("Такой модели в базе нет, внесите параметры вручную");
                    this.bCheck = false;
                }
        }
        
    }

    public void TV(String Mod, int Dia, String Firm, String Country,int Pr){
                this.sModel = Mod;
                this.nDiagolal = Dia;
                this.nPrice = Pr;
                this.sCompany = Firm;
                this.sCountry = Country;
                
                System.out.println("Модель телевизора: " + this.sModel);
                System.out.println("Диагональ телевизора: " + this.nDiagolal);
                System.out.println("Цена телевизора: " + this.nPrice);
                System.out.println("Компания-производитель телевизора: " + this.sCompany);
                System.out.println("Страна-производитель телевизора: " + this.sCountry);

    }


    public void isSmart (String isItSmart){
        if (isItSmart.toLowerCase().equals("yes") || isItSmart.toLowerCase().equals("да")){
            System.out.println(888);
            this.bSmart = true;
            System.out.println("У модели " + this.sModel+ " есть функция Smart");
            this.nPrice = this.nPrice+10000;
            System.out.println("Цена выросла на 10000 до  " + this.nPrice);
        }
    };
}   
    

/*
Формулировка задания:

Реализовать класс Телевизор. У класса есть поля, свойства и методы.
Проверить работу в классе App, методе main.

Ожидаемый результат:

1. Создан класс Телевизор;
2. У класса есть поля, свойства и методы. Поля желательно сделать private. 
3. Задать новые значения полям класса можно через конструктор.
4. Создан класс App с методом main.
5. В методе main класса App создано несколько экземпляров класса
Телевизор.
6. Дополнительно. Задавать параметры класса Телевизор с клавиатуры или случайным числом.

*/