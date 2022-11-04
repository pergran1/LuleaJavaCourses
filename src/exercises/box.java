package exercises;

import java.text.SimpleDateFormat;
import java.util.Date;

public class box {
    // Exercieses in inheritance
    // Create a abstract class that describe a box and then create different boxes with different lengths and so on

    int length = 0;
    int width = 0;

    public box(int length, int width){
        this.length = length;
        this.width = width;
    }



    @Override
    public String toString(){
        return length + " " + width;
    }

    public static void main(String[] args) {
        box testBox = new box(2,5);
        System.out.println(testBox);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        System.out.println(strDate);

    }


}
