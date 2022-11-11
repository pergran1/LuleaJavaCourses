package IntroductionProgramming;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class fourthTask {

    public static void main(String[] args){


        int[] numberArray = new int[0];
        int[] tempArray = new int[0];
        int indexForEven = 0;
        int indexForUneven = 0;

        System.out.println("Hur många slumptal i intervallet 0 - 999 önskas?");


        // Get the input from the user, only a int will work
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextBigInteger()) {
            System.out.println("Input is not a number.");
            scan.nextLine();
        }
        int number = scan.nextInt();

        // Checking if it is to many numbers
        try
        {
            numberArray = new int[number];
            tempArray = new int[number];
        }
        catch (OutOfMemoryError e)
        {
            System.out.println("Felaktig inmatning! Går inte att allokera så mycket minne");

        }



        // A loop that prints all numbers in the order they are created and also calculate if they are even or uneven
        for(int i = 0; i < number; i++){
            numberArray[i] = getRandomNumber();
        }

        System.out.println("Här är de slumpade talen: ");
        for(int i = 0; i < numberArray.length; i++){
             System.out.print(numberArray[i] + " ");

            if(numberArray[i] % 2 == 0){
                tempArray[indexForEven] = numberArray[i];
                indexForEven++;

            } else {
                tempArray[(number-1) -indexForUneven]  = numberArray[i];
                indexForUneven++;
            }
        }


        Arrays.sort(tempArray, 0, indexForEven); // sort the even numbers in ascending order
        Arrays.sort(tempArray, indexForEven+1, tempArray.length); // sort the even numbers in ascending order


        // first I print the even numbers
        System.out.println("\nHär är de slumpade talen ordnade:");
        for(int i = 0; i < indexForEven; i++){
            System.out.print(tempArray[i] + " ");
        }

        // print "-"
        System.out.print(" - ");
        // print the uneven numbers, but backwards
        for(int i = numberArray.length; i > indexForEven; i--){
            //System.out.println(" i är " + i);
            System.out.print(tempArray[i-1] + " ");
        }

        System.out.println("\nAv de ovanstående " + number + " tal var " + indexForEven + " jämna och " + indexForUneven + " udda");




    }



    public static int getRandomNumber(){
        Random randomObj = new Random();
        return randomObj.nextInt(999) + 1;
    }

}
