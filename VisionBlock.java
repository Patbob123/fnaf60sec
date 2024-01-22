import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VisionBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VisionBlock extends Effect
{
    /**
     * Act - do whatever the VisionBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int width;
    private int height;
    private Color fading;
    public VisionBlock(int width, int height, int visionTime)
    {
        this.width = width;
        this.height = height;
        //fading = new Color(34, 24, 59, visionTime);
        fading = new Color(244, 228, 12);
        
        GreenfootImage visionBlock = new GreenfootImage(width, height);
        visionBlock.setColor(fading);
        visionBlock.fill();
        visionBlock.setTransparency(10000);
        setImage(visionBlock);
    
    }
}
