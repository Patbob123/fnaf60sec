import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class mapBuilder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MapBuilder extends Actor
{
    public MapBuilder(){
        GreenfootImage map = new GreenfootImage("tile-bg.png");
        Util.scale(map);
        setImage(map);
        
        String [][] mapLayout = new String [100][100];
        
    }
    public void act()
    {
        // Add your action code here.
    }
}
