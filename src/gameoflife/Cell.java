/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;

/**
 *
 * @author Ilari
 */
public class Cell{
    private final int x;
    private final int y;
    private Color c;
    private boolean populated;
    
    public Cell(int x, int y,Color c){
        this.x = x;
        this.y = y;
        this.c = c;
        this.populated = false;
    }
    
    public Cell(int x, int y){
        this(x,y,Color.BLUE);
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setColor(Color c){
        this.c = c;
    }
    
    public Color getColor(){
        return this.c;
    }
    
    public void kill(){
        this.populated = false;
    }
    
    public void reincarnate(){
        this.populated = true;
    }
    
    public boolean isPopulated(){
        return this.populated;
    }
    
    public int distanceTo(Cell othercell){
        int dx = Math.abs(this.x-othercell.getX());
        int dy = Math.abs(this.y-othercell.getY());
        
        return Math.max(dx, dy);
    }
    
    @ Override
    
    public String toString(){
        return this.x+", "+this.y+" "+this.populated;
    }
}
