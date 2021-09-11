/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoBuscaminas;

import java.util.Arrays;

/**
 *
 * @author sebag
 */
public class Board {

    private char [] [] hiddenMatrix;
    private char [] [] gamingMatrix;
    
    
    
    
    
    public Board(int dimension)
    {
        hiddenMatrix=generateHMatrix(dimension);
        gamingMatrix=generateGMatrix(dimension);
    }
    
    private char [][] generateHMatrix(int dim)
    {
        char [][] aux;
        aux = new char[dim][dim];
        for (char[] cs : aux) {
            for (char c : cs) {
                c='0';
            }
        }
        placeBombs(aux);
        generateMinesHints(aux);
        return aux;
    }
    
    
    /*
    place the bombs in the board
    */
    private void placeBombs(char [][] mat)
    {
        int rowR;
        int colR;
        
        for (int i=0;i<10;i++)
        {
            rowR=(int) (Math.random()*5);
            colR=(int) (Math.random()*5);
            mat[rowR][colR]='X';            
        }
    }
    
    
    /*
    generate the hints arround all the mines
    */
    private void generateMinesHints(char [][]mat)
    {
        for (int i=1;i<mat.length-1;i++)
        {
            for (int j=1;j<mat.length-1;j++)
            {      
                if (mat[i][j]!='X')
                {
                    placeMineHints(mat,i,j);//it is not working properly                
                }
            }
        }
    }
    
    /*
    place the hints arround one mine at the specific position
    */
    private void placeMineHints(char [][] mat,int row,int col)
    {
        
        int minesCounter=0;
        int iBegin;
        int iEnd;
        int jBegin;
        int jEnd;
        iBegin= row==0? row:row-1; //this could be faster using nested IFs
        iEnd= row==(mat.length)? row:row+1;
        jBegin=col==0? col:col-1;
        jEnd= col==(mat.length)? col:col+1;
        
        for (int i= row-1; i<=row+1;i++)
        {
            for (int j=col-1;j<col+1;j++)
            {
                if (mat[i][j]=='X')
                {
                    minesCounter++;
                }
                    
            }
        }
        System.out.println(minesCounter+" mc");
        mat[row][col]=(char)(minesCounter +48);            
    }
    
    private char [][] generateGMatrix(int dim)
    {
        char [][] aux;
        aux = new char[dim][dim];
        for (int i=0;i<dim;i++)
        {
            for (int j=0;j<dim;j++)
            {
                aux[i][j]='*';
            }
        }
        return aux;
    }
    
    public static void showMatrix (char [][] mat)
    {
        for (int i=1;i<mat.length-1;i++)
        {
            for (int j = 1; j < mat.length-1; j++) {
                System.out.print(mat[i][j]);                
            }
            System.out.println("");
        }
                   
        
        
        
//        for (char[] fila : mat) {
//            for (char e : fila) {
//                System.out.print(e+" ");
//            }
//            System.out.println("");
//        }
    }

    public char[][] getHiddenMatrix() {
        return hiddenMatrix;
    }

    public char[][] getGamingMatrix() {
        return gamingMatrix;
    }
    
    
}


