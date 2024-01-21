import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display Enemy Hp Bars and periodically updates the values
 * 
 * @author Dawson 
 * @version November 2023
 */
public class Bar extends Actor
{
    private GreenfootImage barImage;
    private GreenfootImage iconImage;
    private GreenfootImage barUIImage;
    private double maxNum;
    private Color col;
    
    /**
     * Constructor for the User's Health Bar
     * 
     * @param uc        User character
     */
    public Bar(double maxNum, String iconUrl, Color col){
        barImage = new GreenfootImage("bar.png");
        iconImage = new GreenfootImage(iconUrl);
        this.col = col;
        this.maxNum = maxNum;
        
        Util.scale(barImage);
        Util.scale(iconImage);
        
        refresh(maxNum);
    }
    
    /**
     * Method for visuals to match the Entity's current HP values
     */
    public void refresh(double curNum){
        if(curNum <= 0) return;
        barUIImage = new GreenfootImage(64, 16);
        
        Util.scale(barUIImage);
        barUIImage.setColor(col);
        barUIImage.fillRect(17*Constants.SCALE, 9*Constants.SCALE, (int)(43*Constants.SCALE*(curNum/maxNum)), 5*Constants.SCALE);
        
        
        barUIImage.drawImage(iconImage, 0, 0);
        barUIImage.drawImage(barImage, 0, 0);
        
        
        
        setImage(barUIImage);
    }
}