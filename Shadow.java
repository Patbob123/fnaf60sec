import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Shadow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shadow extends Effect
{
    private Hitbox lightRay;
    private GreenfootImage lightRayDetect;
    public Shadow()
    {
        GreenfootImage shadowImage = new GreenfootImage("shadow.png");
        Util.scale(shadowImage);
        setImage(shadowImage);
        
    }

    public tempWorld getW(){
        return (tempWorld)getWorld();
    }

 

}
