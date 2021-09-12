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

    private final char [] [] hiddenMatrix;
    private char [] [] gamingMatrix;
    private boolean gameOver;
    
    private final int MINES;
    private final char NOTCHECK='*';
    public Board()
    {
        gameOver=false;
        MINES=10;
        hiddenMatrix=generateHMatrix(10);
        gamingMatrix=generateGMatrix(10);
    }
    
    /*
    generates the actual gameBoard  with the position of the mines and their hints ,this is supossed to be hidden 
    */
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
    /*1
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
                if (mat[i][j]=='X')
                {
                    minesCounter++;
                }                    
            } 
        }        
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
                aux[i][j]=NOTCHECK;
            }
        }
        return aux;
    }
    
    /*
    reveals in the Gaming Matrix the row and the colum in the parameter
    */
    
    public void reveal(int i,int j)
    {        
        if (insideMatrix(i,j))
        {
            if (gameOver)
            {
                System.out.println("game over");
            }
            else 
            {
                if ('0'==hiddenMatrix[i][j])
                {
                    revealZeroes(i,j);
                }
                else
                {
                    gamingMatrix[i][j]=hiddenMatrix[i][j];                    
                }
                gameOver = (hiddenMatrix[i][j]=='X');                    
            }            
        }
        else
        {
            System.out.println("out of boundaries values");
        }
    }
    
    /*
        put a flag at the specified position in the gamming matrix
    */
    public void putFlag(int i,int j)
    {
        if (insideMatrix(i,j))
        {
            gamingMatrix[i][j]='P';///the P look like a flag
                    
        }
        else
        {
            System.out.println("out of boundaries values");        
        }
    }
    
/*
    retire a flag at the specified position
    */
    public void retireFlag(int i,int j)
    {
        if (insideMatrix(i,j) && gamingMatrix[i][j]=='P')
        {
            gamingMatrix[i][j]=NOTCHECK;///the P look like a flag                    
        }
        else
        {
            System.out.println("out of boundaries values");        
        }
    }
    
    
    /*
    reveal all the zeroes in the hidden matrix sourronding a position "row " and "col" 
    */
    
    private void revealZeroes(int row,int col)//i couldnt find an iterative way to doit, but it gets the job done
    {
        if ( insideMatrix(row,col) && hiddenMatrix[row][col]=='0' && gamingMatrix[row][col]==NOTCHECK)
        {
            gamingMatrix[row][col]='0';
            revealZeroes(row,col+1);
            revealZeroes(row,col-1);
            revealZeroes(row+1,col);
            revealZeroes(row-1,col);
        }
    }
    
    /*
    return true if i,j position belongs to the matrix
    */
    private boolean insideMatrix(int i,int j)
    {
        
        return i>-1 && i<hiddenMatrix.length && j>-1 && j<hiddenMatrix.length;
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
    
    public void setGameOver()
    {
        gameOver=true;
    }
    
    public boolean getGameOver()
    {
        return gameOver;
    }
    
    
    
    
}


