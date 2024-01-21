import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Items here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Tile
{
    protected int weight;
    protected GreenfootImage icon;
    public Item(){
        symbol = "$";
    }
    public void act()
    {
        // Add your action code here.
    }
    public int getWeight(){
        return weight;
    }
}
