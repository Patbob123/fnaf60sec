import greenfoot.*;
/**
 * Notes:
 * Make each night 1 minute, from 12am-6am (6mins per night)
 * Have battery percentage drain 1 every 5 seconds
 */
public class GameRoom extends World {
    private int backgroundX;
    private int backgroundSpeed;
    private Wall wall1;
    private Wall wall2;
    private boolean inCameras;
    private boolean isAlive;
    
    private int battery;
    private int maxBattery;
    
    private int actCounter;
    private int timer; 
    
    private BatteryBar batteryBar;
    
    /**
     * Constructor for GameRoom
     */
    public GameRoom() {
        super(1152, 768, 1);
        actCounter = 0;
        timer = 21600; // 6 minutes
        inCameras = false;
        GreenfootImage backgroundImage = new GreenfootImage("businessroom.png");
        backgroundX = 100; //to set at middle
        setBackground(backgroundImage);
        updateBackgroundPosition();
<<<<<<< Updated upstream

        wall1 = new Wall();
        wall2 = new Wall();
        
        maxBattery = 100;
        battery = 50;
        
        batteryBar = new BatteryBar(battery);
        addObject(batteryBar, 1101, 27);
        
=======
        
        wall1 = new Wall();
        wall2 = new Wall();

>>>>>>> Stashed changes
        backgroundSpeed = 96;
        backgroundX = (backgroundImage.getWidth() - getWidth()) / 2;
        
        setPaintOrder(BatteryBar.class);
    }
    
    
    public void act() {
        timer--;
        System.out.println(battery);
        if(timer % 300 == 0) {
            battery-=10; //temporary. is supposed to be 1
            batteryBar.updateBar(battery);
        }
        if(!inCameras) {
            checkMouseMovement();
        }
        if(timer % 3600 == 0) {
            //switch to next hour on clock
        }
        
        if((battery == 0 && timer > 0) || !isAlive) {
            //play game over
        }
        if(battery > 0 && timer > 0 && isAlive) {
            //play game win
        }
        
        
        
    }
    
    /**
     * Method to check if the mouse is moving 
     */
    private void checkMouseMovement() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {
            int mouseXPos = mouse.getX();
            int deltaX = mouseXPos - getWidth() / 2; 

            //move speed
            backgroundX += deltaX * backgroundSpeed / getWidth(); 
            
            //so that movement doesn't go past image size
            int maxOffset = getBackground().getWidth() - getWidth();
            backgroundX = Math.max(0, Math.min(maxOffset, backgroundX));
            
            updateBackgroundPosition();
            if (mouseXPos > getWidth() / 2) {
                if(backgroundX > 30) {
                    if(backgroundX > 101){
                    addObject(wall2, getWidth() - 100 , getHeight() / 2);
                    removeObject(wall1);
                }
                    else wall1.setLocation(wall1.getX() - 5, wall1.getY());
                }
                
            } else if (mouseXPos < getWidth() / 2) {
                if(backgroundX < 169) {
                    if(backgroundX < 98){
                    addObject(wall1, getWidth() - 1052, getHeight() / 2);
                    removeObject(wall2);
                    }
                    else wall2.setLocation(wall2.getX() + 5, wall2.getY());
                }
                
            }
        }
        
    }

    /**
     * Method to update the background 
     */
    private void updateBackgroundPosition() {
        GreenfootImage background = getBackground();
        background.clear(); 
    
        GreenfootImage newBackground = new GreenfootImage("businessroom.png");
        background.drawImage(newBackground, -backgroundX, 0); 
        
        setBackground(background);
    }
    
    /**
     * Get method for battery
     */
    public int getBattery() {
        return battery;
    }
    /**
     * Set method for battery
     */
    public void setBattery(int battery) {
        this.battery = Math.max(0, Math.min(maxBattery, battery));
    }
    
    /**
     * Gets if player is in cameras
     */
    public boolean getIsInCameras() {
        return inCameras;
    }
}