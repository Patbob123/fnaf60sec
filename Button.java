import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private String buttonText;
    private int textSize;
    private Button plusButton;
    private Button minusButton;
    private String buttonType;
    private int r;
    private int b;
    private int g;
    private double a;
    private boolean expanded = false;

    /**
     * Construct a TextButton given only a String.
     * 
     * @param text       String to be displayed on the button.
     * @param textSize   Size of the text.
     * @param buttonType Type of the button, e.g., "plus" or "minus".
     */
    public Button(String text, int textSize, String buttonType) {
        this(text, 20);
        if ("plus".equals(buttonType)) {
            plusButton = new Button("+", textSize);
            getWorld().addObject(plusButton, getX() + getImage().getWidth() + 20, getY());
        } else if ("minus".equals(buttonType)) {
            minusButton = new Button("-", textSize);
            getWorld().addObject(minusButton, getX() - minusButton.getImage().getWidth() - 20, getY());
        }
    }

    /**
     * Construct a TextButton given a String and a text size.
     * 
     * @param text     String to be displayed on the button.
     * @param textSize Size of the text.
     */
    public Button(String text, int textSize) {
        // Assign value to my internal String
        buttonText = text;
        this.textSize = textSize;

        // Draw a button with centered text:
        updateMe(text);
    }

    /**
     * Act - do whatever the TextButton wants to do.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (Greenfoot.mousePressed(this)) {
            // Handle click events for TextButton
            //setImage(myAltImage);
        } else if ("plus".equals(buttonType) && Greenfoot.mousePressed(plusButton)) {
            // Handle click events for the plus button
            // Execute corresponding operations
        } else if ("minus".equals(buttonType) && Greenfoot.mousePressed(minusButton)) {
            // Handle click events for the minus button
            // Execute corresponding operations
        } else {
            //setImage(myImage);
        }
    }

    /**
     * Update current TextButton text.
     * 
     * @param text New text to be displayed on the button.
     */
    public void updateMe(String text) {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage(text, textSize, new Color(51, 4, 4), Color.WHITE);
        myImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myImage.setColor(Color.WHITE);
        myImage.fill();
        myImage.drawImage(tempTextImage, 4, 4);

        myImage.setColor(new Color(51, 4, 4));
        myImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
        setImage(myImage);

        tempTextImage = new GreenfootImage(text, textSize, Color.WHITE, Color.BLACK);
        myAltImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myAltImage.setColor(Color.WHITE);
        myAltImage.fill();
        myAltImage.drawImage(tempTextImage, 4, 4);

        myAltImage.setColor(new Color(117, 11, 11));
        myAltImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
    }

    public void switchExpansion(int r, int g, int b, double a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        if (!expanded){
            collapseAllOtherCameras();
        }
        expanded = !expanded;
        updateImage(r, g, b, a);
    }

    private void collapseAllOtherCameras(){
        World world = getWorld();
        if (world != null){
            List<Button> camButtons = world.getObjects(Button.class);
            for (Button otherCam : camButtons){
                if (otherCam != this && otherCam.isExpanded()){
                    otherCam.switchExpansion(51, 4, 4, 0.0);
                }
            }
        }
    }

    public void updateImage(int r, int g, int b, double a) {
        
        

        // myImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        // //myImage.setColor(new Color(r, g, b, 10));
        // myImage.setColor(new Color(r, g, b, 140));
        // myImage.fill();
        // myImage.drawImage(tempTextImage, 4, 4);
        // myImage.setColor(new Color(51, 4, 4));
        // myImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
        // //setImage(myImage);

        // //tempTextImage = new GreenfootImage(buttonText, textSize, Color.WHITE, Color.BLACK);

        // myAltImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        // myAltImage.setColor(new Color(r, g, b, 140));
        // myAltImage.fill();
        // myAltImage.drawImage(tempTextImage, 4, 4);
        // //setImage(myImage);

        if (!expanded){
            GreenfootImage tempTextImage = new GreenfootImage (buttonText, textSize, new Color(51, 4, 4), Color.WHITE);
            myImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
            
            myImage.setColor(Color.WHITE);
            myImage.fill(); 
            myImage.drawImage(tempTextImage, 4, 4);
            
            myImage.setColor(new Color(51, 4, 4));
            myImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
            setImage(myImage);
        }else{
            GreenfootImage tempAltImage = new GreenfootImage(buttonText, textSize, new Color(51, 4, 4), new Color(r, g, b, 60));
            myAltImage = new GreenfootImage(tempAltImage.getWidth() + 8, tempAltImage.getHeight() + 8);
            
            myAltImage.setColor(new Color(r, g, b, 160));
            myAltImage.fill();
            myAltImage.drawImage(tempAltImage, 4, 4);
            
            myAltImage.setColor(new Color(51, 4, 4));
            myAltImage.drawRect(0, 0, tempAltImage.getWidth() + 7, tempAltImage.getHeight() + 7);
            setImage(myAltImage);
        }
        //setImage(expanded ? myAltImage : myImage);
    }

    public boolean isExpanded(){
        return expanded;
    }

    public String getCameraName(){
        return buttonText;
    }
}
