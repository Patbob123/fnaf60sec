import greenfoot.GreenfootImage;
import greenfoot.Color;

/**
 * Hitbox class to detect collisions
 * 
 * @author Vincent
 * @version January 2024
 */
public class Hitbox extends SuperSmoothMover
{
    private int width;
    private int height;
    private Color red = new Color(255,0,0);
    /**
     * Constructor for the Hitbox Class
     * 
     * @param width     width of hitbox
     * @param height    height of hitbox
     */
    public Hitbox(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        GreenfootImage hitbox = new GreenfootImage(width, height);
        hitbox.setColor(red);
        hitbox.fill();
        setImage(hitbox);
    
    }
    
    /**
     * Method to check if hitbox collided with the wall
     * 
     * @param x     x-coordinate
     * @param y     y-coordinate
     */
    public boolean intersectWall(int x, int y){
        int curX = getX();
        int curY = getY();
        boolean touchingWall = false;
        
        setLocation(curX+x, curY+y);
        if(isTouching(Wall.class)){
            touchingWall = true;
        }
        setLocation(curX, curY);
        
        return touchingWall;
    }
    /**
     * Method to check if hitbox collided with a different class
     * 
     * @param c        The class of the object being collided
     */
    public boolean intersect(Class c){
        return isTouching(c);
    }
}
