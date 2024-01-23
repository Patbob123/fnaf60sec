import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class to darken the colour of the screen
 * 
 * @author Jennifer
 * @version January 2024
 */
public class DynamicLightning extends Effect
{
    private int width;
    private int height;
    private Color fading;
    private GreenfootImage dynamicLighting;
    
    /**
     * Constructor for DynamicLighting
     * 
     * @param width     height of the world
     * @param height    width of the world
     */
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
        dynamicLighting.setTransparency(visionTime);
        setImage(dynamicLighting);
    }
}