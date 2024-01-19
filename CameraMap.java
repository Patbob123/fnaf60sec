import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CameraMap extends Actor
{
    /**
     * Display the map of the cameras
     * Collapsable
     * Set of buttons
     * Background image
     * Once touched the map button, display the bg and add all the other buttons
     */
    private static GreenfootImage camMap;
    public void act()
    {
        camMap = new GreenfootImage("cameraMap.png");
        camMap.scale((int)(camMap.getWidth()/4), (int)(camMap.getHeight()/4));
        setImage(camMap);
    }
}
