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
    private boolean leftDoorClosed;
    private boolean rightDoorClosed;
    private int battery;
    private int maxBattery;
    
    private int actCounter;
    private int timer; 
    
    //For cameras
    private Button map;
    private Button cam1;
    private Button cam2;
    private Button cam3;
    private Button cam4;
    private Button cam5;
    private Button cam6;
    private Button cam7;
    private int numClicks = 2;
    private CameraMap camMap;
    private Camera c1, c1Empty, c2, c2Empty, c3, c3Empty, c4, c4Empty, c5, c5Empty, c6, c6Empty, c7, c7Empty;
    private Tile tiles[][];
    private BatteryBar batteryBar;
    private EnemyManager em;
    /**
     * Constructor for GameRoom
     */
    public GameRoom() {
        super(1152, 768, 1);
        actCounter = 0;
        timer = 21600; // 6 minutes
        inCameras = false;
        leftDoorClosed = false;
        rightDoorClosed = false;
        
        GreenfootImage backgroundImage = new GreenfootImage("businessroom.png");
        camMap = new CameraMap("translucentCamMap.PNG");
        c1 = new Camera(1, true, "baby2.png");
        c1Empty = new Camera(1, false, "baby2.png");
        c2 = new Camera(1, true, "baby2.png");
        c2Empty = new Camera(1, false, "baby2.png");
        c3 = new Camera(1, true, "baby2.png");
        c3Empty = new Camera(1, false, "baby2.png");
        c4 = new Camera(1, true, "baby2.png");
        c4Empty = new Camera(1, false, "baby2.png");
        c5 = new Camera(1, true, "baby2.png");
        c5Empty = new Camera(1, false, "baby2.png");
        c6 = new Camera(1, true, "baby2.png");
        c6Empty = new Camera(1, false, "baby2.png");
        c7 = new Camera(1, true, "baby2.png");
        c7Empty = new Camera(1, false, "baby2.png");
        
        backgroundX = 100; //to set at middle
        setBackground(backgroundImage);
        updateBackgroundPosition();

        wall1 = new Wall();
        wall2 = new Wall();
        
        maxBattery = 100;
        battery = 50;
        
        batteryBar = new BatteryBar(battery);
        addObject(batteryBar, 1101, 27);
    
        map = new Button("+", 40);
        addObject(map, 915, 734);
        
        backgroundSpeed = 96;
        backgroundX = (backgroundImage.getWidth() - getWidth()) / 2;
        
        em = new EnemyManager();
        
        setPaintOrder(Button.class, BatteryBar.class);
    }
    
    
    public void act() {
        timer--;
        if(timer < 21300) { //spawn them after 30 seconds
            em.spawnBg();
            em.spawnDaniel();
        }
        
        //to open and reopen the cameras
        if (Greenfoot.mousePressed(map)){
            if(numClicks == 2){
                map.updateMe("-");
                generateCamMap();
                numClicks--;
                inCameras = true;
            }else{
                map.updateMe("+");
                numClicks++;
                removeObject(cam1);
                removeObject(cam2);
                removeObject(cam3);
                removeObject(cam4);
                removeObject(cam5);
                removeObject(cam6);
                removeObject(cam7);
                removeObject(camMap);
                inCameras = false;
            }
        }
        
        //black guy cameras
        if(Greenfoot.mousePressed(cam1)) {
            if(em.getBgLocant() == 1) {
                if(em.getBgStage(1)){
                    addObject(c1, getWidth()/2, getHeight()/2);
                }
                else if(!em.getBgStage(1)){
                    addObject(c1Empty, getWidth()/2, getHeight()/2);
                }
            }
        }
        if(Greenfoot.mousePressed(cam2)) {
            if(em.getBgLocant() == 2) {
                if(em.getBgStage(1)){
                    addObject(c2, getWidth()/2, getHeight()/2);
                }
                else if(!em.getBgStage(2)){
                    addObject(c2Empty, getWidth()/2, getHeight()/2);
                }
            }
        }
        if(Greenfoot.mousePressed(cam3)) {
            if(em.getBgLocant() == 3) {
                if(em.getBgStage(3)){
                    addObject(c3, getWidth()/2, getHeight()/2);
                }
                else if(!em.getBgStage(3)){
                    addObject(c3Empty, getWidth()/2, getHeight()/2);
                }
            }
        }

        //for daniel cameras
        if(Greenfoot.mousePressed(cam4)) {
            if(em.getDLocant() == 4) {
                if(em.getDStage(1)){
                    addObject(c4, getWidth()/2, getHeight()/2);
                }
                else if(!em.getDStage(1)){
                    addObject(c4Empty, getWidth()/2, getHeight()/2);
                }
            }
        }
        if(Greenfoot.mousePressed(cam5)) {
            if(em.getDLocant() == 5) {
                if(em.getDStage(5)){
                    addObject(c5, getWidth()/2, getHeight()/2);
                }
                else if(!em.getDStage(5)){
                    addObject(c5Empty, getWidth()/2, getHeight()/2);
                }
            }
        }
        if(Greenfoot.mousePressed(cam6)) {
            if(em.getDLocant() == 6) {
                if(em.getDStage(6)){
                    addObject(c3, getWidth()/2, getHeight()/2);
                }
                else if(!em.getDStage(6)){
                    addObject(c6Empty, getWidth()/2, getHeight()/2);
                }
            }
        }
        
        //for daniel??
        if(Greenfoot.mousePressed(cam7)) {
            if(em.getDLocant() == 7) {
                if(em.getDStage(7)){
                    addObject(c7, getWidth()/2, getHeight()/2);
                }
                else if(!em.getDStage(7)){
                    addObject(c7Empty, getWidth()/2, getHeight()/2);
                }
            }
        }
        
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
     * Method to generate layout of cameras
     */
    public void generateCamMap(){
        cam1 = new Button("CAM1", 20);
        cam2 = new Button("CAM2", 20);
        cam3 = new Button("CAM3", 20); 
        cam4 = new Button("CAM4", 20);
        cam5 = new Button("CAM5", 20);
        cam6 = new Button("CAM6", 20);
        cam7 = new Button("CAM7", 20);

        addObject(camMap, 913, 625);

        addObject(cam1, 808, 572);
        addObject(cam2, 870, 597);
        addObject(cam3, 869, 644);

        addObject(cam4, 1022, 572);
        addObject(cam5, 966, 596);
        addObject(cam6, 966, 644);
        addObject(cam7, 900, 549);

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
     * Set method for battery
     */
    public void setBattery(int battery) {
        this.battery = Math.max(0, Math.min(maxBattery, battery));
    }
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    
    public boolean getRightDoor() {
        return rightDoorClosed;
    }
    public boolean getLeftDoor() {
        return leftDoorClosed;
    }
    /**
     * Gets if player is in cameras
     */
    public boolean getIsInCameras() {
        return inCameras;
    }
}