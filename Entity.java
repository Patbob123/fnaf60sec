import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Super class of all entites
 * 
 * @author Jennifer
 * @version Jan 2024
 */
public class Entity extends SuperSmoothMover
{
    protected Hitbox collider;
    private SimpleTimer timer;
    protected boolean p2;
    public static double getDistance (Actor a, Actor b){
        double distanceBetween = Math.hypot (Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
        return distanceBetween;
    }
}
