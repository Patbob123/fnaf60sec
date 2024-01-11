import greenfoot.*;

public class Util  
{
    // instance variables - replace the example below with your own
    public static void scale (GreenfootImage image){
        image.scale(image.getWidth()*Constants.scale, image.getHeight()*Constants.scale);
    }
}
