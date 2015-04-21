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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game implements Runnable {

    private JFrame frame;
    private Farmer farmer;

    public Game(int[] b, int[] s) {
        this.frame = new JFrame();
        this.farmer = new Farmer(b, s);
    }

    public Game() {
        this.frame = new JFrame();
        this.farmer = new Farmer();
    }

    @Override
    public void run() {
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container c) {
        //Farmer farmer = new Farmer();
        farmer.fillArea(100, 100);
        Kuuntelija kuunt = new Kuuntelija(farmer);
        farmer.addMouseListener(kuunt);

        c.add(farmer, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 5));

        JButton nappi = new JButton("Aloita");
        JButton clrBtn = new JButton("tyhjennä");
        JButton nxt = new JButton("seuraava");
        JComboBox vari = teeVariValitsija();
        JTextField gen = new JTextField("Generation: 0");
        gen.setEditable(false);

        vari.addActionListener(new VariKuuntelija());

        NappiKuuntelija k = new NappiKuuntelija(farmer, kuunt, nappi, clrBtn, nxt, gen);
        nappi.addActionListener(k);
        clrBtn.addActionListener(k);
        nxt.addActionListener(k);

        controlPanel.add(gen);
        controlPanel.add(nappi);
        controlPanel.add(clrBtn);
        controlPanel.add(nxt);
        controlPanel.add(vari);

        c.add(controlPanel, BorderLayout.SOUTH);

    }

    private JComboBox teeVariValitsija() {
        String[] varit = new String[]{"blue","green","yellow", "purple", "red"};
        JComboBox box = new JComboBox(varit);
        return box;
    }

    private class VariKuuntelija implements ActionListener { // to be replaced by some kind of implementation of a dropdown menu  

        private final Map<String, Color> varit;

        public VariKuuntelija() {
            this.varit = new HashMap<>();
            varit.put("blue", Color.blue);
            varit.put("green", Color.green);
            varit.put("yellow", Color.yellow);
            varit.put("purple", Color.magenta.darker());
            varit.put("red", Color.red);

        }

        @Override
        public void actionPerformed(ActionEvent ae){
            JComboBox box = (JComboBox) ae.getSource();
            Color c = this.varit.get((String) box.getSelectedItem());
            Game.this.farmer.setColor(c);
        }
    }

}
