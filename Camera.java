import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Camera here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Camera extends Actor
{
    private GreenfootImage cam;
    /**
     * Constructor for the singular cameras (cameras 1-7)
     * 
     * @param stage             The specific camera an enemy is at
     * @param enemyExists       If the enemy is at that camera
     * @param cameraFile        The file directory for the image for the camera
     */
    public Camera(int stage, boolean enemyExists, String cameraFile) {
        if(stage == 1) {
            if(!enemyExists) {
                cam = new GreenfootImage(cameraFile);
                cam.scale(cam.getWidth(), cam.getHeight());
                setImage(cam);
            }
            if(enemyExists) {
                cam = new GreenfootImage(cameraFile);
                cam.scale(cam.getWidth(), cam.getHeight());
                setImage(cam);
            }
        }
        else if(stage == 2) {
            if(!enemyExists) {
                cam = new GreenfootImage(cameraFile);
                cam.scale(cam.getWidth(), cam.getHeight());
                setImage(cam);
            }
            if(enemyExists) {
                cam = new GreenfootImage(cameraFile);
                cam.scale(cam.getWidth(), cam.getHeight());
                setImage(cam);
            }
        }
        else if(stage == 3) {
            if(!enemyExists) {
                cam = new GreenfootImage(cameraFile);
                cam.scale(cam.getWidth(), cam.getHeight());
                setImage(cam);
            }
            if(enemyExists) {
                cam = new GreenfootImage(cameraFile);
                cam.scale(cam.getWidth(), cam.getHeight());
                setImage(cam);
            }
        }
    }    
}
