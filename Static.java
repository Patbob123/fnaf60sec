import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Create an animated static overlay effect on screen
 * 
 * @author Dawson
 * @version January 2024
 */
public class Static extends Effect
{
    private int acts;
    private int actsPerFrame;
    private int frames;
    private int curFrame;
    private GreenfootImage[] staticImages;
    
    /**
     * Constructor for Static
     */
    public Static() {
        frames = 5;
        curFrame = 0;
        acts = 0;
        actsPerFrame = 2;
        
        staticImages = new GreenfootImage[frames];
        for(int i = 0; i < frames; i++){
            staticImages[i] = new GreenfootImage("static/static"+(i+1)+".png");
            Util.scale(staticImages[i]);
        }

        
        setImage(staticImages[curFrame]);
    }
    
    /**
     * Play frames to simulate animation
     */
    public void act() {
        acts++;
        
        //Updates the frames of the image
        if(acts >= actsPerFrame){
            acts = 0;
            curFrame++;
            if(curFrame >= staticImages.length){
                curFrame= 0;
            }
            setImage(staticImages[curFrame]);
        }
        
        
    }
}