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

        renderMap(((tempWorld)getWorld()).getMap().getTiles());
    }
    public void renderMap(Tile[][] map){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        for(int i = 1; i <= map.length; i++){
            for(int j = 1; j <= map[0].length; j++){
                int tileX = j*Constants.TILE_LEN;
                int tileY = i*Constants.TILE_LEN;
                System.out.print(map[i-1][j-1]+" ");
                if((x<tileX&&tileX<endX)&&(y<tileY&&tileY<endY)){
                    getWorld().addObject(map[i-1][j-1], tileX-x, tileY-y);
                    System.out.print("o");
                }else{
                    getWorld().removeObject(map[i-1][j-1]);
                    //map[i-1][j-1] = 0;
                    System.out.print(" ");
                }
                
            }
            System.out.println();
        }
        
    }
    
    
}
