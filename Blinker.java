import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Class to give player (Timmy) idle animation of blinking
 * 
 * @author Dawson 
 * @version January 2024
 */
public class Blinker extends Entity
{
    private GreenfootImage blinkerImage;
    
    /**
     * Constructor for Blinker Class
     */
    public Blinker(){
        blinkerImage = new GreenfootImage("blinker.png");
        Util.scale(blinkerImage);
        setImage(blinkerImage);
    }
    public void act(){
        if(getObjectsInRange(270, Player.class).size()>0){
            ((SuperWorld)getWorld()).getSM().playSound("whispers");
            getWorld().removeObject(this);
        }
    }

}
