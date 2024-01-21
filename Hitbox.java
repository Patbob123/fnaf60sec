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
        int curX = getX();
        int curY = getY();
        boolean touchingWall = false;
        
        setLocation(getX()+x, getY()+y);
        if(isTouching(Wall.class)) touchingWall = true;
        setLocation(getX()+width*Math.signum(x), getY()+10);
        if(!isTouching(Wall.class)) touchingWall = false;
        setLocation(getX()+width*Math.signum(x), getY()-20);
        if(!isTouching(Wall.class)) touchingWall = false;
        
        setLocation(curX, curY);
        return touchingWall;
    }
    public boolean intersect(Class c){
        return isTouching(c);
    }
}
