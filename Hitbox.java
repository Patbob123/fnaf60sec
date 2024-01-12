import greenfoot.GreenfootImage;

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
    public Hitbox(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        GreenfootImage hitbox = new GreenfootImage(width, height);
        hitbox.fillRect(100, 100, 100, 100);
        setImage(hitbox);
    
    }
    public boolean intersectWall(){
        return isTouching(Wall.class);
    }
}
