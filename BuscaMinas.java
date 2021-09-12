/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoBuscaminas;

import java.util.Scanner;

/**
 *
 * @author sebag
 */
public class BuscaMinas {

        public static Scanner reader =new Scanner(System.in);
        
        
        public static void main(String[] args) {
        Board b=new Board();
        showBoard(b.getHiddenMatrix());
        game(b);
    }
    
    public static void game(Board b)
    {
        int row;
        int col;
        int op;
        do
        {            
            showBoard(b.getGamingMatrix());            
            System.out.println("row and colum");
            row=reader.nextInt();
            col=reader.nextInt();
            op=menu();
            switch (op)
            {
                case 1: 
                    b.reveal(row, col);
                break;
                case 2:
                    b.putFlag(row, col);
                    break;
                case 3:
                    b.retireFlag(row, col);
                   break;
            }
        }while(! b.getGameOver());        
        
        showBoard(b.getGamingMatrix());            
        if (b.getGameOver())
            {
                System.out.println("you loose game over");
            }
        else
        {
            System.out.println("you win game over");
        }
    }
    
    
    public static int  menu()
    {
        System.out.println("1.reveal point");
        System.out.println("2.put flag");
        System.out.println("3.retire flag");
        return reader.nextInt();
    }
    
    
    public static void showBoard(char [][] board)
    {
         for (char[] e :board) {
                for (char c : e) {
                    System.out.print(c+" ");
                }
                System.out.println("");
            }
    }
}
