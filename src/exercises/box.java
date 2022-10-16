package exercises;

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
    }


}
