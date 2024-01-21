import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Battery here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battery extends Item
{
    private int power;
    public Battery(){
        weight = 1;
        power = 10;
        icon = new GreenfootImage("batteryIcon.png");
        setImage(icon);
    }
    public void act()
    {
        // Add your action code here.
    }
}
