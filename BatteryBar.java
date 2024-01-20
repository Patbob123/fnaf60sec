import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BatteryBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatteryBar extends Actor
{
    /**
     * Constructor for BatteryBar
     */
    public BatteryBar(int startBattery) {
        //the battery you get is the width of the bar, so max width
        //would be 100 px as the max battery you can get 100
        GreenfootImage barImage = new GreenfootImage(startBattery, 20);
        barImage.setColor(Color.GREEN); 
        barImage.fill(); 
        setImage(barImage);
    }

    /**
     * Method to update the battery status during gameplay
     * 
     * @param battery       The amount of battery the player collects during phase 1
     * @param maxBattery    The max amount of battery a player can have in the game
     */
    public void updateBar(int barWidth) {
        GreenfootImage barImage = getImage();
        barImage.clear();
        barImage.fillRect(0, 0, barWidth, barImage.getHeight());
    }
}
