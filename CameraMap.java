import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CameraMap extends Actor
{
    private GreenfootImage camMap;
    private GreenfootImage cam;
    /**
     * Constructor for only the background of the main camera system
     * 
     * @param schoolMap     File name of school map
     */
    public CameraMap (String schoolMap) {
        camMap = new GreenfootImage(schoolMap);
        camMap.scale((int)(camMap.getWidth()/2), (int)(camMap.getHeight()/2));
        setImage(camMap);
    }
    
    
}
    
