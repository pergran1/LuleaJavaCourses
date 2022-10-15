package IntroductionProgramming;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;

public class thirdTask {

    public static void main(String args[])
    {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Välkommen till spelet 12. Du ska slå 1-3 tärningar och försöka få summan 12... ");

        int diceOne = 0;
        int diceTwo = 0;
        int diceThree = 0;
        int sum = 0;
        int wins = 0;
        int losses = 0;

        int loopCount = 0;

        while(true)
        {
            // ha en counter variabel här som räknar hur många gånger man har spelat och avlutar efter tredje?

            System.out.print("Ange vilken tärning du vill slå [1,2,3](avsluta med q): ");


            String strInput = userInput.nextLine();

            // true = the input is a int
            Boolean strOrInt = checkInputStrOrInt(strInput);



            if(strOrInt == true)
            {
                int intAnswer = Integer.parseInt(strInput);

                if(intAnswer < 0 || intAnswer > 3){
                    System.out.println("Du skrev ett nummer som inte är mellan 1 och 3 försök igen tack");
                    continue;
                }
                else if(intAnswer == 1){
                    diceOne = getRandomNumber();
                }
                else if(intAnswer == 2) {
                    diceTwo = getRandomNumber();
                }
                else if(intAnswer == 3) {
                    diceThree = getRandomNumber();
                }

                sum = diceOne + diceTwo + diceThree;
                loopCount++;

                if(sum == 12){
                    wins++;
                    loopCount = 3;
                }

                if (loopCount == 3){
                    //start a new game
                    if(sum > 12){
                        losses++;
                    }

                    printResults( diceOne, diceTwo, diceThree, wins, losses);
                    System.out.println("nästa omgång!");
                    diceOne = 0;
                    diceTwo = 0;
                    diceThree = 0;
                    loopCount = 0;
                    continue;
                }
                printResults(diceOne, diceTwo, diceThree, wins, losses);
                continue;
            }

            exitLoop(strInput);

        }
    }

    public static int getRandomNumber(){
        Random randomObj = new Random();
        return randomObj.nextInt(6) + 1;
    }

    public static void printResults(int diceOne, int diceTwo, int diceThree, int wins, int losses){
        int sum = diceOne + diceTwo + diceThree;
        System.out.printf("%d %d %d sum: %d  #vinst: %d #förlust: %d \n", diceOne, diceTwo, diceThree, sum, wins, losses);
    }

    public static void exitLoop(String str){
        if (str.toLowerCase().equals("q")){
            System.out.println("Avslutar spel!");
            System.exit(0);
        } else {
            System.out.println("Du skrev ett kommando som inte finns med, försök igen");
        }
    }

    public static boolean checkInputStrOrInt(String str){

        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
