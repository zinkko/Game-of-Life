/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author Ilari
 */
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
        

public class Game implements Runnable{
    private JFrame frame;
    private Farmer farmer;
    
    public Game(int[] b, int[] s){
        this.frame = new JFrame();
        this.farmer = new Farmer(b,s);
    }
    public Game(){
        this.frame = new JFrame();
        this.farmer = new Farmer();
    }
    
    @ Override
    public void run(){
        frame.setPreferredSize(new Dimension(800,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoKomponentit(Container c){
        //Farmer farmer = new Farmer();
        farmer.fillArea(100,100);
        Kuuntelija kuunt = new Kuuntelija(farmer);
        farmer.addMouseListener(kuunt);
        
        c.add(farmer,BorderLayout.CENTER);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,5));
        
        JButton nappi = new JButton("Aloita");
        JButton clrBtn = new JButton("tyhjennä");
        JButton nxt = new JButton("seuraava");
        JTextField vari = new JTextField("kirjoita tänne väri");
        JTextField gen = new JTextField("Generation: 0");
        gen.setEditable(false);
            
        vari.addKeyListener(new AnkkaKuuntelija(vari));
        
        NappiKuuntelija k = new NappiKuuntelija(farmer,kuunt,nappi,clrBtn,nxt,vari,gen);
        nappi.addActionListener(k);
        clrBtn.addActionListener(k);
        nxt.addActionListener(k);
        
        controlPanel.add(gen);
        controlPanel.add(nappi);
        controlPanel.add(clrBtn);
        controlPanel.add(nxt);
        controlPanel.add(vari);
        
        c.add(controlPanel,BorderLayout.SOUTH);
        
    }


    private class AnkkaKuuntelija implements KeyListener{ // to be replaced by some kind of implementation of a dropdown menu  

        private JTextField txt;
        private Map<String,Color> varit;

        public AnkkaKuuntelija(JTextField txt){
            AnkkaKuuntelija.this.txt = txt;
            this.varit = new HashMap<>();
            varit.put("blue", Color.blue);
            varit.put("green", Color.green);
            varit.put("yellow", Color.yellow);
            varit.put("purple", Color.magenta.darker());
            varit.put("red", Color.red);
            
        }

        @Override
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                String colorName = txt.getText();
                Game.this.farmer.setColor(this.varit.get(colorName));
                txt.setText("");
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
        }

        @Override
        public void keyTyped(KeyEvent e){
        }
    }

}
    