import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends SuperSmoothMover
{
    private GreenfootImage[] walk;
    private GreenfootImage[] stand;
    /**
     * Act - do whatever the Entity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int index, count = 0;
    private final int COUNT_NUM = 7;
    public void act() 
    {
        // Add your action code here.
    } 
    /**
     * Constructor for the Entity class.
     * @param walk Array of walking animation frames.
     * @param stand Array of standing animation frames.
     */
    public Entity(GreenfootImage[] walk, GreenfootImage[] stand){
        this.walk = walk;
        this.stand = stand;
    }
    
    /**
     * Prepares animation frames from image files
     * 
     * @param imgs Array to store the animationn frames
     * @param frameName The base name of the image files
     */
    public void prepareAnimation(GreenfootImage[] imgs, String frameName){
        for (int i = 0; i < imgs.length; i++){
            imgs[i] = new GreenfootImage(frameName + i + " .png");
        }
    }
    
    /**
     * Prepares the scaled animation frames from image files
     * 
     * @param imgs Array to store the animationn frames
     * @param frameName The base name of the image files
     * @param width The width to scale the image to
     * @param height The height to scale the image to
     */
    public void prepareAnimation(GreenfootImage[] imgs, String frameName, int width, int height){
        for (int i = 0; i < imgs.length; i++){
            imgs[i] = new GreenfootImage(frameName + i + " .png");
            imgs[i].scale(width, height);
        }
    }
    
    public void animate(GreenfootImage[] imgs){
        if (index < imgs.length){
            if (count == COUNT_NUM){
                setImage(imgs[index]);
                index++;
                count = 0;
            }else{
                count++;
            }
        }else{
            index = 0;
        }
    }
}
