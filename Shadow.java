import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Dark Overlay for the First Floor Gameplay to imitate a dark surrounding
 * 
 * @author Dawson 
 * @version January 2024
 */
public class Shadow extends Effect
{
    private Hitbox lightRay;
    private GreenfootImage lightRayDetect;
    /**
     * Constructor for Shadow Class
     */
    public Shadow()
    {
        GreenfootImage shadowImage = new GreenfootImage("shadow.png");
        Util.scale(shadowImage);
        setImage(shadowImage);
        
    }
    
    /**
     * Method to Reference the First Floor World
     */
    public tempWorld getW(){
        return (tempWorld)getWorld();
    }
}
