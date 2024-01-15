import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class C3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class C3 extends Grid
{
    private Map map = ram.getMap();
    World toWorld; 
    public C3(Grid world,Memory ram)
    {
        super(world,ram);
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        left = new PressurePlate(50,50);
        addObject(left, 0, 384);
        
        toWorld = world;
    }
    public void setCoord(){
        map.setPointer("C3");
        map.setX(2);
        map.setY(2);
    }
    public void act(){
        if(fisho.touchP(left)){
            map.loadWorld("C2",ram);
            
            map.storeWorld("C1",this);
        }
    }
}
