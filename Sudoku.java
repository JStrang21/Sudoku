import java.util.*;

public class Sudoku
{
    /*  Test Board
        7 2 4 8 6 5 1 3 9
        5 1 9 2 4 3 8 7 6
        3 0 6 7 9 1 5 4 2
        1 7 8 6 2 9 4 5 3
        9 4 3 1 5 8 2 6 7
        6 5 2 3 7 4 9 1 8
        2 3 1 5 8 6 7 9 4
        8 9 5 4 3 7 6 2 1
        4 6 7 9 1 2 3 8 5
     * 
     * 
     * Justin P. Strang
     * EECS 1510 Sudoku Project
     * Sudoku solver project where the program is read in a number of 9x9 matrices and outputs the numbers needed to solve the matrices
     * First Commit 02/12/2023
     *      Will begin by getting one board to work
     *      Maybe use int[][][] to put all boards in a single variable
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
        //System.out.println("//////////");
        //printBoard(boardOne);
        
        //Send board to board solver method
        boardSolver(boardOne);

    }

    public static void boardSolver(int[][] board)
    {
        //To solver needs to find squares with missing values, recognize the problem type, check rows and columns 
        //Send board to another method to find the missing values and return what type of problem it is and where the missing values are located
        //Store in int[][] variable where column of three represents the three possible missing values and the row represents the location of the value
        int[][] locationOfMissingValues = new int[3][3];
        locationOfMissingValues = findTypeOfMissingValue(board);
        //If type 1 solve by checking column/row

    }

    public static int[][] findTypeOfMissingValue(int[][] board)
    {
        int[][] locationOfMissingValues = new int[3][3];
        return locationOfMissingValues;
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