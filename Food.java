import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Food item
 * 
 * @author Edmond
 * @version January 2024
 */
public class Food extends Item
{
    /**
     * Constructor for the Food Class
     */
    public Food(){
        weight = 1;
        setIcon("itemSprites/Food.png");
    }
    /**
     * To string method to return type of item
     */
    public String toString(){
        return "Food";
    }
}
