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
    private ArrayList<Player> players;
    private Player targetPlayer;
    /**
     * Three enemies
     * 1. Z path
     * 2. smoothly & silently follow the player, then suddennly teleport too the front of the player
     * 3. go towards the player by teleporting intermittently
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
        
    }    
    
    private void targetClosestPlayer(){
        double closestTargetDis = 0;
        double distanceToActor;
        players = (ArrayList<Player>)getObjectsInRange (70, Player.class);
        
        if (players.size() == 0){
            players = (ArrayList<Player>)getObjectsInRange(170, Player.class);
        }
        
        if (players.size() > 0){
            targetPlayer = players.get(0);
            closestTargetDis = getDistance(this, targetPlayer);
            
            for (Player o : players){
                distanceToActor = getDistance(this, o);
                if (distanceToActor < closestTargetDistance){
                    targetPlayer = o;
                    closestTargetDis = distanceToActor;
                }
            }
            setLocation(targetPlayer.getX(), targetPlayer.getY());
            
        }
    }
}
