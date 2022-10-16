package exercises;

public class lockedBox extends  box{

    private boolean isLocked;

    public lockedBox(int length, int width){
        super(length, width);

        this.isLocked = true;
    }

    @Override
    public String toString(){
        return length + " " + width + " " + isLocked;
    }




    public static void main(String[] args){
        box testar = new lockedBox(5,7);
        System.out.println(testar);
    }
}
