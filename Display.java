import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Display here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Display extends Actor
{
    private GreenfootImage image;
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
