/**
 * Write a description of class Coordiniate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coordinate
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    /**
     * Constructor for objects of class Coordiniate
     */
    public Coordinate(int x, int y)
    {
        this.x = x; 
        this.y = y;
    }
    public int seeX(){
        return x;
    }
    public int seeY(){
        return y;
    }
    public String toString(){
        return "X: "+x+" Y:"+y;
    }
}
