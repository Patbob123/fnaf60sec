import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ResourceScramble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResourceScramble extends Tile
{
    private Inventory bunker;
    public ResourceScramble(int x, int y){
        super(x,y);
        bunker = new Inventory();
    }
    public Inventory getInventory(){
        return bunker;
    }
}
