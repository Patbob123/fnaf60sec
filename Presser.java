import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Button class that runs its Function object's function when clicked by the mouse
 * 
 * @author Dawson
 * @version 2023-06-20
 */

public class Presser extends Actor
{
    private int startX;
    private int startY;
    
    private Function action;
    private GreenfootImage buttonImage;
    private GreenfootImage hoverButtonImage;
    
    private boolean hovering;
    

    
    /**
     * Constructor for Presser
     * @param buttonAction       The Function object that will run
     * @param buttonFile         The image of the button's directory
     * @param hoverButtonFile    The image of the button's hover directory
     */
    public Presser(Function buttonAction, String buttonFile, String hoverButtonFile){
        buttonImage = new GreenfootImage(buttonFile);
        hoverButtonImage = new GreenfootImage(hoverButtonFile);
        Util.scale(buttonImage);
        Util.scale(hoverButtonImage);   
        setImage(buttonImage);
        action = buttonAction;
    }
    
    /**
     * Constructor for Presser
     * @param buttonAction       The Function object that will run
     * @param buttonFile         The image of the button
     */
    public Presser(Function buttonAction, GreenfootImage buttonImage){
        setImage(buttonImage);
        action = buttonAction;
    }

    
    /**
     * Act method
     * For clicking and hovering
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            ((SuperWorld)getWorld()).getSM().playSound("click");
            runAction();
        }
        detectHover();
    }
    
    /**
     * Mutator for X and Y
     * 
     * @param w           World the button is added to
     */
    protected void addedToWorld(World w){
        startX = getX();
        startY = getY();
    }
    
    /**
     * Running the Function object's function
     */
    public void runAction() {
        if(action != null) action.apply();
    }
    
    /**
     * Method to set image of button
     * 
     * @param image         Image of button
     */
    public void setButtonImage(GreenfootImage image){
        buttonImage = image;
        hoverButtonImage = image;
        setImage(image);
    }
    /**
     * Checks if the mouse is hovering over the button, if so, change button image for visual effect
     */
    private void detectHover(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null&&hoverButtonImage!=null&&getWorld()!=null){
            List hovering = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Presser.class);
            if(hovering.contains(this)){
                setImage(hoverButtonImage); //Moves the button up
            }else{
                setImage(buttonImage); //Moves the button back to starting position
            }
        }
        if (Greenfoot.mouseMoved(null)) 
        {
            if (hovering != Greenfoot.mouseMoved(this)) 
            {
                hovering = !hovering; 
                if (hovering) 
                {
                    ((SuperWorld)getWorld()).getSM().playSound("hover");
                }
            }
        }
    }
}