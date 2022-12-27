package IntroductionProgramming;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class sixthTask {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args){
        //menu();

        int[][] articlesMatrix = new int[10][3];
        int[][] sales = new int[10][3];
        Date[] dateTime = new Date[100];

        int articleNumber = 1000;
        int userInput;
        int noOfArticles;



        /**
         * The main framework for the program. It is an endless while loop that repeatedly calls the menu on every iteration
         * as each one will run one command from the user. It checks the input from the menu with that of its switch statement,
         * checking if any of the 6 commands have been issued and then running them. After every entry, it repeats and waits
         * for the next command, even if the user input wasn't a valid option of 1-6.
         */
        outerloop:
        while(true)
        {
            System.out.println();
            userInput = menu();

            switch (userInput)
            {
                case 1:
                    System.out.println("Hur många artiklar vill du lägga till?");
                    noOfArticles = input();

                    while(noOfArticles <= 0) {
                        System.out.println("Lägg till minst 1 artikel?");
                        noOfArticles = input();
                    }

                    // add nbr of articles to add
                    articlesMatrix = insertArticles(articlesMatrix, articleNumber, noOfArticles);
                    articleNumber += noOfArticles;
                    break;

                case 2:
                    removeArticle(articlesMatrix);
                    break;

                case 3:
                    printArticles(articlesMatrix);
                    break;

                case 4:
                    sellArticle(sales, dateTime, articlesMatrix);
                    break;

                case 5:
                    printSales(sales, dateTime);
                    break;

                case 6:
                    sortedTable(sales, dateTime);
                    break;

                case 7:
                    System.out.println("Programmet avslutas");
                    break outerloop;

            }
        }

    }


    public static int menu(){
        System.out.println("" +
                "1. Lägg in artiklar \n" +
                "2. Ta bort artikel\n" +
                "3. Visa artiklar\n" +
                "4. Försäljning\n" +
                "5. Orderhistorik\n" +
                "6. Sortera orderhistoriktabell\n" +
                "7. Avsluta\n" +
                "Ditt val:");

        return input();
    }

    public static int input(){
        String in;

        while(true) {
            if(keyboard.hasNextInt()) {
                return keyboard.nextInt();
            }
            else {
                in = keyboard.next();
                System.out.println("Du skrev: " + in + " vilket inte är giltigt, försök igen och skriv en siffra!");
                continue;
            }
        }
    }

    public static int[][] insertArticles (int[][]articles, int articleNumber, int noOfArticles) {


        int[][] newMatrix = checkFull(articles, noOfArticles);


            for (int i = 0; i < newMatrix.length ; i++)
            {
               //System.out.println(" i är: " + Arrays.toString(articles[i]));
                // length returns number of rows
                if(newMatrix[i][0] == 0 && noOfArticles != 0){
                    //insert values into this row
                    newMatrix[i][0] = articleNumber;
                    newMatrix[i][1] = getNbrArticles();
                    newMatrix[i][2] = getPrice();
                    articleNumber++;
                    noOfArticles--;



                }
            }

        return newMatrix;
    }

    public static int getPrice(){
        Random randomObj = new Random();
        return randomObj.nextInt(1000) + 1;
    }

    public static int getNbrArticles(){
        Random randomObj = new Random();
        return randomObj.nextInt(10) + 1;
    }

    public static int getNbrOfEmptyRows(int[][]matrix, int lookFor){
        // use this to get the number index of where the matrix have data
        int value;
        int emptyRows = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            value = matrix[i][0];
            if(value == lookFor){
                // found the value, return index
                emptyRows++;
            }
        }
        // return -1 if there is no value in first column that match
        return emptyRows;
    }

    public static boolean checkIfBiggerMatrix(int[][]matrix, int newRowsNeeded, int lookFor){

        int matrixLength = matrix.length;
        int emptyRows = getNbrOfEmptyRows(matrix, lookFor);

        return (emptyRows - newRowsNeeded ) < 0;
    }

    public static int[][] checkFull(int[][]articles, int noOfArticles){
        int increaseRows = 0;
        boolean needBiggerMatrix = checkIfBiggerMatrix(articles, noOfArticles,0);

        if (needBiggerMatrix == true){
            // the matrix is to small and needs to be bigger
            increaseRows += noOfArticles;
            // System.out.println("increase rows är nu " + increaseRows);
            int[][] newMatrix = new int[articles.length + increaseRows ][articles[0].length];
            for (int i = 0; i < articles.length; i++) {
                System.arraycopy(articles[i], 0, newMatrix[i], 0, articles[i].length);
            }
            // return a bigger matrix
            return newMatrix;

        } else {
            // return the same matrix
            return articles;
        }

    }


    public static void removeArticle (int[][]articles){
        System.out.println("Skriv vilket artikelnummer du vill ta bort: ");
        int getArticleToRemove = input();

        // loop matrix to find article
        for (int i = 0; i < articles.length ; i++)
        {
            if(articles[i][0] == getArticleToRemove){
                //insert values into this row
                articles[i][0] = 0;
                articles[i][1] = 0;
                articles[i][2] = 0;

            }
        }
    }



    public static void printArticles (int[][]articles){
        int[] firstColumn  = new int[articles.length];
        for (int i = 0; i < firstColumn.length ; i++){
            firstColumn[i] = articles[i][0];

        }
         //sort the article
        bubbleSort(firstColumn);

        for (int j = 0; j < firstColumn.length ; j++){
            if(firstColumn[j] == 0){
                continue;
            } else {
                for (int i = 0; i < articles.length ; i++)
                {
                    //System.out.println(" i är: " + Arrays.toString(articles[i]));
                    // length returns number of rows
                    if(articles[i][0] == firstColumn[j] ){
                        //insert values into this row
                        System.out.println("Artikelnummer: " + articles[i][0] + " Antal: " + articles[i][1] + " Pris: " + articles[i][2]);
                    }
            }

            }
        }


    }


    private static void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }






    public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles){
        System.out.println("Skriv vilket artikelnummer du vill köpa av: ");
        int getArticleNbr = input();
        System.out.println("Skriv hur många enheter du vill köpa: ");
        int getNbrOfProducts = input();

        while(getNbrOfProducts < 1)
        {
            System.out.println("Invalid input. Please enter an integer number that is above 0.");
            getNbrOfProducts = input();
        }

        for (int i = 0; i < articles.length ; i++)
        {
            //System.out.println(" i är: " + Arrays.toString(articles[i]));
            // length returns number of rows
            if(articles[i][0] == getArticleNbr){
                // Control if the number of articles in stock are enough
                System.out.println("Artikeln: " + articles[i][0] + " letar efter " + articles[i][1]  );
                if (articles[i][1] >= getNbrOfProducts ){
                    // sale is confirmed
                    articles[i][1] -= getNbrOfProducts;
                    System.out.println("Artikeln: " + articles[i][0] + " gjorde en försäljning " + articles[i][1]  );

                    // loop through sales to find next index
                    for (int k = 0; k < sales.length; k++)
                    {
                        if (sales[k][0] == 0)
                        {
                            sales[k][0] = getArticleNbr;
                            sales[k][1] = getNbrOfProducts;
                            sales[k][2] = articles[i][2] * getNbrOfProducts;

                            salesDate[k] = new Date();

                            break;
                        }
                    }
                } else {
                    System.out.println("Varan har inte tillräckligt många artiklar, skriv ett lägre antal");
                }

            }
        }

    }


    public static void printSales ( int[][] sales, Date[] salesDate)
    {
        System.out.println("Date/Time                           Article Number   Amount Sold   Sum");
        for (int i = 0; i < sales.length; i++)
        {

            if (sales[i][0] != 0)
            {
                System.out.println(salesDate[i].toString() + "       " + sales[i][0] + "             " + sales[i][1] + "             " + sales[i][2]);
            }
        }
    }




    public static void sortedTable(int[][]sales,  Date[] salesDate){
        // kombinera sales med salesDate
        // eftersom man inte måste sortera på tid så räcker det kanske att bara printa
        int[] firstColumn  = new int[sales.length];
        for (int i = 0; i < firstColumn.length ; i++){
            firstColumn[i] = sales[i][0];

        }
        //sort the article
        bubbleSort(firstColumn);
        firstColumn = returnNonRepeated(firstColumn);

        for (int j = 0; j < firstColumn.length ; j++){
            if(firstColumn[j] == 0){
                continue;
            } else {
                for (int i = 0; i < sales.length ; i++)
                {

                    if(sales[i][0] == firstColumn[j] ){
                        //insert values into this row
                        System.out.println("Artikelnummer: " + sales[i][0] + " Pris: " + sales[i][2] + " Datum: " + salesDate[i]);
                    }
                }

            }
        }

    }


    public static int[] returnNonRepeated(int[] arr) {

        int[] temp = new int[arr.length];
        int numbersCount = 1;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                temp[numbersCount] = arr[i];
                numbersCount++;
            } else {
                if (arr[i] != arr[i - 1]) {
                    temp[numbersCount] = arr[i];
                    numbersCount++;
                }
            }
        }

        return temp;
    }



}

