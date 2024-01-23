import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * Super class to create behaviours for all the tiles to be placed on the map and world
 * 
 * @author Edmond
 * @version January 2024
 */
public abstract class Tile extends SuperSmoothMover
{
    protected int width;
    protected int height;
    protected String symbol;
    protected String prop;
    protected String url;
    
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
     * Set the Properties of the Tile
     * 
     * @param prop      property of the tile
     */
    public void assignProp(String prop){
        this.prop = prop;
    }
    /**
     * Get the Properties of the Tile
     * 
     * @return String      property of the tile
     */
    public String getProp(){
        return this.prop;
    }
    /**
     * Get the image url of the Tile
     * 
     * @return String      image url of the Tile
     */
    public String getUrl(){
        return this.url;
    }
    /**
     * To string method to return type of tile
     */
    public String toString(){
        return symbol;
    }
}
