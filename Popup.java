import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class to make icons popup
 * 
 * @author Vincent 
 * @version January 2024
 */
public class Popup extends Effect
{
    private GreenfootImage popupImage;
    /**
     * Constructor for Popup
     */
    public Popup(GreenfootImage image){
        popupImage = new GreenfootImage(image.getWidth(), image.getHeight());
        popupImage.drawImage(image, 0, 0);
        popupImage.scale(60,60);
        setImage(popupImage);
    }
}
