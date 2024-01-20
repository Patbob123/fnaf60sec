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
    private int totalWeight;
    public Inventory()
    {
        storage = new ArrayList<>(); 
        totalWeight = 0;
    }
    public void act(){
        
    }
    public boolean isEmpty(){
        if(totalWeight < 4){
            return true;
        }
        return false;
    }
    public void addWeight(int weight){
        totalWeight += weight;
    }
    public void clearWeight(){
        totalWeight = 0;
    }
    public int getTotalWeight(){
        return totalWeight;
    }
    public ArrayList<Item> getStorage(){
        return storage;
    }
    public String toString(){
        //Item[] array = storage.toArray(new Item[storage.size()]);
        return storage.toString();
    }
}
