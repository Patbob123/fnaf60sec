import java.util.ArrayList;
import greenfoot.*;
import java.util.Arrays;

/**
 * Inventory class to keep track of stored objects 
 * 
 * @author Vincent 
 * @version January 2024
 */
public class Inventory  
{
    private ArrayList<Item> storage; 
    private int totalWeight;
    /**
     * Constructor for Inventory Class
     */
    public Inventory()
    {
        storage = new ArrayList<>(); 
        totalWeight = 0;
    }
    /**
     * Method to check if item can fit into the Player's inventory
     */
    public boolean canPickup(int addedWeight){
        if(totalWeight + addedWeight <= 4){
            return true;
        }
        return false;
    }
    /**
     * Method to add weight to the inventory
     * 
     * @param weight    Weight of the item
     */
    public void addWeight(int weight){
        totalWeight += weight;
    }
    /** 
     * Method to clear all the weight in the player's inventory
     */
    public void clearWeight(){
        totalWeight = 0;
    }
    /**
     * Getter method to return the current weight taken up in the player's inventory
     */
    public int getTotalWeight(){
        return totalWeight;
    }
    public ArrayList<Item> getStorage(){
        return storage;
    }
    /**
     * toString method to return the contents of the inventory arraylist
     */
    public String toString(){
        return storage.toString();
    }
}
