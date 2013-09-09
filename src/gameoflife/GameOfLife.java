/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import javax.swing.SwingUtilities;

/**
 *
 * @author Ilari
 */
public class GameOfLife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = new Game();
        SwingUtilities.invokeLater(game);
    }
}
