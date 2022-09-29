package IntroductionProgramming;

import java.util.Scanner;

public class secondTask {

    public static void main(String[] args) {

        int month = 0;
        int day = 0;
        int sunriseHour = 0;
        int sunriseMinute = 0;

        int sunsetHour = 0;
        int sunsetMinute = 0;

        final int SOLARPANALS = 26;
        final double SURFACE_AREA = 1.7 * 1;
        final double AREA_OF_AFFECT;


        final int SUNRAY = 166;
        final double ELECTRICAL_PRICE = 0.9;
        final double SOLAR_CELL_AFFECT = 0.2;

        AREA_OF_AFFECT = SOLARPANALS * SURFACE_AREA;


        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        userInput.useDelimiter("-|\\s+");

        System.out.println("Skriv dagens datum [mm-dd]>");
        month = userInput.nextInt();
        day = userInput.nextInt();
        System.out.println(month);
        if(month < 6 || month > 7){
            System.out.println("Felmeddelande! Du har angett en månad som inte är juni eller juli");
            System.exit(0);
        }

        System.out.println("Skriv in tidpunkt soluppgång [hh:mm]>");
        userInput.useDelimiter(":|\\s+");
        userInput.nextLine();
        sunriseHour = userInput.nextInt();
        sunriseMinute = userInput.nextInt();


        System.out.println("Skriv in tidpunkt solnedgång [hh:mm]>");
        userInput.nextLine();


        sunsetHour = userInput.nextInt();
        sunsetMinute = userInput.nextInt();
        //userInput.nextLine();

        checkMinutes(sunriseMinute);
        checkMinutes(sunsetMinute);


        double sunHours = sunsetHour - sunriseHour + ((sunsetMinute/60.0) - (sunriseMinute/60.0));
        double production = (SUNRAY * SOLAR_CELL_AFFECT * AREA_OF_AFFECT * sunHours) / 1000;
        double totalCost = production * ELECTRICAL_PRICE;
        System.out.println(production);
        System.out.printf("sunhours is: %.2f\n", sunHours );
        System.out.printf("Produktionen %d/%d är:  %.2f kWh till ett värde av: %.2f", day, month, production, totalCost );



    }

    public static void checkMinutes(int minutes){
        if(minutes > 60 || minutes < 0 ) {
            System.out.println("Felmeddelande! Du har angett ett minutvärde som är negativt eller över 60");
            System.exit(0);
        }
    }
}
