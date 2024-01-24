import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimatedPopup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimatedPopup extends Effect
{
    private SimpleTimer animTimer; 
    private String spriteSheetUrl = "GO/go.png";
    private GreenfootImage[] curFrames;
    private int curFrame;
    
    public AnimatedPopup(){
        animTimer = new SimpleTimer();
        curFrames = new GreenfootImage[30];
        loadSprites(curFrames);
    }
    public void act()
    {
        animate();
    }
    /**
     * Method to construct and load the animation sprites of the player
     * 
     * @param frames    The 2d array of GreenfootImage animation frames
     * @param startY    Y-offset variable
     */
    public void loadSprites(GreenfootImage[] frames){
        for(int i = 0; i < frames.length; i++){
                GreenfootImage image = new GreenfootImage(spriteSheetUrl);
                GreenfootImage cropimage = new GreenfootImage(16, 16);
                cropimage.drawImage(image, -16+i*16, -16);
                scaleIcon(cropimage);
                frames[i] = cropimage;
        }
    }
    /**
     * Helper method to scale Icons
     * 
     * @param icon      The GreenfootImage of the icon
     */
    public void scaleIcon(GreenfootImage icon){
        icon.scale(32, 52);
        Util.scale(icon);
    }
    /**
     * Display animations and iterate through the  array of frames
     */
    public void animate(){
        if(animTimer.millisElapsed() >= Constants.ANIM_SPEED){
            curFrame++;
            if(curFrames.length <= curFrame){
                curFrame = 0;
            }
            setImage(curFrames[curFrame]);
            animTimer.mark();
        }
    }
}
