import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ResourceScramble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shelter extends Tile
{
    private Inventory bunker;
    public Shelter(){
        super();
        symbol = "#"; 
        bunker = new Inventory();
    }
    public Inventory getInventory(){
        return bunker;
    }
}
