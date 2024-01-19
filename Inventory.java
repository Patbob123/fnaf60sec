import java.util.ArrayList;
import greenfoot.*;
import java.util.Arrays;

/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory  
{
    private ArrayList<Item> storage; 
    public Inventory()
    {
        storage = new ArrayList<>(); 
    }
    
    public void pickUp(){
        
    }
    public void dropOff(){
        
    }
    public boolean isEmpty(){
        if(storage.size() < 4){
            return true;
        }
        return false;
    }
    public ArrayList<Item> getStorage(){
        return storage;
    }
    public String toString(){
        Item[] array = storage.toArray(new Item[storage.size()]);
        return Arrays.toString(array);
    }
}
