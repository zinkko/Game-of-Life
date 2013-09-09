/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author Ilari
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class NappiKuuntelija implements ActionListener{
    
    private boolean kaynnissa;
    private Farmer farmer;
    private GenThread t;
    private Kuuntelija k;
    private JTextField vari,gen;
    
    private JButton paaNappi,btn2,btn3;
    
    
    public NappiKuuntelija(Farmer farmer,Kuuntelija k,JButton paaNappi, JButton poisto, JButton nxt,JTextField vari,JTextField gen){
        this.farmer = farmer;
        this.kaynnissa  =false;
        this.paaNappi = paaNappi;
        this.k = k;
        this.btn2 = poisto;
        this.btn3 = nxt; 
        this.vari = vari;
        this.gen = gen;
    }
    
    @ Override
    
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource().equals(paaNappi)){
            this.paaNappiToiminto();
        }else if (e.getSource().equals(btn2)){
            this.tyhjennys();
        }else if (e.getSource().equals(btn3)){
            this.seuraava();
        }
    }
    
    public void tyhjennys(){
        this.farmer.clearCells();
        this.gen.setText("Generation: 0");
    }
    
    public void seuraava(){
        this.farmer.nextGeneration();
    }
    
    public void paaNappiToiminto(){
        if (kaynnissa){ // lopettaa
            this.paaNappi.setText("Aloita");
            this.btn2.setEnabled(true);
            this.btn3.setEnabled(true);
            this.vari.setEditable(true);
            //t = null;
            t.interrupt();
            kaynnissa  = false;
            
        }else{ // aloittaa
            this.paaNappi.setText("Lopeta");
            this.btn2.setEnabled(false);
            this.btn3.setEnabled(false);
            this.vari.setEditable(false);
            if (t !=null){
                if (t.isInterrupted()){
                    return;
                }
            }
            
            t = new GenThread(farmer,this.gen);
            t.setDaemon(true); 
            t.start();
            kaynnissa = true;
        }
        paaNappi.repaint();
    }
}

class GenThread extends Thread{
    
    private Farmer f;
    private int sleepTime;
    private JTextField genField;
    
    public GenThread(Farmer f,JTextField genField){
        this.f = f;
        sleepTime = 100;
        this.genField = genField;
    }
    
    @ Override
    public void run(){
        while (true){
            int gen = f.nextGeneration();
            genField.setText("Generation: "+gen);
            try{
                Thread.sleep(sleepTime);
            } catch (InterruptedException e){
                System.out.println("broken! :(");
                break;            
            }
        }
        
    }
}
