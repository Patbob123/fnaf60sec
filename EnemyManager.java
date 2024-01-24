import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyManager extends Actor
{
    private boolean specialStage;
    
    private Enemy daniel, tyrone;
    
    private int specialStageTimer;
    private int specialRunningTimer;
    
    public EnemyManager() {
        //Enemies have different movement times 
        
        daniel = new Enemy("daniel", 360, 360, 0);
        tyrone = new Enemy("tyrone", 600, 600, 0);
        
        specialStageTimer = 1800;
        specialRunningTimer = 300;
        specialStage= false;
        setImage(new GreenfootImage(1,1));
    }
    /*
    /**
     * Set Daniel's stage number
     * @param stage     Set to this number
     
    public void setDStageSeven(boolean stage) {
        this.dStageThree = stage;
        dLocation = 7;
    }
    */
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
        
        
        /*
        if(specialStage){
            if(specialStageTimer == 0) {
                specialRunningTimer--;
                //play running sound in LEFT EAR
            }
            else{
                specialStageTimer--; 
            }
            
        }
        if(specialRunningTimer == 0 && ((GameRoom)getWorld()).getLeftDoor()) {
            ((GameRoom)getWorld()).setAlive(false);
            //play daniel jumpscare
        }
        */
        knockOnDoor();
    }
    public void knockOnDoor(){
        if(daniel.getStage() == 3) {
            daniel.decreaseTimer(1);
            if(daniel.getResetTimer() == 0 && !((GameRoom)getWorld()).getLeftDoor()) {
                ((GameRoom)getWorld()).setAlive(false);
                
            }
            else if(daniel.getResetTimer() == 0 && ((GameRoom)getWorld()).getLeftDoor()){
                int specialSpawn = Greenfoot.getRandomNumber(10);
                if(specialSpawn == 3) {
                    //daniel.setStage(7);
                }
                else {
                    daniel.setStage(1);
                }
            }
        }    
        
        if(tyrone.getStage() == 3) {
            ((SuperWorld)getWorld()).getSM().playSound("doorKnock1");
            tyrone.decreaseTimer(1);
            if(tyrone.getResetTimer() == 0 && ((GameRoom)getWorld()).getRightDoor()) {
                tyrone.setStage(1);
            }
            else if (tyrone.getResetTimer() == 0 && !((GameRoom)getWorld()).getRightDoor()){
                ((GameRoom)getWorld()).setAlive(false);
            }
        }  
    }
    public void moveEnemies(){
        daniel.moveLocation();
        tyrone.moveLocation();
    }
    public int getDanielLocation(){
        System.out.println(daniel.getStage());
        return daniel.getLocation(daniel.getStage());
    }
    public int getTyroneLocation(){
        System.out.println(tyrone.getStage());
        return tyrone.getLocation(tyrone.getStage());
    }
    public Enemy getDaniel(){
        return daniel;
    }
    public Enemy getTyrone(){
        return tyrone; 
    }
    
}
