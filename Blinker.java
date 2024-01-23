import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class to give player (Timmy) idle animation of blinking
 * 
 * @author Dawson 
 * @version January 2024
 */
public class Blinker extends Entity
{
    private GreenfootImage blinkerImage;
    private Timer timer;
    
    /**
     * Constructor for Blinker Class
     */
    public Blinker(){
        blinkerImage = new GreenfootImage("purpletimmysprites.png");
        timer = new Timer(10.0, true);
    }
    
    /**
     * Method to add the animations to the world
     * 
     * @param w     Greenfoot world to add object to
     */
    public void addedToWorld(World w){
        w.addObject(timer, 0, 0);
    }
    public void act()
    {
        if(timer.getTime() > 0){
            getWorld().removeObject(timer);
            getWorld().removeObject(this);
        }
    }
}
