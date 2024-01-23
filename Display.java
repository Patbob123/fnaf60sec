import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display class converts a greenfoot image into a object that can be placed into the world
 * 
 * @author Vincent 
 * @version January 2024
 */
public class Display extends Actor
{
    private GreenfootImage image;
    /**
     * Constructor for Display Class
     * 
     * @param images   The Greenfootimage
     */
    public Display(GreenfootImage image){
        this.image = image;
        image.scale(100,100);
        setImage(image);
    }
    public Display(GreenfootImage image, int x, int y){
        this.image = image;
        image.scale(x,y);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}
