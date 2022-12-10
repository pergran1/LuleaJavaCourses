package IntroductionProgramming;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class sixthTask {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args){
        //menu();

        int[][] matrix = {  { 3, 2, 1 },
                { 9, 11, 5},
                { 0, 0, 13 },
                { 7, 21, 14 } };

        int[][] articlesMatrix = new int[10][3];
        int[][] sales = new int[10][3];
        Date[] dateTime = new Date[100];

        //insertArticles(articlesMatrix, 2, 3);
       // getNbrOfEmptyRows(matrix, 0);

        //System.out.println(checkIfBiggerMatrix(matrix, 1,0));
        //System.out.println(checkIfBiggerMatrix(matrix, 2,0));

        // printMatrix(insertArticles(matrix, 4, 3));
        //matrix = insertArticles(matrix, 4, 3);

       // removeArticle(matrix);

        printMatrix(bubbleSortMatrix(matrix));
        System.out.println("Testar ny variant ");
        printMatrix(sortMatrix(matrix));




       // printArticles(matrix);

       // sellArticle(sales, dateTime,  matrix);
       // printMatrix(sales);





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
        int ENDVALUE = -1;
        int ENDNUMBER = 7;

        //Keep going until q, ignore invalid values.
        while(true) {
            //Check if it is integer,
            if(keyboard.hasNextInt()) {
                return keyboard.nextInt();
            }
            else {
                //Check if it is q
                in = keyboard.next();
                if (in.equals(ENDNUMBER))
                    return ENDVALUE;
                //Check if it is something else
                // else {
                //   System.out.println(in + " is not a valid integer ");
                // }
            }
        }
    }

    public static int[][] insertArticles (int[][]articles, int articleNumber, int noOfArticles) {

        int insertedNumberArticles = 0;
        int[][] newMatrix = checkFull(articles, noOfArticles);


            for (int i = 0; i < newMatrix.length ; i++)
            {
               //System.out.println(" i är: " + Arrays.toString(articles[i]));
                // length returns number of rows
                if(newMatrix[i][0] == 0){
                    //insert values into this row
                    newMatrix[i][0] = articleNumber;
                    newMatrix[i][1] = getNbrArticles();
                    newMatrix[i][2] = getPrice();
                    insertedNumberArticles++;
                    System.out.println("Matrix:");
                    printMatrix(newMatrix);


                    if (insertedNumberArticles == noOfArticles){
                        //dont insert any more
                        return newMatrix;
                    }


                }
            }
        System.out.println("Nu är de slut");
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
       // System.out.println("Rows needed are " + newRowsNeeded );
        int matrixLength = matrix.length;
        //System.out.println(matrixLength);
        int emptyRows = getNbrOfEmptyRows(matrix, lookFor);
       // System.out.println("Emptuy rows are : " + emptyRows);

        return (emptyRows - newRowsNeeded ) <= 0;
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
            //System.out.println(" i är: " + Arrays.toString(articles[i]));
            // length returns number of rows
            if(articles[i][0] == getArticleToRemove){
                //insert values into this row
                articles[i][0] = 0;
                articles[i][1] = 0;
                articles[i][2] = 0;

            }
        }
    }

    public  static void printMatrix(int mat[][]){
        for (int[] row : mat)
            System.out.println(Arrays.toString(row));
    }


    public static int[][] bubbleSortMatrix(int[][] arr) {
        int arrayLength = arr.length;
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 1; j < (arrayLength - i); j++) {
                int nameTemp, scoreTemp;
                int leftValue, rightValue;
                leftValue = Integer.valueOf(arr[j - 1][1]);
                rightValue = Integer.valueOf(arr[j][1]);
                if (leftValue > rightValue) {
                    //swap elements
                    nameTemp = arr[j - 1][0];
                    scoreTemp = arr[j - 1][1];
                    arr[j - 1][0] = arr[j][0];
                    arr[j - 1][1] = arr[j][1];
                    arr[j][0] = nameTemp;
                    arr[j][1] = scoreTemp;
                }

            }
        }

        return arr;
    }


    private static int[][] sortMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int totalCount = row * col;

        System.out.println("totalCount : " +totalCount);

        boolean noSwaps = false;
        for(int i = 0; !noSwaps; i++) {
            noSwaps = true;

            for(int j = 1; j < totalCount - i; j++) {
                int currentRow = (j-1) / col;
                int currentOffset = (j-1) % col;
                int nextRow = j / col;
                int nextOffset = j % col;

                if( matrix[currentRow][currentOffset] > matrix[nextRow][nextOffset]) {
                    //swapping
                    int temp = matrix[nextRow][nextOffset];
                    matrix[nextRow][nextOffset] = matrix[currentRow][currentOffset];
                    matrix[currentRow][currentOffset] = temp;

                    noSwaps = false;
                }
            }
        }
        return matrix;
    }


    public static void printArticles (int[][]articles){
        articles = bubbleSortMatrix(articles);

        for (int i = 0; i < articles.length ; i++)
        {
            //System.out.println(" i är: " + Arrays.toString(articles[i]));
            // length returns number of rows
            if(articles[i][0] != 0){
                //insert values into this row
                System.out.println("Artikelnummer: " + articles[i][0] + " Antal: " + articles[i][1] + " Pris: " + articles[i][2]);
            }
        }


    }


    public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles){
        System.out.println("Skriv vilket artikelnummer du vill köpa av: ");
        int getArticleNbr = input();
        System.out.println("Skriv hur många enheter du vill köpa: ");
        int getNbrOfProducts = input();

        for (int i = 0; i < articles.length ; i++)
        {
            //System.out.println(" i är: " + Arrays.toString(articles[i]));
            // length returns number of rows
            if(articles[i][0] == getArticleNbr){
                // Control if the number of articles in stock are enough
                if (articles[i][1] >= getArticleNbr ){
                    // sale is confirmed
                    articles[i][1] -= getArticleNbr;

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









}

