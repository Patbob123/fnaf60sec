import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class def here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class def extends World
{
    Player fisho;
    World toWorld;
    Aaa test;
    /**
     * Constructor for objects of class def.
     * 
     */
    public def(World inWorld, Aaa test)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        fisho = new Player();
        addObject(fisho,0,0);
        toWorld = inWorld;
        this.test = test;
    }
    public void act(){
        if (Greenfoot.mouseClicked(null)){
            World world = test.getHashMap().get("abc");
            Greenfoot.setWorld(world);
        }
        else if (Greenfoot.isKeyDown("b")){
            if(!test.check("ghf")){
                Greenfoot.setWorld(new ghf(test));
            }
        }
    }
}
