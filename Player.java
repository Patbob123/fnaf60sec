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
    public void act()
    {
        move();
        poop();
    }
    protected void addedToWorld(World world){
        world.addObject(collider, getX(), getY());
    }
    int speed = 10;
    private void move(){
        int pWidth = getImage().getWidth()/2;
        int pHeight = getImage().getHeight()/2;
        
        if(Greenfoot.isKeyDown("a") && !checkWall(-pWidth - speed,0)){
            setLocation(getX()-speed,getY());
        }
        if(Greenfoot.isKeyDown("d") && !checkWall(pWidth + speed,0)){
            setLocation(getX()+speed,getY());
        }
        if(Greenfoot.isKeyDown("w") && !checkWall(0, -pHeight - speed)){
            setLocation(getX(),getY()-speed);
        }
        if(Greenfoot.isKeyDown("s") && !checkWall(0, pHeight + speed)){
            setLocation(getX(),getY()+speed);
        }
    } 
    public void poop(){
        if(Greenfoot.isKeyDown("k")){
            getWorld().addObject(new Wall(20,20), getX(), getY());
        }
    }
    public boolean checkWall(int x, int y){
        return  collider.intersectWall(x,y);
    }
    public boolean onPressurePlate(){
        return isTouching(PressurePlate.class);
    }
    public boolean touchP(PressurePlate p){
        return intersects(p);
    }
    
}
