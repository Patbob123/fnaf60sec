/**
 * Helper Class to return Coordinates 
 * 
 * @author Dawson
 * @version January 2024
 */
public class Coordinate
{
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
    /**
     * Getter method for x-coordinate
     */
    public int seeX(){
        return x;
    }
    /**
     * Getter method for y-coordinate
     */
    public int seeY(){
        return y;
    }
    /**
     * toString method to return the (x,y) coordinate 
     */
    public String toString(){
        return "X: "+x+" Y:"+y;
    }
}
