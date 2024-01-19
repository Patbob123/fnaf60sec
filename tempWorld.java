import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class tempWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tempWorld extends World
{
    private Viewport vp;
    private MapArray ma;
    
    public tempWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Constants.WW, Constants.WH, 1);
        vp = new Viewport(Constants.WW,Constants.WH);
        ma = new MapArray();
        addObject(vp, 0, 0);
    }
    public void act(){
        if(Greenfoot.isKeyDown("a")){
            vp.move(-10, 0);
        }
        if(Greenfoot.isKeyDown("w")){
            vp.move(0, -10);
        } 
        if(Greenfoot.isKeyDown("s")){
            vp.move(0, 10);
        } 
        if(Greenfoot.isKeyDown("d")){
            vp.move(10, 0);
        }
    }
    public MapArray getMap(){
        return ma;
    }
}


