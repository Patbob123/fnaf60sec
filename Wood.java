import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Wood item
 * 
 * @author Edmond
 * @version January 2024
 */
public class Wood extends Item
{
    /**
     * Constructor for the Wood Class
     */
    public Wood (){
        weight = 3;
        setIcon("itemSprites/Wood.png");
    }
    /**
     * To string method to return type of item
     */
    public String toString(){
        return "Wood";
    }

}
