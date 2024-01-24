import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manager for Enemy 
 * 
 * @author Jaiden
 * <p> Modified and updated by Dawson </p>
 * @version Jan 2024
 */
public class EnemyManager extends Actor
{
    private boolean specialStage;
    
    private Enemy daniel, tyrone;
    
    private int specialStageTimer;
    private int specialRunningTimer;
    private String killer;
    
    private SimpleTimer simpleTimer = new SimpleTimer();
    
    public EnemyManager() {
        //Enemies have different movement times 
        
        daniel = new Enemy("daniel", 720, 720, 0);
        tyrone = new Enemy("tyrone", 900, 900, 0);
        killer = "none";
        
        specialStageTimer = 1800;
        specialRunningTimer = 300;
        specialStage= false;
        setImage(new GreenfootImage(1,1));
    }
    
    /**
     * Set Special stage number
     * @param stage     Set to this number
     */
    public void setSpecialStage(boolean stage) {
        this.specialStage = stage;
    }

    /**
     * Act - do whatever the EnemyManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        //If any of the enemies are on stage 3
        knockOnDoor();
    }
    /**
     * Method to determine if player's door is closed
     * If closed, leave
     * Else Kill the player
     */
    public void knockOnDoor(){
        if(daniel.getStage() == 3) {
            daniel.decreaseTimer(1);
            if(daniel.getResetTimer() == 0 && !((GameRoom)getWorld()).getLeftDoor()) {
                killer = "danieljump2.jpg";
                ((GameRoom)getWorld()).setAlive(false);
            }
            else if(daniel.getResetTimer() == 0 && ((GameRoom)getWorld()).getLeftDoor()){
                daniel.setStage(1);
            }
        }    
        
        if(tyrone.getStage() == 3) {
            tyrone.decreaseTimer(1);
            if(tyrone.getResetTimer() == 0 && ((GameRoom)getWorld()).getRightDoor()) {
                tyrone.setStage(1);
            }
            else if (tyrone.getResetTimer() == 0 && !((GameRoom)getWorld()).getRightDoor()){
                killer = "tyronejump1.png";
                ((GameRoom)getWorld()).setAlive(false);
            }
        }  
    }
    
    /*
     * Helper Methods
     */
    public String getKiller(){
        return killer;
    }
    public void moveEnemies(){
        daniel.moveLocation();
        tyrone.moveLocation();
    }
    public int getDanielLocation(){
        return daniel.getLocation(daniel.getStage());
    }
    public int getTyroneLocation(){
        return tyrone.getLocation(tyrone.getStage());
    }
    public Enemy getDaniel(){
        return daniel;
    }
    public Enemy getTyrone(){
        return tyrone; 
    }
    
}
