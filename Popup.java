import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PopupFader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Popup extends Effect
{
    private GreenfootImage popupImage;
    
    public Popup(GreenfootImage image){
        popupImage = new GreenfootImage(image.getWidth(), image.getHeight());
        popupImage.drawImage(image, 0, 0);
        popupImage.scale(60,60);
        setImage(popupImage);
    }

}
