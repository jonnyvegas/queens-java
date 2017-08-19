
/**
 * Queens.java
 * COMP 182
 * December 4, 2014
 * Prof. Putnam
 * This is a program that assists in solving an 8 queens problem,
 * where the queens are placed in such a way that no other queen can attack
 * another queen.
 * @author Jonathan Villegas
 */
package queens;

/**
 * This is a class that assists in solving an 8 queens problem,
 * where the queens are placed in such a way that no other queen can attack
 * another queen.
 * @author Jonny
 */
public class Queens 
{
    public static final int BOARD_SIZE = 8;
    public static final int EMPTY = 0;
    public static final int QUEEN = 1;
    private int board[][];
    //Constructor. Creates new board.
    public Queens()
    {
        //BOARD_SIZE x BOARD_SIZE board.
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }
    
    /**
     * Clears the board. All squares are set to 0,
     * which indicates no queen is on a space.
     */
    public void clearBoard()
    {
        //Go through rows.
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            //Go through columns.
            for(int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j] = EMPTY;
            }
        }
    }
    
    /**
     * Displays the board. a Q is a queen and a * is an empty space.
     */
    public void displayBoard()
    {
        //Each row.
        for(int i = 0; i < BOARD_SIZE; i++)
        {
            //Each row on its own line.
            System.out.println("");
            //Go through the columns.
            for(int j = 0; j < BOARD_SIZE; j++)
            {
                //Determine whether or not there is a queen on the space.
                if(board[i][j] == QUEEN)
                {
                    System.out.print("Q ");
                }
                else
                {
                    System.out.print("* ");
                }
            }
        }
    }
    
    /**
     * Places the queens on the column indicated in the
     * parameter list. It then places the queens in each
     * consecutive columns.
     * @param column The column to place the queen in.
     * @return If the queen was able to be successfully placed.
     * True if so, false if not.
     */
    public boolean placeQueens(int column)
    {
        //Base case.
        if(column >= BOARD_SIZE)
        {
            return true;
        }
        else
        {
            boolean queenPlaced = false;
            int row = 0;
            //Queen has not been placed and we are not through all the rows.
            while(!queenPlaced && (row < BOARD_SIZE))
            {
                //If it is under attack, move the queen by row.
                if(isUnderAttack(row,column))
                    ++row;
                //Not under attack.
                else
                {
                    //Set the queen.
                    setQueen(row, column);
                    //Recursive call to place queen in next column.
                    queenPlaced = placeQueens(column + 1);
                    //If queen was not placed successfully...
                    if(!queenPlaced)
                    {
                        //...remove and backtrack and try again.
                        removeQueen(row, column);
                        ++row;
                    }
                }
            }
            return queenPlaced;
        }
    }
    
    /**
     * Sets the queen at the parameter for the number and column.
     * @param row The row to set the queen.
     * @param column The column to set the queen.
     */
    private void setQueen(int row, int column)
    {
        //Square row,column was set to have a queen.
        board[row][column] = QUEEN;
    }
    
    /**
     * Removes the queen from the square indicated by row and column.
     * @param row The row for the queen.
     * @param column The column for the queen.
     */
    private void removeQueen(int row, int column)
    {
        //Removes the queen from the row and column.
        board[row][column] = EMPTY;
    }
    
    /**
     * Determines if a queen in a square is under attack. Only checks for
     * horizontals and diagonals to the left because there will be no
     * queens to the right of the queen checked.
     * @param row The row of the queen.
     * @param column The square of the queen.
     * @return True if it can be attacked horizontally or diagonally.
     * False if it cannot.
     */
    private boolean isUnderAttack(int row, int column)
    {
        //Cannot be attacked if it is the first column.
        if(column == 0)
            return false;
        //Check attacks horizontally.
        for(int i = column - 1; i >= 0; i--)
        {
            if(board[row][i] == QUEEN)
                return true;    
        }
        //Check attacks going diagonally up-left.
        for(int i = row - 1, j = column - 1; i > 0 && j > 0; i--, j--)
        {
            if(board[i][j] == QUEEN)
                return true;
        }
        //Check attacks going diagonally down-left.
        for(int i = row + 1, j = column - 1; i < BOARD_SIZE && j > 0; i++, j--)
        {
            if(board[i][j] == QUEEN)
                return true;
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Queens q = new Queens();
        //q.displayBoard();
        //Place the queen in each of the first 8 rows and
        //Get a new solution.
        for(int i = 0; i <= 7; i++)
        {
            q.setQueen(i, 0);
            if(q.placeQueens(1))
                q.displayBoard();
            q.clearBoard();
            System.out.println();
        }
    }
}
