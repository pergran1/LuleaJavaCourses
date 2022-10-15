
package IntroductionProgramming;
/**
 * Denna kod kommer först be användaren att specificera ett datumn med format mm-dd
 * Ingen kontroll görs på att formatet är rätt men det görs en kontroll som bevisar om månaden är 6 eller 7
 * Sedan kommer användaren skriva in soluppgång och solnedgång. En kontroll görs för att kolla att soluppgången
 * är tidigare än solnedgången.
 *
 * Sedan beräknas antalet soltimmar och effekten och priset som dessa soltimmar ger, resultatet presenteras i
 * en fin print sats
 *
 * @author Per Granberg, epager-1
 */

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
        if (month < 6 || month > 7) {
            System.out.println("Felmeddelande! Du har angett en månad som inte är juni eller juli");
            System.exit(0);
        }

        System.out.println("Skriv in tidpunkt soluppgång [hh:mm]>");
        userInput.useDelimiter(":|\\s+");
        userInput.nextLine();
        sunriseHour = userInput.nextInt();
        sunriseMinute = userInput.nextInt();
        checkHour(sunriseHour);
        checkMinutes(sunriseMinute); // control if it is above 60


        System.out.println("Skriv in tidpunkt solnedgång [hh:mm]>");
        userInput.nextLine();


        sunsetHour = userInput.nextInt();
        sunsetMinute = userInput.nextInt();
        checkHour(sunsetHour);
        checkMinutes(sunsetMinute);


        double sunHours = sunsetHour - sunriseHour + ((sunsetMinute / 60.0) - (sunriseMinute / 60.0));
        if (sunHours <= 0) {
            System.out.println("Felmeddelande! Du har angivit en solnedgång som inträffar tidigare eller samtidigt som " +
                    "din soluppgång, och detta är enligt naturens lagar omöjligt");
            System.exit(0);

        }
        double production = (SUNRAY * SOLAR_CELL_AFFECT * AREA_OF_AFFECT * sunHours) / 1000;
        double totalCost = production * ELECTRICAL_PRICE;

        // Print out the information
        System.out.println("===========================================");
        System.out.printf("Soltimmar: %.2f timmar\n", sunHours);
        System.out.printf("Produktionen %d/%d är:  %.2f kWh till ett värde av: %.2f kr", day, month, production, totalCost);

    }

    public static void checkMinutes(int minutes) {
        if (minutes > 59) {
            System.out.println("Felmeddelande! Du har angett ett minutvärde som är över 60 minuter");
            System.exit(0);
        }
    }

    public static void checkHour ( int hour){
        if (hour > 23 || hour < 0) {
            System.out.println("Felmeddelande! Du har angett ett timvärde som inte är mellan 00 eller 23");
            System.exit(0);
        }
    }

}