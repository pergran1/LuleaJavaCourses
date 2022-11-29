package IntroductionProgramming;

import javax.annotation.processing.SupportedSourceVersion;
import java.sql.Array;
import java.util.Arrays;

public class fifthTask {

    public static void main(String[] args){


        Arrays.stream(fraction(7,3)).forEach(System.out::println);
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


        arrValues[0] = nominator/denominator;
        arrValues[1] = gcd(nominator, denominator);
        arrValues[2] = denominator;
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

        if(parts[0] == 0){
            System.out.println(parts[1] +"/"+ parts[2]);
        }
    }


}
