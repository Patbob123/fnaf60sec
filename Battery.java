import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Battery item
 * 
 * @author Edmond
 * @version January 2024
 */
public class Battery extends Item
{
    private int power;
    /**
     * Constructor for the Battery Class
     */
    public Battery(){
        weight = 2;
        power = 10;
        setIcon("itemSprites/Battery.png");
    }
    /**
     * To string method to return type of item
     */
    public String toString(){
        return "Battery";
    }
}
