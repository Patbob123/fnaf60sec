import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class B1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class B1 extends Grid
{

    Player fisho;
    PressurePlate right;
    public B1()
    {
        super();
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        right = new PressurePlate(50,50);
        addObject(right, 1152, 384);
    }
    public void setCoord(){
        map.setPointer("B1");
        map.setX(0);
        map.setY(1);
    }
    public void act(){
        if(fisho.touchP(right)){
            map.loadWorld("B2");
        }
    }
}
