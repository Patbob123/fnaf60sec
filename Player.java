import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{   
    private Hitbox collider; 
    public Player(){
        collider = new Hitbox(getImage().getWidth(),getImage().getHeight()) ;
    }
    protected void addedToWorld(World world){
        world.addObject(collider, getX(), getY());
    }
    double speed = 10;
    private void move(){
        double playerWidth = getImage().getWidth()/2;
        double playerHeight = getImage().getHeight()/2;
        if(Greenfoot.isKeyDown("a") && !checkWall(-playerWidth - speed,0)){
            ((Crossroads)getWorld()).move(speed, 0);
            setLocation(getX()-speed,getY());
        }
        if(Greenfoot.isKeyDown("d") && !checkWall(playerWidth + speed,0)){
            ((Crossroads)getWorld()).move(-speed, 0);
            setLocation(getX()+speed,getY());
        }
        if(Greenfoot.isKeyDown("w") && !checkWall(0,- playerHeight - speed)){
            ((Crossroads)getWorld()).move(0, speed);
            setLocation(getX(),getY()-speed);
        }
        if(Greenfoot.isKeyDown("s") && !checkWall(0, playerHeight + speed)){
            ((Crossroads)getWorld()).move(0, -speed);
            setLocation(getX(),getY()+speed);
        }
    }   
    public boolean checkWall(){
        return collider.intersectWall();
    }
    public void act()
    {
        move();
    }
}
