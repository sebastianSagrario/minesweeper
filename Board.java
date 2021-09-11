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
    private final int MINES;
    
    
    public Board(int dimension)
    {
        MINES=7;
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
        placeMines(aux);
        generateMinesHints(aux);
        return aux;
    }
    /*
    place the bombs in the board
    */
    private void placeMines(char [][] mat)
    {
        int rowR;
        int colR;
        
        for (int i=0;i<MINES;i++)
        {//buscar alguna forma de que no se repita la posicion en la que voy a clavar la mina//DONE
            do
            {
                rowR=(int) (Math.random()*mat.length);
                colR=(int) (Math.random()*mat.length);                
            }while (mat[rowR][colR]=='X');
            mat[rowR][colR]='X';            
        }
    }
    /*
    generate the hints arround all the mines
    */
    private void generateMinesHints(char [][]mat)
    {
        for (int i=0;i<mat.length;i++)
        {
            for (int j=0;j<mat.length;j++)
            {      
                if (mat[i][j]!='X')
                {
                    placeMineHints(mat,i,j);//it is not working properly //FIXED
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
        iEnd= row==(mat.length-1)? row:row+1;
        jBegin=col==0? col:col-1;
        jEnd= col==(mat.length-1)? col:col+1;
                
        for (int i= iBegin; i<=iEnd;i++)
        {
            for (int j=jBegin;j<=jEnd;j++)
            {
                System.out.println(i+" "+j);
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
         for (char[] fila : mat) {  
             for (char e : fila) {
                System.out.print(e+"|");
            }
            System.out.println("");
        }
    }

    public char[][] getHiddenMatrix() {
        return hiddenMatrix;
    }

    public char[][] getGamingMatrix() {
        return gamingMatrix;
    }
    
    public void reveal()
    {
        Scanner reader=new Scanner (System.in);
        
    }
    
    
}


