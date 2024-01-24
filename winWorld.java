import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Write a description of class winWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class winWorld extends SuperWorld
{
    private Presser nextButton;
    private GreenfootImage startBg = new GreenfootImage("winWorld.png");
    public winWorld()
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
    public void goToStartWorld(){
        goToWorld(new startWorld());
    }
    public void unlockGoldTimmy(){
        writeFile("Tom","goldtimmysprites.png");
        saveFile("files/data.txt");
    }
    public Function goStartWorld = () -> goToStartWorld();
}