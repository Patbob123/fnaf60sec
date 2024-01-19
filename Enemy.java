import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.ArrayList;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private PriorityQueue<Coordinate> nextMoves;

    public Enemy(){
        collider = new Hitbox(getImage().getWidth(),getImage().getHeight());
    }
    protected void addedToWorld(World world){
        world.addObject(collider, getX(), getY());
    }
    public void act() 
    {
            bfsTowards();
        
        
    }    
    
    public boolean checkWall(int x, int y){
        return collider.intersectWall(x,y);
    }
    public void bfsTowards(){
        ArrayList<Coordinate> checked = new ArrayList<>();
        nextMoves = new PriorityQueue<Coordinate>();
        nextMoves.add(new Coordinate(0, 0));
        while(!nextMoves.isEmpty()){
            Coordinate c = nextMoves.poll();
            if(getX()+c.seeX()<0||getX()+c.seeX()>Constants.WW) continue;
            if(getY()+c.seeY()<0||getY()+c.seeY()>Constants.WH) continue;
            if(checkWall(c.seeX(), c.seeY())){
                System.out.println(checked);
                System.out.println(getX()+c.seeX()+" "+ getY()+c.seeY());
                continue;
            }
            if(((tempWorld)getWorld()).checkPlayer(getX()+c.seeX(), getY()+c.seeY())){
                System.out.println("ASDASD");
                setLocation(getX()+checked.get(1).seeX(), getY()+checked.get(1).seeY());
                break;
            }
            if(!containCoord(checked, c)){
                checked.add(c);
                nextMoves.add(new Coordinate(c.seeX()+10, c.seeY()));
                nextMoves.add(new Coordinate(c.seeX(), c.seeY()+10));
                nextMoves.add(new Coordinate(c.seeX()-10, c.seeY()));
                nextMoves.add(new Coordinate(c.seeX(), c.seeY()-10));
            }
        }
    }
    public boolean containCoord(ArrayList<Coordinate> checked, Coordinate c){
        for(Coordinate cur:checked){
            if(c.seeX()==cur.seeX()&&c.seeY()==cur.seeY()) return true;
        }
        return false;
    }
}
