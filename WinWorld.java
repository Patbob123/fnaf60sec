import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Display win world
 * 
 * @author Vincent
 * @version January 2024
 */
public class WinWorld extends SuperWorld
{
    private Presser nextButton;
    private GreenfootImage startBg = new GreenfootImage("winWorld.png");
    public WinWorld()
    {   
        super(Constants.WW, Constants.WH, 1);
        GreenfootImage restartBut = new GreenfootImage("restartButton.png");
        restartBut.scale(400,100);
        nextButton = new Presser(goStartWorld, restartBut);
        addObject(nextButton, 550, 600);
        
        setBackground(startBg);
        
        setPaintOrder(Effect.class, Presser.class);
        unlockGoldTimmy();
    }
    public void act(){
        super.act();
    }
    public void goToStartWorld(){
        goToWorld(new StartWorld());
    }
    public void unlockGoldTimmy(){
        writeFile("TimSprite","goldtimmysprites.png");
        saveFile("files/data.txt");
    }
    public Function goStartWorld = () -> goToStartWorld();
}