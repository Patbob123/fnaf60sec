import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Item class
 * 
 * @author Edmond
 * @version January 2024
 */
public class Item extends Tile
{
    protected int weight;
    protected GreenfootImage icon;
    /**
     * Constructor for the Item Class
     */
    public Item(){
        symbol = "$";
    }
    /**
     * Getter Method for weight of item
     */
    public int getWeight(){
        return weight;
    }
    /**
     * To string method to return type of item
     */
    public String toString(){
        return "SOME ITEM";
    }
}
