import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class theWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class theWorld extends World
{
    private Button camButton;
    private Button cam1;
    private Button cam2;
    private Button cam3;
    private Button cam4;
    private Button cam5;
    private Button cam6;
    private Button cam7;
    CameraMap camMap= new CameraMap("tile-bg.png");

    private int numClicks = 2;
    
    private int CMXOffset = 1015;
    private int CMYOffset = 156;
    
    /**
     * Constructor for objects of class theWorld.
     * 
     */
    public theWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1152, 768, 1);
        
        camButton = new Button("+", 40);
        addObject(camButton, 1045, 30);
        setPaintOrder(Button.class, CameraMap.class);
    }

    public void act(){
        if (Greenfoot.mousePressed(camButton)){
            //System.out.println(numClicks);
            if(numClicks == 2){
                camButton.updateMe("-");
                generateCamMap();
                numClicks--;
                //System.out.println("expanded" + numClicks);
            }else{
                camButton.updateMe("+");
                numClicks++;
                removeObject(cam1);
                removeObject(cam2);
                removeObject(cam3);
                removeObject(cam4);
                removeObject(cam5);
                removeObject(cam6);
                removeObject(cam7);
                removeObject(camMap);
                //System.out.println("collapsed" + numClicks);
            }
        }
    }

    private void generateCamMap(){
        cam1 = new Button("CAM1", 20);
        cam2 = new Button("CAM2", 20);
        cam3 = new Button("CAM3", 20); 
        cam4 = new Button("CAM4", 20);
        cam5 = new Button("CAM5", 20);
        cam6 = new Button("CAM6", 20);
        cam7 = new Button("CAM7", 20);

        addObject(camMap, CMXOffset, CMYOffset);

        addObject(cam1, CMXOffset - 63, CMYOffset - 10);
        addObject(cam2, CMXOffset - 41, CMYOffset + 24);
        addObject(cam3, CMXOffset + 62, CMYOffset - 11);

        addObject(cam4, CMXOffset + 23, CMYOffset + 20);
        addObject(cam5, CMXOffset - 95, CMYOffset + 54);
        addObject(cam6, CMXOffset + 5, CMYOffset + 66);
        
        addObject(cam7, CMXOffset + 94, CMYOffset + 40);

    }
}
