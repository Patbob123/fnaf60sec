import greenfoot.*;

/**
 * Viewport class to generate the world for the player's screen
 * 
 * @author Dawson
 * @version January 2024
 */
public class Viewport extends Actor
{
    private int x;
    private int y;
    private int endX;
    private int endY;
    private int width;
    private int height;
    private final int xOffset = Constants.TILE_LEN/2;
    private final int yOffset = Constants.TILE_LEN/2;
    
    /**
     * Constructor for the ViewPort
     * 
     * @param       width of the world
     * @param       height of the world
     */
    public Viewport(int width, int height){
        x = 0;
        y = 0;
        this.width = width;
        this.height = height;
    }
    /**
     * Method to move the render of the world when the player moves
     * 
     * @param addX      total movement in x direction
     * @param addY      total movement in y direction
     */
    public void move(double addX, double addY){
        x+=addX;
        y+=addY;
        endX = x+width;
        endY = y+height;
        
        moveObjects(-addX, -addY);
        renderMap(getW().getMap().getTiles(), getW().getMap().getItems());
    }
    
    /**
     * Method to move objects according the player's movement inputs
     * 
     * @param addX      total movement in x direction
     * @param addY      total movement in y direction
     */
    public void moveObjects(double addX, double addY){
        for(Tile t: getW().getObjects(Tile.class)){
            t.setLocation(t.getX()+addX, t.getY()+addY);
        }
        for(Enemy e: getW().getObjects(Enemy.class)){
            e.setLocation(e.getX()+addX, e.getY()+addY);
        }
    }
    
    /** 
     * Main method to generate the world
     * 
     * @param map           The 2d array of the tileset
     * @param itemLayer     The 2d array of the items spawns 
     */
    public void renderMap(Tile[][] map, Tile[][] itemLayer){ //Its FAST SO NO ONE CARES IF IT LOOPS THROUGH IT EVERYTIME YEA?
        tempWorld w = getW();
        for(int i = 1; i <= map.length; i++){
            for(int j = 1; j <= map[0].length; j++){
                int tileX = j*Constants.TILE_LEN;
                int tileY = i*Constants.TILE_LEN;
                if((x<tileX-xOffset&&tileX-xOffset<endX)&&(y<tileY-yOffset&&tileY-yOffset<endY)){
                    if(!w.getObjects(Tile.class).contains(map[i-1][j-1])){
                        w.addObject(map[i-1][j-1], tileX-x-xOffset, tileY-y-yOffset);
                        
                        if(itemLayer[i-1][j-1]!=null) w.addObject(itemLayer[i-1][j-1], tileX-x-xOffset, tileY-y-yOffset);
                    }
                }else{
                    w.removeObject(map[i-1][j-1]);
                    if(itemLayer[i-1][j-1]!=null) w.removeObject(itemLayer[i-1][j-1]);
                }
            }
        }
        
    }
    
    /**
     * Helper method to remove items from the map
     * 
     * @param item      Item to be removed
     */
    public void removeItem(Item item){
        Tile[][] items = getW().getMap().getItems();
        for(int i = 0; i < items.length; i++){
            for(int j = 0; j < items[0].length; j++){
                if(items[i][j]==item){
                    getW().removeObject(items[i][j]);
                    items[i][j] = null;
                } 
            }
            
        }
    }
    
    /**
     * Helper method to get First Floor World
     */
    public tempWorld getW(){
        return ((tempWorld)getWorld());
    }
}
