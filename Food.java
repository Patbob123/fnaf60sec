import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Food item
 * 
 * @author Dawson
 * @version January 2024
 */
public class Food extends Item
{
    /**
     * Constructor for the Food Class
     */
    public Food(){
        weight = 1;
        icon = new GreenfootImage("grapes.png");
        setImage(icon);
    }
    /**
     * To string method to return type of item
     */
    public String toString(){
        return "Food";
    }
}
