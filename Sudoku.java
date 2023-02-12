import java.util.*;

public class Sudoku
{
    /*
     * 
     * 
     * Justin P. Strang
     * EECS 1510 Sudoku Project
     * First Commit 02/12/2023
     *      Will begin by getting one board to work
     */
    public static void main(String[] args)
    {
        //Create a scanner to get user input
        Scanner input = new Scanner(System.in);
        //Prompt user for input
        System.out.println("Enter three 9x9 matrices: ");
        //Instantiate board variables which will have input read into
        int[][] boardOne = new int[9][9];
        //Use for loop to read in user input to board variable
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                boardOne[i][j] = input.nextInt();
            }
        }
        //Test print user input
        printBoard(boardOne);
    }

    public static void printBoard(int[][] board)
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}