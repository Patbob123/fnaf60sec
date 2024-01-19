import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * Write a description of class SchoolWorlds here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class SchoolWorld extends World
{
    protected WorldManager wManager;
    protected HashMap<String, World> worldStorage;
    protected Player fisho;
    protected PressurePlate left, right, up, down;

    private Button map;
    private Button cam1;
    private Button cam2;
    private Button cam3;
    private Button cam4;
    private Button cam5;
    private Button cam6;
    Actor camMap= new CameraMap();

    private int numClicks = 2;
    /**
     * Constructor for objects of class SchoolWorlds.
     * 
     */
    public SchoolWorld(WorldManager wManager)
    {
        super(1152, 768, 1);
        //Greenfoot.setWorld(this);
        this.wManager = wManager;
        worldStorage = wManager.getHashMap();
        map = new Button("+", 40);
        addObject(map, 1057, 36);
        setPaintOrder(Button.class, CameraMap.class);
    }

    public boolean isCreated(String worldName){
        if(wManager.checkIfCreated(worldName) == false){
            wManager.storeWorld(worldName, this);
            return false;
        }
        return true;
    }

    public void act(){
        if (Greenfoot.mousePressed(map)){
            System.out.println(numClicks);
            if(numClicks == 2){
                map.updateMe("-");
                generateCamMap();
                numClicks--;
                System.out.println("expanded" + numClicks);
            }else{
                map.updateMe("+");
                numClicks++;
                removeObject(cam1);
                removeObject(cam2);
                removeObject(cam3);
                removeObject(cam4);
                removeObject(cam5);
                removeObject(cam6);
                removeObject(camMap);
                System.out.println("collapsed" + numClicks);
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

        addObject(camMap, 995, 79);

        addObject(cam1, 995, 79);
        addObject(cam2, 1057, 79);
        addObject(cam3, 1119, 79);

        addObject(cam4, 995, 114);
        addObject(cam5, 1057, 114);
        addObject(cam6, 1119, 114);

    }
}
