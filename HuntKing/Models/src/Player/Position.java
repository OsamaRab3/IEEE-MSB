package Player;

public class  Position{
    private int  x = 0;
    private  int y = 0;

   public Position(int x ,int y){
       this.x = x;
       this.y = y;
   }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    // here  hard to extend without modification.
    // Violates Open-Closed Principle
    public int[] getPosition() {
        return new int[] { x, y };
    }

}
