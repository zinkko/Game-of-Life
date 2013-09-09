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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Kuuntelija implements MouseListener{
    
    private Farmer farmer;   
    public Kuuntelija(Farmer farmer){
        this.farmer = farmer;

    }
    
    @ Override
    public void mousePressed(MouseEvent e){
        this.farmer.change(e.getX(),e.getY(),Color.BLUE);
        //System.out.println("pressed @ "+e.getX()+", "+e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
