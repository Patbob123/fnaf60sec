import greenfoot.GreenfootImage;
import greenfoot.Color;

/**
 * Write a description of class Hitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hitbox extends SuperSmoothMover
{
    // instance variables - replace the example below with your own
    private int width;
    private int height;
    private Color red = new Color(255,0,0);
    public Hitbox(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        GreenfootImage hitbox = new GreenfootImage(width, height);
        hitbox.setColor(red);
        hitbox.fill();
        setImage(hitbox);
    
    }
    public boolean intersectWall(int x, int y){
        if(getObjectsAtOffset(x,y,Wall.class).size() > 0){
            return true;
        }
        return false;
    }
}
