import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Battery item
 * 
 * @author Dawson
 * @version January 2024
 */
public class Battery extends Item
{
    private int power;
    /**
     * Constructor for the Battery Class
     */
    public Battery(){
        weight = 1;
        power = 10;
        icon = new GreenfootImage("batteryIcon.png");
        setImage(icon);
    }
    /**
     * To string method to return type of item
     */
    public String toString(){
        return "Battery";
    }
}
