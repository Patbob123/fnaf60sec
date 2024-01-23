import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A helper class to make icons fade away for visual effects 
 * 
 * @author Vincent  
 * @version January 2024
 */
public class PopupFader extends Effect
{
    private int fadeDuration;
    private int maxDuration;
    private boolean reverse;
    private GreenfootImage popupImage;
    /**
     * Constructor for PopupFader
     * 
     * @param image         The image of the icon
     * @param maxDuration   The duration of the fade out
     * @param reverse       Variable to indicate if it is fade in
     */
    public PopupFader(GreenfootImage image,int maxDuration, boolean reverse){
        this.maxDuration = maxDuration;
        this.reverse = reverse;
        fadeDuration = reverse ? maxDuration: 0;
        
        popupImage = new GreenfootImage(image.getWidth(), image.getHeight());
        popupImage.drawImage(image, 0, 0);
        popupImage.scale(40,40);
        popupImage.setTransparency(255-(int)(((double)fadeDuration/maxDuration)*255));
        setImage(popupImage);
    }
    public void act() 
    {
       // Check whether or not to fade in or fade out
        fadeDuration += reverse ? -1 : 1;
        
        if(fadeDuration > maxDuration || fadeDuration < 0){
            getWorld().removeObject(this);
            return;
        }
        getImage().setTransparency(255-(int)(((double)fadeDuration/maxDuration)*255));
        
        setLocation(getX(),getY() -2);
    }
    /**
     * Getter method for Max Duration of the Fader
     */
    public int getMaxDuration(){
        return maxDuration;
    }
}
