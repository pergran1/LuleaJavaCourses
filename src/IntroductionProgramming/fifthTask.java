package IntroductionProgramming;

import javax.annotation.processing.SupportedSourceVersion;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class fifthTask {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args){


        System.out.println("Enter the values, end with q");

        System.out.println(
                "----------------------------------\n" +
                "# Test av area och volymmetoderna\n" +
                "---------------------------------");
        printAreaVolume();


        System.out.println(
                        "----------------------------------\n" +
                        "# Test av bråktalsmetoderna\n" +
                        "----------------------------------");

        printFractionsAll();



    }

    public static double area(int radius){
        return Math.PI * Math.pow(radius, 2);
    }

    public static double area(int radius, int height){
        return Math.PI * radius * pythagoras(height, radius);
    }

    public static double pythagoras(int sideA, int sideB){
        return Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
    }

    public static double volume(int radius, int height){
        return (Math.PI * Math.pow(radius, 2) * height) / 3;
    }

    public static int[] fraction(int nominator, int denominator){
        int[] arrValues = new int[3];
        if(denominator == 0){
            return null;
        }

        if(nominator == 0){
            return arrValues; //array with only 0
        }

        int commonDivider;
        int changedNominator;
        int changedDenominator;

        commonDivider = gcd(nominator, denominator);
        changedNominator = nominator / commonDivider;
        changedDenominator = denominator / commonDivider;



        arrValues[0] = nominator/denominator;
        arrValues[1] = changedNominator % changedDenominator;
        arrValues[2] = changedDenominator;
        return  arrValues;
    }

    public static int gcd(int a, int b) {
        if (b==0) {
            return a;
        } else{
            return gcd(b,a%b);
        }
    }


    public static void printFraction(int[] parts){
        String stringOutput = "0";
        if (parts == null) {
            System.out.println("Error");

        }

        else if(parts[0] == 0 && parts[1] == 0 && parts[2] == 0){
            System.out.println(stringOutput);
        }


        else if(parts[0] == 0){
            System.out.println(parts[1] +"/"+ parts[2]);
        }
        else if(parts[1] == 0){
            System.out.println(parts[0]);
        }
        else{
            //Everything should be alright
            System.out.println(parts[0] + " " + parts[1] +  "/"+ parts[2]);
        }

    }




    public static void printAreaVolume(){
        int r = 0;
        int h  = 0;
        final DecimalFormat dfSharp = new DecimalFormat("#.##");
        while(true){
            r = input();
            if(r == -1){
                break;
            }

            h = input();
            if(h == -1){
                break;
            }

            System.out.println("r = " + r + " h = " + h);
            System.out.println("Basytans area:      " + dfSharp.format(area(r)));
            System.out.println("Mantelytans area:   " + dfSharp.format(area(r, h)));
            System.out.println("volymn:             " + dfSharp.format(volume(r, h)));
            System.out.println("");


        }
    }


    public static void  printFractionsAll(){
        int nominator = 0;
        int denominator = 0;
        final DecimalFormat dfSharp = new DecimalFormat("#.##");

        while(true){
            nominator = input();
            if(nominator == -1){
                break;
            }

            denominator = input();
            if(denominator == -1){
                break;
            }

            System.out.print(nominator + "/" + denominator +"=      ");
            printFraction(fraction(nominator, denominator));

        }
    }



    public static int input() {
        String in;
        int ENDVALUE = -1;
        String ENDLETTER= "q";

        //Keep going until q, ignore invalid values.
        while(true) {
            //Check if it is integer,
            if(keyboard.hasNextInt()) {
                return keyboard.nextInt();
            }
            else {
                //Check if it is q
                in = keyboard.next();
                if (in.equals(ENDLETTER))
                    return ENDVALUE;
                    //Check if it is something else
               // else {
                 //   System.out.println(in + " is not a valid integer ");
                // }
            }
        }
    }



}
