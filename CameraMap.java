import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Camera Map Displays the visuals
 * 
 * @author Jennifer
 * @version January 2024
 */
public class CameraMap extends Actor
{
    private static GreenfootImage camMap;
    /**
     * Constructor for only the background of the main camera system
     * 
     * @param schoolMap     File name of school map
     */
    public CameraMap (String schoolMap) {
        camMap = new GreenfootImage("translucentCamMapV2.PNG");
        camMap.scale((int)(camMap.getWidth()/2), (int)(camMap.getHeight()/2));
        setImage(camMap);
    }
}

