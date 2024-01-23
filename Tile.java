import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * Super class to create behaviours for all the tiles to be placed on the map and world
 * 
 * @author Dawson
 * @version January 2024
 */
public abstract class Tile extends SuperSmoothMover
{
    protected int width;
    protected int height;
    protected String symbol;
    
    /**
     * Constructor for the Tile 
     */
    public Tile(){
        this.width = Constants.TILE_LEN;
        this.height = Constants.TILE_LEN;
        
        GreenfootImage temp = new GreenfootImage(width,height);
        temp.drawRect(0,0,width,height);
    }
    /**
     * Set the image of the Tile
     * 
     * @param imageUrl      The string url of the image file
     */
    public void setIcon(String imageUrl){
        GreenfootImage icon = new GreenfootImage(imageUrl);
        icon.scale(32, 32);
        Util.scale(icon);
        setImage(icon);
    }
    /**
     * To string method to return type of tile
     */
    public String toString(){
        return symbol;
    }
}
