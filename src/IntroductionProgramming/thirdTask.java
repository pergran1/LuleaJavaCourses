package IntroductionProgramming;

import java.util.Random;
import java.util.Scanner;

public class thirdTask {

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Välkommen till spelet 12. Du ska slå 1-3 tärningar och försöka få summan 12... ");

        int diceOne = 0;
        int diceTwo = 0;
        int diceThree = 0;

        while(true)
        {
            // ha en counter variabel här som räknar hur många gånger man har spelat och avlutar efter tredje?
            System.out.print("Ange vilken tärning du vill slå [1,2,3](avsluta med q): ");

            if(input.hasNextInt())
            {
                int intAnswer = input.nextInt();
                if(intAnswer == 1){
                    diceOne = getRandomNumber();
                }
                else if(intAnswer == 2) {
                    diceTwo = getRandomNumber();
                }
                else if(intAnswer == 3) {
                    diceThree = getRandomNumber();
                } else {
                    System.out.println("Du skrev ett nummer som inte är mellan 1 och 3 försök igen tack");
                    continue;
                }
                printResults( diceOne, diceTwo, diceThree);
                continue;
            }


            // Control if the user wants to quit
            if(input.hasNext())
            {
                String strAnswer = input.nextLine().toLowerCase();
                if(strAnswer.equals("q")){
                    System.out.println("Avslutar spel!");
                    break;
                } else{
                    System.out.println("Du angav ett värde som inte har en funktionen i detta spel, vänligen försök igen");
                }
                printResults(diceOne, diceTwo, diceThree);
                continue;

            }



            input.nextLine();
        }
    }

    public static int getRandomNumber(){
        Random randomObj = new Random();
        return randomObj.nextInt(6) + 1;
    }

    public static void printResults(int diceOne, int diceTwo, int diceThree){
        int sum = diceOne + diceTwo + diceThree;
        System.out.printf("%d %d %d sum: %d  #vinst: 0 #förlust: 0 ", diceOne, diceTwo, diceThree, sum);
    }
}
