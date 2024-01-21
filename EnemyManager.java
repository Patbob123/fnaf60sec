import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyManager extends Actor
{
    private Enemy blackGuy, daniel;
    private int bgTick, dTick;
    private int bgCamCount, danielCamCount;
    private int spawnLocant;
    private int bgCameraLocant;
    private int dCameraLocant;
    private int dLocantThreeTimer;
    private int bgLocantThreeTimer;
    private boolean bgIsSpawned;
    private boolean dIsSpawned;
    
    public EnemyManager() {
        //Enemies have different movement times 
        bgTick = 360;
        dTick = 600;
        dLocantThreeTimer = 240;
        bgLocantThreeTimer = 240;
        dIsSpawned = false;
        bgIsSpawned = false;
    }
    
    public void spawnBg() {
        bgIsSpawned = true;
        bgCameraLocant = 1;
    }
    public void spawnDaniel() {
        bgIsSpawned = true;
        dCameraLocant = 1;
    }
    
    public int getDLocant() {
        return dCameraLocant;
    }
    public int getBgLocant() {
        return dCameraLocant;
    }
    
    /**
     * Act - do whatever the EnemyManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        bgTick--;
        dTick--;
        
        if(bgTick == 0) {
            int chance = Greenfoot.getRandomNumber(2);
            if(chance == 1){
                bgCameraLocant++;
            }
            bgTick = 360;
        }
        if(dTick == 0) {
            dCameraLocant++;
            dTick = 600;
        }
        
        //If any of the enemies are on stage 3
        if(dCameraLocant == 3) {
            dLocantThreeTimer--;
            if(dLocantThreeTimer == 0 && ((GameRoom)getWorld()).getRightDoor()) {
                dCameraLocant = 1;
            }
            else {
                ((GameRoom)getWorld()).setAlive(false);
            }
        }    
        if(bgCameraLocant == 3) {
            bgLocantThreeTimer--;
            if(bgLocantThreeTimer == 0 && ((GameRoom)getWorld()).getLeftDoor()) {
                dCameraLocant = 1;
            }
            else {
                ((GameRoom)getWorld()).setAlive(false);
            }
        }  
    }
}
