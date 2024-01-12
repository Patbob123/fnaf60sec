import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{   
    int speed = 10;
    private void move(){
        if(Greenfoot.isKeyDown("a")){
            setLocation(getX()-speed,getY());
        }
        if(Greenfoot.isKeyDown("d")){
            setLocation(getX()+speed,getY());
        }
        if(Greenfoot.isKeyDown("w")){
            setLocation(getX(),getY()-speed);
        }
        if(Greenfoot.isKeyDown("s")){
            setLocation(getX(),getY()+speed);
        }
    }
    public void act()
    {
        move();
    }
}
