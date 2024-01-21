import greenfoot.*;

/**
 * Write a description of class Viewport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    public Viewport(int width, int height){
        x = 0;
        y = 0;
        this.width = width;
        this.height = height;
    }
    public void move(double addX, double addY){
        x+=addX;
        y+=addY;
        endX = x+width;
        endY = y+height;
        
        moveObjects(-addX, -addY);
        renderMap(getW().getMap().getTiles(), getW().getMap().getItems());
    }
    public void moveObjects(double addX, double addY){
        for(Tile t: getW().getObjects(Tile.class)){
            t.setLocation(t.getX()+addX, t.getY()+addY);
        }
        for(Enemy e: getW().getObjects(Enemy.class)){
            e.setLocation(e.getX()+addX, e.getY()+addY);
        }
    }
    public void renderMap(Tile[][] map, Tile[][] itemLayer){ //Its FAST SO NO ONE CARES IF IT LOOPS THROUGH IT EVERYTIME YEA?
        tempWorld w = getW();
        //w.removeObjects(w.getObjects(Tile.class));
        //$System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        for(int i = 1; i <= map.length; i++){
            for(int j = 1; j <= map[0].length; j++){
                int tileX = j*Constants.TILE_LEN;
                int tileY = i*Constants.TILE_LEN;
                //System.out.print(map[i-1][j-1]+" ");
                if((x<tileX-xOffset&&tileX-xOffset<endX)&&(y<tileY-yOffset&&tileY-yOffset<endY)){
                    if(!w.getObjects(Tile.class).contains(map[i-1][j-1])){
                        w.addObject(map[i-1][j-1], tileX-x-xOffset, tileY-y-yOffset);
                        
                        if(itemLayer[i-1][j-1]!=null) w.addObject(itemLayer[i-1][j-1], tileX-x-xOffset, tileY-y-yOffset);
                    }
                    
                    //System.out.print("o");
                }else{
                    
                    w.removeObject(map[i-1][j-1]);
                    if(itemLayer[i-1][j-1]!=null) w.removeObject(itemLayer[i-1][j-1]);
                    //map[i-1][j-1] = 0;
                }
                
            }
        }
        
    }
    public void removeItem(Item item){
        System.out.println("ASD");
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
    public tempWorld getW(){
        return ((tempWorld)getWorld());
    }
    
    
}
