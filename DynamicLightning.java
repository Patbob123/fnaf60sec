import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VisionBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DynamicLightning extends Effect
{
    /**
     * Act - do whatever the VisionBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int width;
    private int height;
    private Color fading;
    private GreenfootImage dynamicLighting;
    
    public DynamicLightning(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        dynamicLighting = new GreenfootImage(width, height);
    }
    
    public void refresh(int visionTime){
        fading = new Color(34, 24, 59);
        dynamicLighting.setColor(fading);
        dynamicLighting.fill();
        System.out.println(visionTime);
        dynamicLighting.setTransparency(visionTime);
        setImage(dynamicLighting);
    }
}
