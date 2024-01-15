import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class B1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class B3 extends Grid
{
    public B3()
    {
        super();
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        left = new PressurePlate(50,50);
        addObject(left, 0, 384);
    }
    public void setCoord(){
        map.setPointer("B3");
        map.setX(2);
        map.setY(1);
    }
    public void act(){
        if(fisho.touchP(left)){
            map.loadWorld("B2");
        }
    }
}
