import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class C1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class C1 extends Grid
{
    public C1()
    {
        super();
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        right = new PressurePlate(50,50);
        addObject(right, 1152, 384);
        
    }
    public void act(){
        if(fisho.touchP(right)){
            map.loadWorld("C2");
        }
    }
    public void setCoord(){
        map.setPointer("C1");
        map.setX(0);
        map.setY(2);
    }
}
