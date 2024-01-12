import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private int width;
    private int height;
    
    public Tile(int width, int height){
        this.width = width;
        this.height = height;
        GreenfootImage temp = new GreenfootImage(width,height);
        temp.drawRect(0,0,width,height);
        //setImage(temp);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
