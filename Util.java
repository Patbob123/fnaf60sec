import greenfoot.*;
/**
 * Util class is a helper class to scale images
 * 
 * @author Dawson
 * @version January 2024
 */
public class Util  
{
    /**
     * Method to Scale Greenfoot Images
     * 
     * @param image     The GreehfootImage that needs to be scaled
     */
    public static void scale (GreenfootImage image){
        image.scale(image.getWidth()*Constants.SCALE, image.getHeight()*Constants.SCALE);
    }
}
