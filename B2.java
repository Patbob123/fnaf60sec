import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class B2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class B2 extends Grid
{

    Player fisho;
    PressurePlate left;
    PressurePlate right;
    public B2()
    {
        super();
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        left = new PressurePlate(50,50);
        addObject(left, 0, 384);
        right = new PressurePlate(50,50);
        addObject(right, 1152, 384);
    }
    public void setCoord(){
        map.setPointer("B2");
        map.setX(1);
        map.setY(1);
    }
    public void act(){
        if(fisho.touchP(left)){
            map.loadWorld("B1");
        }
        else if(fisho.touchP(right)){
            map.loadWorld("B3");
        }
    }
}
