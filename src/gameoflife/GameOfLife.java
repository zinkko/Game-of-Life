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
        
        if (args.length==2){ 
            try{     
                String[] bs = args[0].split(","); 
                String[] ss = args[1].split(",");
                int[] b = new int[bs.length];
                int[] s = new int[ss.length];
                for (int i=0;i<bs.length;i++){
                    b[i] = Integer.parseInt(bs[i]);
                }
                for (int i=0;i<ss.length;i++){
                    s[i] = Integer.parseInt(ss[i]);
                }
                Game game = new Game(b,s);
                SwingUtilities.invokeLater(game);
            }catch (Exception ex){ // 
                System.out.println("Error occurred while reading input.\n "
                        + "Correct syntax: java GameOfLife x_1,x_2,...,x_k y_1,y_2,...,y_k\n"
                        + "where x_1 to x_k are for birth and y_1 to y_k for survival\n"
                        + "...or simply no parameters for normal b={3}, s={2,3}");
            }
        }else{ // normal game of life
            Game game = new Game();
            SwingUtilities.invokeLater(game);
        }
        
    }
}
