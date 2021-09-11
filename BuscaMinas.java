/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoBuscaminas;

/**
 *
 * @author sebag
 */
public class BuscaMinas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b=new Board(10);
       // Board.showMatrix(b.getGamingMatrix());
        Board.showMatrix(b.getHiddenMatrix());

    }
    
}
