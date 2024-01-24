import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Buttons here.
 * 
 * @author Jennifer
 * @version January 2024
 */
public class Button extends Actor
{
    private GreenfootImage myImage;
    private GreenfootImage myAltImage;
    private Button plusButton;
    private Button minusButton;
    private String buttonType;
    private String buttonText;
    private int buttonNumber;
    private int textSize;
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
        //updateMe(text);
        updateImage(12, 12, 12, 12);
    }
    
    public Button(String text, int textSize, boolean bottomButton) {
        // Assign value to my internal String
        buttonText = text;
        this.textSize = textSize;

        // Draw a button with centered text:
        if (bottomButton){
            updateMe(text);
        } 
    }
    
    public Button(String text, int textSize, boolean bottomButton, int buttonNumber) {
        // Assign value to my internal String
        buttonText = text;
        this.textSize = textSize;
        this.buttonNumber = buttonNumber;

        // Draw a button with centered text:
        if (bottomButton){
            updateMe(text);
        } 
    }
    
    /**
     * Get button number
     */
    public int getButNum() {
        return buttonNumber;
    }
    
    /**
     * Act - do whatever the TextButton wants to do.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {

    }

    /**
     * Update current TextButton text.
     * 
     * @param text New text to be displayed on the button.
     */
    public void updateMe(String text) {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage(text, textSize, Color.WHITE, new Color(0,0,0,0));
        myImage = new GreenfootImage(tempTextImage.getWidth(), tempTextImage.getHeight() + 2); //8
        myImage.setColor(new Color(0,0,0,0));
        myImage.setFont(new Font (text, true, false, 20));
        myImage.fill();
        myImage.drawImage(tempTextImage, 4,4); //4
        myImage.setColor(Color.WHITE);
        myImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 1); //7
        setImage(myImage);

        tempTextImage = new GreenfootImage(text, textSize, Color.WHITE, Color.WHITE);
        myAltImage = new GreenfootImage(tempTextImage.getWidth(), tempTextImage.getHeight() + 2);
        myAltImage.setFont(new Font (text, true, false, 20));
        myAltImage.setColor(new Color(0,0,0,0));
        myAltImage.fill();
        myAltImage.drawImage(tempTextImage,4, 4);
        myAltImage.setColor(new Color(117, 11, 11));
        myAltImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 1); //8
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
        
        // GreenfootImage tempTextImage = new GreenfootImage (buttonText, textSize, Color.WHITE, new Color(0,0,0,0));
            // myImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);

            // myImage.setFont(new Font (buttonText, true, false, 20));
            // myImage.setColor(new Color(0,0,0,0));
            // myImage.fill(); 
            // myImage.drawImage(tempTextImage, 4, 4);

            // myImage.setColor(Color.WHITE);
            // myImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
            // setImage(myImage);

        if (!expanded){
            GreenfootImage tempTextImage = new GreenfootImage (buttonText, textSize, Color.WHITE, new Color(0,0,0,0));
            myImage = new GreenfootImage(tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);

            myImage.setFont(new Font (buttonText, true, false, 20));
            myImage.setColor(Color.RED);
            myImage.fill(); 
            myImage.drawImage(tempTextImage, 4, 4);

            myImage.setColor(Color.WHITE);
            myImage.drawRect(0, 0, tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
            setImage(myImage);
        }else{
            GreenfootImage tempAltImage = new GreenfootImage(buttonText, textSize, Color.WHITE, new Color(r, g, b, 60));
            myAltImage = new GreenfootImage(tempAltImage.getWidth() + 8, tempAltImage.getHeight() + 8);

            myAltImage.setFont(new Font (buttonText, true, false, 20));
            myAltImage.setColor(Color.RED);
            myAltImage.fill();
            myAltImage.drawImage(tempAltImage, 4, 4);

            myAltImage.setColor(Color.WHITE);
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
