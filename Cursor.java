import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates animation display for cursor clicks
 * 
 * @author Jaiden
 * @version January 2024
 */
public class Cursor extends Effect
{
    private int acts;
    private int actsPerFrame;
    private int frames;
    private int curFrame;
    private GreenfootImage[] cursorImages;
    
    /**
     * Constructor for Cursor
     */

    public Cursor() {
        frames = 5;
        curFrame = 0;
        acts = 0;
        actsPerFrame = 2;
        
        cursorImages = new GreenfootImage[frames];
        for(int i = 0; i < frames; i++){
            cursorImages[i] = new GreenfootImage("click/click"+(i+1)+".png");
            Util.scale(cursorImages[i]);
        }

        
        setImage(cursorImages[curFrame]);
    }
    
    /**
     * Act method
     */
    public void act() {
        acts++;
        
        //Updates the frames of the image
        if(acts >= actsPerFrame){
            acts = 0;
            curFrame++;
            if(curFrame >= cursorImages.length){
                getWorld().removeObject(this);
                return;
            }
            setImage(cursorImages[curFrame]);
        }
        
        
    }
    
    
}