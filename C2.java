import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class C2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class C2 extends Grid
{
    Player fisho;
    PressurePlate left;
    PressurePlate right;
    public C2()
    {
        super();
        setCoord();
        fisho = new Player();
        addObject(fisho,0,0);
        right = new PressurePlate(50,50);
        addObject(right, 1152, 384);
        left = new PressurePlate(50,50);
        addObject(left, 0, 384);
        System.out.println(map.getX());
    }
    public void act(){
        if(fisho.touchP(right)){
            exit("right");
        }
        else if(fisho.touchP(left)){
            exit("left");
        }
    }
    public void setCoord(){
        map.setPointer("C2");
        map.setX(1);
        map.setY(2);
    }
}
