import greenfoot.*;

public class GameRoom extends World {

    private int backgroundX;
    private int backgroundSpeed;
    private Wall wall1;
    private Wall wall2;
    private boolean inCameras;
    
    public GameRoom() {
        super(1152, 768, 1);
        inCameras = false;
        GreenfootImage backgroundImage = new GreenfootImage("businessroom.png");
        backgroundX = (backgroundImage.getWidth() - getWidth()) / 2;
        setBackground(backgroundImage);
        updateBackgroundPosition();
        
        wall1 = new Wall(20,20);
        wall2 = new Wall(20,20);
        

        backgroundSpeed = 96;
    }
    
    
    public void act() {
        if(!inCameras) {
            checkMouseMovement();
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
            System.out.println(backgroundX);
            
            if (mouseXPos > getWidth() / 2) {
                if(backgroundX > 100){
                    addObject(wall2, getWidth() + 100 , getHeight() / 2);
                    removeObject(wall1);
                }
            } else {
                if(backgroundX < 96){
                    addObject(wall1, getWidth() - 1252, getHeight() / 2);
                    removeObject(wall2);
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
     * Gets if player is in cameras
     */
    public boolean getIsInCameras() {
        return inCameras;
    }
}