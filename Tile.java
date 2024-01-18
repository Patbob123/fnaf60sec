import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Tile extends SuperSmoothMover
{
    private int width;
    private int height;
    protected String symbol;
    
    public Tile(){
        this.width = Constants.TILE_LEN;
        this.height = Constants.TILE_LEN;
    
        
        GreenfootImage temp = new GreenfootImage(width,height);
        temp.drawRect(0,0,width,height);
        //setImage(temp);
    }
    public String toString(){
        return symbol;
    }
}
