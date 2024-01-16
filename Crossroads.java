import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.*; 

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Crossroads extends World
{
    public File map = new File("files/Map.txt");
    public static int height = 36;  
    public static int width = 36;  
    public String [][] test = new String [height][width];
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Crossroads()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1152, 768, 1,false); // 36 by 24
        //MapBuilder map = new MapBuilder();
        //addObject(map,0,0);
        Player fisho = new Player();
        addObject(fisho,576,384);
        try{
            loadWorld();
            drawWorld();
        }
        catch(FileNotFoundException e){
            System.out.println("Error: "+e);
        }
        
    }
    public void act(){
        try{
            if(Greenfoot.isKeyDown("1")){
                saveWorld();
                System.out.println("here");
            }
        }
        catch(IOException e){
            System.out.println("error");
        }
    }
    public void move(double moveX, double moveY){
        for(Tile t: getObjects(Tile.class)){
            t.setLocation(t.getX()+moveX,t.getY()+moveY);
        }
    }
    public void drawWorld(){
        for(int i = 0; i< 1;i++){
            for(int j = 0; j< width; j++){
                //System.out.println(test[i][j]);
                //System.out.println(Arrays.toString(test[i]));
                if(test[i][j].equals("w")){
                    addObject(new Wall(25,25), 32*j, 32*i);
                }
            }
        }
    }
    public void saveWorld() throws IOException {
        FileWriter output = new FileWriter(map);
        PrintWriter printer = new PrintWriter(output);
        int counter = 0;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i<height; i++){
            for(int j = 0; j < width; j++){
                builder.append(test[i][j]);
                if(j < width){
                    builder.append(",");
                }
            }
            builder.append("\n");
        }
        
        printer.print(builder.toString());
        printer.close(); 
    }
    public void loadWorld() throws NoSuchElementException, FileNotFoundException {
        Scanner scanner = new Scanner(map);
        for(int i = 0; i < width; i++){
            if(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(",");
                for(int j = 0; j < width; j++){
                    test[i][j] = line[j];
                }
            }
        }
        
    }
}
