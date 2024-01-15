import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Grid extends World
{
    Player fisho;
    PressurePlate left;
    PressurePlate right;
    protected Map map = new Map();
    public Grid()
    {    
        super(1152, 768, 1);
    }
}
