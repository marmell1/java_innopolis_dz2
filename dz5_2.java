import java.util.Scanner;

public class dz5_2 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in, "cp866");
        System.out.println();
        System.out.println("Введите последовательность символов, не более 106");
        String sSymbols = scanner1.nextLine();
        if (sSymbols.length()>106){System.out.println("Введено слишком большое количество символов");}
        else{
            int nCount = 0;
            System.out.println("Могут ли стрелы пересекаться? Если да, то >>-->>--> - это две стрелы, если нет, то одна");
            System.out.println("Введите 1 если не могут пересекаться, 0 - если могут");
            Scanner scanner2 = new Scanner(System.in, "cp866");
            String sCross = scanner2.nextLine();
            int nCross = Integer.valueOf(sCross);
            int nDelta = 1;

            for (int i =0;i<(sSymbols.length()-4);i= i+nDelta){
                nDelta=1;
                int nIndexR = sSymbols.indexOf(">>-->",i);
                int nIndexL = sSymbols.indexOf("<--<<",i);
                if (nIndexR==i){nCount++;nDelta=nDelta+(5*nCross);}
                if (nIndexL==i){nCount++;;nDelta=nDelta+(5*nCross);};
            }
            System.out.println("Итоговое количество стрел - " +nCount);
            scanner2.close();
            }
        scanner1.close();
        
    }
}

/*
Задача 2. Задана последовательность, состоящая только из символов ‘>’, ‘<’ и ‘-‘. Требуется найти количество стрел, 
которые спрятаны в этой последовательности. Стрелы – это подстроки вида ‘>>-->’ и ‘<--<<’.
Входные данные: в первой строке входного потока записана строка, состоящая из символов ‘>’, ‘<’ и ‘-‘ (без пробелов). 
Строка может содержать до 106 символов.
Выходные данные: в единственную строку выходного потока нужно вывести искомое количество стрелок.

 */
