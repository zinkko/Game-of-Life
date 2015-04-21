/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author Ilari
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Farmer extends JPanel{
    
    private ArrayList<Cell> solut,hitList,newCells;
    private int cellWidth,sleepTime,generationAmount;
    private boolean kaynnissa;
    private Color color;
    private int[] born;
    private int[] survive;
    
    public Farmer(int[] b, int[] s){
        super();
        this.cellWidth = 8;
        this.solut = new ArrayList<>();
        this.hitList = new ArrayList<>();
        this.newCells = new ArrayList<>();
        this.generationAmount = 0;
        this.color = Color.BLUE;
        
        this.born = b;
        this.survive = s;
    }
    
    public Farmer(){
        this(new int[]{3},new int[]{2,3});
    }
    
    public int nextGeneration(){
        
        this.generationAmount++;
        //System.out.println("Hi!");
        this.hitList.clear();
        this.newCells.clear();
        
        if (this.solut.isEmpty()){
            return -1;
        }
        
        List<Cell> naapurit;
        for (Cell cell: this.solut){
            naapurit = this.getNeighbours(cell);
            int neighbours = naapurit.size();
            
            /*if (neighbours<2){
                this.hitList.add(cell); //kill
            }
            else if(neighbours>3 && cell.isPopulated()){
                this.hitList.add(cell); //kill
            }
            else if (neighbours==3 && !cell.isPopulated()){
                this.newCells.add(cell); // born
            }*/
            if (cell.isPopulated()){ // kill?
                boolean dead = true;
                for (int i:this.survive){
                    if (neighbours==i){
                        dead = false;
                        break;
                    }
                }
                if (dead){
                    this.hitList.add(cell); // did not survive :(
                //}else{ // age
                //    cell.setColor(cell.getColor().darker());
                }
            }else{ //reincarnate?
                for (int i: this.born){
                    if (neighbours == i){
                        this.newCells.add(cell);
                        break;
                    }
                }
            }
            
        }
        
        for (Cell cell: this.newCells){
            naapurit = this.getNeighbours(cell);
            cell.setColor(this.getGrowthColor(naapurit));
            // this is separated, so that the cells to be born this generation
            // will not affect the color of ohter newborn cells
        }        
        for (Cell cell: this.newCells){
            cell.reincarnate();          
        }
        for (Cell cell: this.hitList){
            cell.kill();
        }
        this.repaint();
        
        return this.generationAmount;
    }
    
    public Color getGrowthColor(List<Cell> naapurit){
        List<Color> neighbours = new ArrayList<>();
        
        for (Cell c: naapurit){
            neighbours.add(c.getColor());
        }
        if (neighbours.isEmpty()){
            System.out.println("ERROR!!!");
            return Color.BLACK;
        }
        return this.average(neighbours);
    }
    
    private Color average(List<Color> colors){
        //int r = 0;
        //int g = 0;
        //int b = 0;
        float h = 0;
        float s = 0;
        float b = 0;
        
        for (Color c: colors){
            //r += c.getRed();
            //g += c.getGreen();
            //b += c.getBlue();
            float[] hsbColor = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(),null);
            h += hsbColor[0];
            s += hsbColor[1];
            b += hsbColor[2];
        }
        
        float l = (float) colors.size();
        h/= l; s/= l; b/=l; // average
        
        return new Color(Color.HSBtoRGB(h, s, b));
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public void change(int x, int y,Color c){
        x = Math.round(x/(cellWidth+1));
        y = Math.round(y/(cellWidth+1));

        for (Cell cell: this.solut){
            
            if (cell.getX()!=x || cell.getY()!=y){
                continue;
            }
            if (cell.isPopulated()){
                cell.kill();
            }else{
                //System.out.println("populate!");
                cell.reincarnate();
                cell.setColor(this.color);
            }
        }
        this.repaint();
    }
    
    public void clearCells(){
        for (Cell cell: this.solut){
            cell.kill();
        }
        this.generationAmount = 0;
        repaint();
    }
    
    private List<Cell> getNeighbours(Cell cell){
        List<Cell> neighbours = new ArrayList<>();
        
        int x = cell.getX();
        int y = cell.getY();
        
        for (int i=x-1; i<x+2;i++){ 
            if (i<0 || i>=100){ // outside "farm"
                continue;
            }          
            for (int j=y-1; j<y+2;j++){
                if (j<0 || j>= 100){ // outside "farm" (vertically)
                    continue;
                }
                Cell naapuri = this.solut.get(i*100+j);
                if (!naapuri.equals(cell) && naapuri.isPopulated()){
                    neighbours.add(naapuri);
                }
            }
        }
        //if (cell.isPopulated()){
        //    neighbours.remove(cell);
        //}

        return neighbours;
    }
    
    public void fillArea(int x, int y){
        for (int i=0; i<x;i++){
            for (int j=0; j<y;j++){
                this.solut.add(new Cell(i,j));
            }
        }
    }
    
    public ArrayList<Cell> getCells(){
        return this.solut;
    }
    
    @Override 
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Cell cell: this.solut){
            int x = (this.cellWidth+1)*cell.getX();
            int y = (this.cellWidth+1)*cell.getY();
            
            if (cell.isPopulated()){
                g.setColor(cell.getColor());
                //System.out.println(cell.getColor());
            }else{
                g.setColor(Color.lightGray);
            }
            
           // System.out.println("Hello!");
            
            
            g.fillRect( x, y, this.cellWidth, this.cellWidth);
            //System.out.println(x+" " +y*(cellWidth+1)+" "+ this.cellWidth+" "+ this.cellWidth);
            
        }
        
    }
}


