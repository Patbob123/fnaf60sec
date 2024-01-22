import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PopupFader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PopupFader extends Effect
{
    private int fadeDuration;
    private int maxDuration;
    private boolean reverse;
    private GreenfootImage popupImage;
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
    public int getMaxDuration(){
        return maxDuration;
    }
}
