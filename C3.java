import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class C3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class C3 extends Grid
{
    Player fisho;
    PressurePlate left;
    public C3()
    {
        super();
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        left = new PressurePlate(50,50);
        addObject(left, 0, 384);
        System.out.println(map.getX());
    }
    public void setCoord(){
        map.setPointer("C3");
        map.setX(2);
        map.setY(2);
    }
    public void act(){
        if(fisho.touchP(left)){
            exit("left");
        }
    }
}
