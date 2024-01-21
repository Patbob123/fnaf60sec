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
    
    private boolean dStageOne;
    private boolean dStageTwo;
    private boolean dStageThree;
    private boolean bgStageOne;
    private boolean bgStageTwo;
    private boolean bgStageThree;  
    
    private int dLocantCounter;
    private int bgLocantCounter;
    
    private int dLocantThreeTimer;
    private int bgLocantThreeTimer;
    
    public EnemyManager() {
        //Enemies have different movement times 
        bgTick = 360;
        dTick = 600;
        dLocantThreeTimer = 240;
        bgLocantThreeTimer = 240;
        bgLocantCounter = 0;
        dLocantCounter = 0;
        
    }
    
    public void spawnBg() {
        dStageOne = true;
        bgLocantCounter = 1;
    }
    public void spawnDaniel() {
        bgStageOne = true;
        dLocantCounter = 1;
    }
    
    public int getDLocant() {
        return dLocantCounter;
    }
    public int getBgLocant() {
        return bgLocantCounter;
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
                bgLocantCounter++;
                if(bgLocantCounter == 2) {
                    bgStageOne = false;
                    bgStageTwo = true;
                }
                else if(bgLocantCounter == 3) {
                    bgStageTwo = false;
                    bgStageThree = true;
                }
            }
            bgTick = 360;
        }
        if(dTick == 0) {
            dLocantCounter++;
            if(dLocantCounter == 2) {
                bgStageOne = false;
                bgStageTwo = true;
            }
            else if(dLocantCounter == 3) {
                bgStageTwo = false;
                bgStageThree = true;
            }
            dTick = 600;
        }
        
        //If any of the enemies are on stage 3
        if(dLocantCounter == 3) {
            dLocantThreeTimer--;
            if(dLocantThreeTimer == 0 && ((GameRoom)getWorld()).getRightDoor()) {
                dLocantCounter = 1;
            }
            else {
                ((GameRoom)getWorld()).setAlive(false);
            }
        }    
        if(bgLocantCounter == 3) {
            bgLocantThreeTimer--;
            if(bgLocantThreeTimer == 0 && ((GameRoom)getWorld()).getLeftDoor()) {
                bgLocantCounter = 1;
            }
            else {
                ((GameRoom)getWorld()).setAlive(false);
            }
        }  
    }
}
