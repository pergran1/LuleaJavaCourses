package IntroductionProgramming;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class fourthTask {

    public static void main(String[] args){

        int randomNumberCount;
        int[] numberArray = new int[0];
        int[] tempArray = new int[0];

        System.out.println("Hur många slumptal i intervallet 0 - 999 önskas?");

        // Get a int from the user



        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextBigInteger()) {
            System.out.println("Input is not a number.");
            scan.nextLine();
        }
        int number = scan.nextInt();

        System.out.println("Number is " + number);

        try
        {
            numberArray = new int[number];
            tempArray = new int[number];
        }
        catch (OutOfMemoryError e)
        {
            System.out.println("Felaktig inmatning! Går inte att allokera så mycket minne");

        }



        int indexForEven = 0;
        int indexForUneven = 0;

        for(int i = 0; i < number; i++){
            numberArray[i] = getRandomNumber();
        }
        //System.out.println(Arrays.toString(numberArray));

        System.out.println("Här är de slumpade talen: ");
        for(int i = 0; i < numberArray.length; i++){
             System.out.print(numberArray[i] + " ");
            //System.out.print(numberArray[ (number-1) -i] + " Är sista \n");

            if(numberArray[i] % 2 == 0){
              //  System.out.println(numberArray[i] + " är jämn ");
                tempArray[indexForEven] = numberArray[i];
               // System.out.println(Arrays.toString(tempArray) + "Så ser array ut nu för jämna ");
              //  System.out.println(tempArray[i] + " Är på temp array platsen");
                indexForEven++;

            } else {
               // System.out.println(numberArray[i] + " är oojämn ");
                tempArray[(number-1) -indexForUneven]  = numberArray[i];
               // System.out.println(Arrays.toString(tempArray) + "Så ser array ut nu ");
                indexForUneven++;
            }
        }


        System.out.println("\nThe total number of even numbers are " + indexForEven);

        for(int i = 0; i < indexForEven; i++){
            // print only the even numbers
            System.out.print(tempArray[i] + " ");
        }
        System.out.println(Arrays.toString(tempArray));

        // Left to do is to filter the array in two parts, one for the even and one for the uneven



    }



    public static int getRandomNumber(){
        Random randomObj = new Random();
        return randomObj.nextInt(999) + 1;
    }

}
