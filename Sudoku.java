import java.util.*;

public class Sudoku
{
    /*  Test Boards
7 2 4 8 6 5 1 3 9 
5 1 9 2 4 3 8 7 6 
3 0 6 7 9 1 5 4 2 
1 7 8 6 2 9 4 5 3 
9 4 3 1 5 8 2 6 7  
6 5 2 3 7 4 9 1 8  
2 3 1 5 8 6 7 9 4  
8 9 5 4 3 7 6 2 1 
4 6 7 9 1 2 3 8 5   
7 2 4 8 6 5 1 3 9 
5 1 9 2 4 3 8 7 6 
3 8 6 7 9 1 5 4 2 
1 7 8 6 2 9 4 0 3 
9 4 3 1 5 8 2 0 7  
6 5 2 3 7 4 9 1 8  
2 3 1 5 8 6 7 9 4  
8 9 5 4 3 7 6 2 1 
4 6 7 9 1 2 3 8 5
7 2 4 8 6 5 1 3 9 
5 1 9 2 4 3 8 7 6 
3 8 6 7 9 1 5 4 2 
1 7 8 6 2 9 4 5 3 
9 4 3 1 5 8 2 6 7  
6 5 2 3 7 4 0 0 8  
2 3 1 5 8 6 7 0 4  
8 9 5 4 3 7 6 2 1 
4 6 7 9 1 2 3 8 5
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0

7 2 4 8 6 5 1 3 9 
5 1 9 2 4 3 8 7 6 
3 8 6 7 9 1 5 4 2 
1 7 8 6 2 9 4 5 3 
9 4 3 1 5 8 2 6 7  
6 5 2 3 0 4 9 1 8  
2 3 1 5 0 6 7 9 4  
8 9 5 4 3 7 6 2 1 
4 6 7 9 1 2 3 8 5 
* 
     * 
     * Justin P. Strang
     * EECS 1510 Sudoku Project
     * Sudoku solver project where the program is read in a number of 9x9 matrices and outputs the numbers needed to solve the matrices
     * First Commit 02/12/2023
     */
    public static void main(String[] args)
    {
        //Create a scanner to get user input
        Scanner input = new Scanner(System.in);
        //Prompt user for input
        System.out.println("Enter 9x9 matrices: ");
        //Initialize boards holder to hold 100 boards
        int[][][] boards = new int[100][9][9];
       
        //While there is input continue looping and assigning values to board squares
        //Crtl+d to tell scanner to stop reading in
        int boardNumber = 0;
        while (input.hasNext())
        {
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    boards[boardNumber][i][j] = input.nextInt();
                }
            }
            boardNumber++;
        }

        //Send board to board solver method
        for (int i = 0; i < boardNumber; i++)
        {
            //Check for board with all 0's
            if (checkLastBoard(boards[i]))
            {
                break;
            }
            //If not last board then solve board
            boardSolver(boards[i]);
        }
        System.out.println("END");

    }

    public static void boardSolver(int[][] board)
    {
        //Check if board is last board (board with all zeros)
        if (checkLastBoard(board))
        {
            return;
        }

        //To solver needs to find squares with missing values, recognize the problem type, check rows and columns 
        //Send board to another method to find the missing values and return what type of problem it is and where the missing values are located
        //Store in int[][] variable where column of three represents the three possible missing values and the row represents the location of the value
        int[][] locationOfMissingValues = new int[4][3];
        locationOfMissingValues = findTypeOfMissingValue(board);

        //Print Check: location[x][1] = row && location[x][2] = column
        //System.out.println(locationOfMissingValues[0][1]);
        //System.out.println(locationOfMissingValues[0][2]);

        //Number of missing values found determines which type of solver the board is sent to
        if (locationOfMissingValues[3][0] == 1)
        {
            fourByFourSolver(board, locationOfMissingValues);
        }
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

    public static boolean checkLastBoard(int[][] board)
    {
        if (board[0][0] == 0 && board[0][1] == 0 && board[0][3] == 0)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    public static int[][] findTypeOfMissingValue(int[][] board)
    {
        //Create array to store missing value locations
        int[][] locationOfMissingValues = new int[4][3];
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

    public static void fourByFourSolver(int[][] board, int[][] locationOfMissingValues)
    {
        //Check one 3x3 box and find missing value for that square
        //Use location of last/4th square in 4x4 grid of missing values as the loner square so that the missing values order is correct for sending missing values to typeThreeSolver
        int lonerRowLocation = locationOfMissingValues[2][1];
        int lonerColumnLocation = locationOfMissingValues[2][2];
        //Solve 3x3 square with target loner square
        int[] lonerLocationAndValue = new int[3];
        lonerLocationAndValue = solve3x3(board, lonerRowLocation, lonerColumnLocation);

        //Print result
        System.out.print("(" + lonerLocationAndValue[0] + "," + lonerLocationAndValue[1] + "," + lonerLocationAndValue[2] + ") ");

        //Update locationOfMissingValues variable so that typeThreeSolver doesn't think 4th value is still missing
        locationOfMissingValues[2][1] = locationOfMissingValues[3][1];
        locationOfMissingValues[2][2] = locationOfMissingValues[3][2];
        locationOfMissingValues[3][0] = 0;

        //Once loner square solved then it's a type three problem
        typeThreeSolver(board, locationOfMissingValues);
    }

    public static void typeThreeSolver(int[][] board, int[][] locationOfMissingValues)
    {
        int[] lonerLocationAndValue = new int[3];
        //Find location of loner square
        lonerLocationAndValue = findLonerSquare(board);

        System.out.print("(" + lonerLocationAndValue[0] + "," + lonerLocationAndValue[1] + "," + lonerLocationAndValue[2] + ") ");

        //Once loner is figured out then its a type two problem
        //Find updated missing values
        locationOfMissingValues = findTypeOfMissingValue(board);
        //Type two solver for remaining two values
        typeTwoSolver(board, locationOfMissingValues);
    }

    public static int[] findLonerSquare(int[][] board)
    {

        int rowLocation = 0;
        int columnLocation = 0;
        int lonerRowLocation = 0;
        int lonerColumnLocation = 0;
        int counter = 0;
        for (int i = 0; i < 9; i++)
        {
            //Need to use counter for iterating rows-need same value for every 3 rows
            if (i < 3)
            {
                counter = 0;
            }
            else if (i >= 3 && i < 6)
            {
                counter = 3;
            }
            else {
                counter = 6;
            }
            int numberMissingInSquare = 0;
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    //Relate position within 3x3 square to overall 9x9 square
                    rowLocation = j + 3 * counter / 3;
                    columnLocation = k + i % 3 * 3;
                    //System.out.println(rowLocation + " " + columnLocation);
                    if (board[rowLocation][columnLocation] == 0)
                    {
                        lonerRowLocation = rowLocation;
                        lonerColumnLocation = columnLocation;
                        numberMissingInSquare++;
                        //System.out.println(rowLocation + " " + columnLocation);
                    }
                }
            }
            //If only one missing value in a 3x3 square then thats the loner
            if (numberMissingInSquare == 1)
            {
                
                break;
            }
        }

        //Once loner square is found create array of values in the 3x3 square to find what the missing value's value is
        int[] lonerLocation = new int[3];
        lonerLocation = solve3x3(board, lonerRowLocation, lonerColumnLocation);
        //System.out.println(lonerRowLocation + " " + lonerColumnLocation);
        return lonerLocation;
    }

    public static int[] solve3x3(int[][] board, int rowLocation, int columnLocation)
    {
        //Found method for searching 3x3 box in the book
        int[] values = new int[9];

        //Find top left square of 3x3 square
        int firstSquareRow = (rowLocation / 3) * 3;
        int firstSquareColumn = (columnLocation / 3) * 3;
        
        //Loop through 3x3 square starting at top-left / first square
        int i = 0; 
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                //Add current iteration to location of top-left square of 3x3 square
                values[i] = board[r + firstSquareRow][c + firstSquareColumn];
                i++;
            }
        }
                
        int[] sortedValues = sort(values);

        int missingValue = 0;
        for (int j = 1; j < 9; j++)
        {
            if (j == 8 && sortedValues[j] == 9)
            {
                missingValue = 8;
                break;
            }
            //Case where 9 is missing value
            if (j == 8 && missingValue == 0 && sortedValues[j] == 8)
            {
                missingValue = 9;
                break;
            }
        
            if (sortedValues[j] != j)
            {
                missingValue = j;
                break;
            }
        }
        
        //System.out.println(rowLocation + " " + columnLocation + " " + missingValue);
        //Place found value into square
        board[rowLocation][columnLocation] = missingValue;

        int[] lonerLocation = {rowLocation, columnLocation, missingValue};
        return lonerLocation;
    }

    public static void typeTwoSolver(int[][] board, int[][] locationOfMissingValues)
    {
        //Arrays to hold values of rows and columns
        //Declare them all but won't need one
        int[] cOne = new int[9];
        int[] cTwo = new int[9];
        int[] rOne = new int[9];
        int[] rTwo = new int[9];

        //If true then two rows && if false then two columns
        //boolean twoRowsOrTwoColumns = findNumberRowsVsColumns(locationOfMissingValues);
        //Locations of missing values
        int rLocationOne = locationOfMissingValues[0][1];
        int rLocationTwo = locationOfMissingValues[1][1];
        int cLocationOne = locationOfMissingValues[0][2];
        int cLocationTwo = locationOfMissingValues[1][2];
        
        //PrintCheck
        //System.out.println(rLocationOne + " " + cLocationOne + " " + rLocationTwo + " " + cLocationTwo);

        //Both on same Column 
        if (cLocationOne == cLocationTwo)
        {
            //Loop through and add present values to row and column arrays
            for (int i = 0; i < 9; i++)
            {
                rOne[i] = board[rLocationOne][i];
                rTwo[i] = board[rLocationTwo][i];
            }
            //Sort column and row arrays
            int[] rOneSorted = sort(rOne);
            int[] rTwoSorted = sort(rTwo);

            //PrintCheck
            /*for (int i = 0; i < 9; i++)
            {
                System.out.println(rOneSorted[i] + " " + rTwoSorted[i]);
            }*/

            //Loop through each row and if value is not present from 1-9 then thats the missing value
            int rOneMissing = 0;
            int rTwoMissing = 0;
            for (int i = 0; i < 9; i++)
            {
                //Edge case where missing value is 9: loop terminates early (do-while loop might work better)
                if (i == 8  && rOneMissing == 0 && rOneSorted[i] == 8)
                {
                    rOneMissing = 9;
                    break;
                }
                //If sorted array value doesn't equal current i then that's the missing value
                if (rOneSorted[i] != i)
                {
                    rOneMissing = i;
                    break;
                }
                
            }

            for (int i = 0; i < 9; i++)
            {
                //Edge case where missing value is 9: loop terminates early
                if (i == 8  && rTwoMissing == 0 && rTwoSorted[i] == 8)
                {
                    rTwoMissing = 9;
                    break;
                }
                //If sorted array value doesn't equal current i then that's the missing value
                if (rTwoSorted[i] != i)
                {
                    rTwoMissing = i;
                    break;
                }
            }

            //Print check
            //System.out.println(rOneMissing + " " + rTwoMissing);
            
            //Place correct value in missing squares
            board[rLocationOne][cLocationOne] = rOneMissing;
            board[rLocationTwo][cLocationOne] = rTwoMissing;

            System.out.print("(" + rLocationOne + "," + cLocationOne + "," + rOneMissing + ") ");
            System.out.print("(" + rLocationTwo + "," + cLocationTwo + "," + rTwoMissing + ") ");
            System.out.println();
        }
        //Both on same row
        else
        {
            //Loop through and add present values to row and column arrays
            for (int i = 0; i < 9; i++)
            {
                cOne[i] = board[i][cLocationOne];
                cTwo[i] = board[i][cLocationTwo];
            }
            //Sort column and row arrays
            int[] cOneSorted = sort(cOne);
            int[] cTwoSorted = sort(cTwo);

            //Loop through each column and if value is not present from 1-9 then thats the missing value
            int cOneMissing = 0;
            int cTwoMissing = 0;
            for (int i = 0; i < 9; i++)
            {
                if (cOneSorted[i] == 8 && i == 8 && cOneMissing == 0)
                {
                    cOneMissing = 9;
                    break;
                }
                if (cOneSorted[i] != i)
                {
                    cOneMissing = i;
                    break;
                }
                
            }

            for (int i = 0; i < 9; i++)
            {
                if (cTwoMissing == 0 && i == 8 && cTwoSorted[i] == 8)
                {
                    cTwoMissing = 8;
                    break;
                }
                if (cTwoSorted[i] != i)
                {
                    cTwoMissing = i;
                    break;
                }
            }

            //Place found value on board to replace missing square
            board[rLocationOne][cLocationOne] = cOneMissing;
            board[rLocationOne][cLocationTwo] = cTwoMissing;

            System.out.print("(" + rLocationOne + "," + cLocationOne + "," + cOneMissing + ") ");
            System.out.print("(" + rLocationOne + "," + cLocationTwo + "," + cTwoMissing + ") ");
            System.out.println();
            //printBoard(board);
        }


        
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
        int missingValue = 0;
        for (int i = 0; i < 9; i++)
        {
            if (i == 8 && missingValue == 0 && sortedColumn[i] == 8)
            {
                missingValue = 9;
                break;
            }
            if (i == 8 && missingValue == 0 && sortedColumn[i] == 9)
            {
                missingValue = 8;
                break;
            }
            //If sortedColumn and sortedRow are both missing a number from 1-9 then the missing number is the missing value
            if (sortedColumn[i] != i && sortedRow[i] != i)
            {
                missingValue = i;
                break;
            }
        }

        //Fix missing value
        board[rowLocation][columnLocation] = missingValue;

        System.out.print("(" + rowLocation + "," + columnLocation + "," + missingValue + ") ");
        System.out.println();
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

    public static boolean findNumberRowsVsColumns(int[][] missingValues)
    {
        //True == rows && false == columns
        boolean twoRowsOrColumns = false;
        if (missingValues[0][1] == missingValues[1][1])
        {
            twoRowsOrColumns = true;
        }

        return twoRowsOrColumns;
    }
}