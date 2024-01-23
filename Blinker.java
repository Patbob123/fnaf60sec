import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blinker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blinker extends Entity
{
    private GreenfootImage blinkerImage;
    private Timer timer;
    public Blinker(){
        blinkerImage = new GreenfootImage("purpletimmysprites.png");
        timer = new Timer(10.0, true);
    }
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
