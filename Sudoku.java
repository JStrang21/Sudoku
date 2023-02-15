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
       
        //Print Check: location[x][1] = row && location[x][2] = column
        //System.out.println(locationOfMissingValues[0][1]);
        //System.out.println(locationOfMissingValues[0][2]);

        //Number of missing values found determines which type of solver the board is sent to
        if (locationOfMissingValues[2][0] == 1)
        {
            typeThreeSolver(board, locationOfMissingValues);
        }
        if (locationOfMissingValues[1][0] == 1 && locationOfMissingValues[2][0] == 0)
        {
            typeTwoSolver(board, locationOfMissingValues);
        }
        if (locationOfMissingValues[0][0] == 1 && locationOfMissingValues[1][0] == 0)
        {
            typeOneSolver(board, locationOfMissingValues);
        }

    }

    public static int[][] findTypeOfMissingValue(int[][] board)
    {
        //Create array to store missing value locations
        int[][] locationOfMissingValues = new int[3][3];
        int numberMissing = 0;
        //Loop over entire board
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                //If square has missing value
                if (board[i][j] == 0)
                {
                    //Set first column = to 1 to indicate missing value
                    locationOfMissingValues[numberMissing][0] = 1;
                    //Second column is the row location
                    locationOfMissingValues[numberMissing][1] = i;
                    //Third column is the column location
                    locationOfMissingValues[numberMissing][2] = j;
                    numberMissing++;
                }
            }
        }
        return locationOfMissingValues;
    }

    public static void typeThreeSolver(int[][] board, int[][] locationOfMissingValues)
    {

    }

    public static void typeTwoSolver(int[][] board, int[][] locationOfMissingValues)
    {

    }

    public static void typeOneSolver(int[][] board, int[][] locationOfMissingValues)
    {
        //Arrays to hold values of row and column of missing value
        int[] column = new int[9];
        int[] row = new int[9];
        
        int columnLocation = locationOfMissingValues[0][2];
        int rowLocation = locationOfMissingValues[0][1];
        //Loop through and add present values to row and column arrays
        for (int i = 0; i < 9; i++)
        {
            row[i] = board[rowLocation][i];
            column[i] = board[i][columnLocation];
        }
        
        //Sort column and row array
        int[] sortedColumn = sort(column);
        int[] sortedRow = sort(row);

        //Print check
        /*for (int i : sortedRow)
        {
            System.out.print(i);
        }
        System.out.println();*/

        //Compare sorted arrays to find value of missing value
    }

    public static int[] sort(int[] array)
    {
        //Bubblesort used to sort arrays
        for (int i = 0; i < array.length - 1; i++)
        {
            for (int j = i + 1; j < array.length; j++)
            {
                //If prior element in array is greater than next element than swap the two
                if (array[i] > array[j])
                {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
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