
/**
 * This program runs a game called ConnectTextFour. An empty game board is printed off and and the first user will enter which column
 * they wish to play in. Their assigned charcter will be prined in the lowest row of that column. Player two will do they same, except
 * their own character will be placed on the board in the lowest row of the column they enter. A winner is determined when a player 
 * enter a column that is full of characters.
 * 
 * @author (Brice Vollmer) 
 * @version (12/12/2015)
 */
public class ConnecTextFour
{
    public static final int GROWBY = 2,
    BAD_MOVE = 1,
    GOOD_MOVE = 2,
    LOSING_MOVE = 3,
    ZERO_ASCII = 48;

    private Texel[] board; 
    private int numTexels; 

    private int numGameRows, numGameCols;
    /*This method adds the texel objects that display the board peices.
     * @param inRows as an integer, inCols as an integer
     */
    public ConnecTextFour(int inRows, int inCols)
    {
        board = new Texel[GROWBY];
        for (int i = 0; i < inRows; i++)
            add( new Texel(0, i, '|') );
        for (int i = 0; i < inRows; i++)
            add( new Texel(inCols + 1, i, '|') );
        for (int i = 1; i <= inCols; i++)
            add( new Texel(i, inRows, '-') );
        for (int i = 1; i <= inCols; i++)
            add( new Texel(i, inRows + 1, (char)(i + ZERO_ASCII)) );
        add( new Texel(0, inRows, '+') );
        add( new Texel(inCols + 1, inRows, '+') );
        numGameRows = inRows;
        numGameCols = inCols;
    }

    /*This method adds the texel object to the board. If the board is full, the grow method will be called to increase the size.
     * @param a texel object
     */
    private void add(Texel txl) 
    {
        if(numTexels >= board.length)
            grow();
        board[numTexels++] = txl;
    }

    /*This method will grow the board to fit more peices.
     */
    private void grow()
    {
        Texel[] temp = new Texel[numTexels * GROWBY];
        for (int i = 0; i < numTexels; i++)
            temp[i] = board[i];
        board = temp;
    }

    /*This method decides if the move entered is valid, if it is it creates a new texel object and adds it to the board. If not valid,
     * it will return with two options; either it was an invalid column, which gives the player another chance to enter a column, or 
     * it will end the game if the column entered was full of characters already.
     * @param player as a character and whichColumn as an integer
     * @return Good, bad, or losing move.
     */
    public int takeTurn(char player, int whichColumn)
    {
        if(whichColumn > numGameCols || whichColumn < BAD_MOVE)
            return BAD_MOVE;
        int row = getRow(whichColumn);
        if(row != -1)
        {
            Texel txl = new Texel(whichColumn, row, player);
            add(txl);
        }
        else
        {
            System.out.println();
            System.out.println("     !! You cannot play in that column !!\n");
            return LOSING_MOVE;
        }
        return GOOD_MOVE;
    }

    /*The print mehthod will print all the characters in the board object in the correct order to form the game board and characters
     */
    public void print()
    { 
        if (numTexels == 0)
            return;   
        for(int i = 0; i <= numGameRows + 1; i++)
        {
            for(int j = 0; j <= numGameCols + 1; j++)
            {
                if(indexOf(j, i) == -1)
                    System.out.print(" ");
                else if(j <= numGameCols)
                    System.out.print(board[indexOf(j, i)].getCh());
                else
                    System.out.println(board[indexOf(j, i)].getCh());
            }
        }
        System.out.println("\n"); 
    }

    /*This method find the place of the object that has the given coordinates. If it does not exist, it will return -1.
     * @param col as an integer, row as an integer
     * @return the place of the object that matches the given coordinates as an integer
     */
    private  int indexOf(int col, int row)
    {
        for (int i = 0; i < numTexels; i++)
            if ( board[i].getX() == col && board[i].getY() == row)
                return i;  
        return -1;
    }

    /*This method gets the lowest row that the character can be placed in the given column.
     * @param col as an integer
     * @return the row to be used as an integer
     */
    public int getRow(int col)
    {
        for(int i = numGameRows - 1; i >= 0; i--)
        {
            if(indexOf(col, i) == -1)
                return i;    
        }
        return -1;
    }

    public static final int NUM_ROWS = 7,
    NUM_COLS = 9,
    NUM_PLAYERS = 2;

    public static void main(String args[])
    {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("     !! Welcome to ConnecTextFour !!\n");
        ConnecTextFour game = new ConnecTextFour(NUM_ROWS, NUM_COLS);
        game.print();
        char[] players = {'T', 'B'};
        int currentPlayer = 0, col, moveCount = 0;
        char winner = 0;
        while (winner == 0 && moveCount < NUM_ROWS * NUM_COLS)
        {
            System.out.println("The current player is: " + players[currentPlayer]);
            System.out.print("Enter Column Selection: ");
            col = in.nextInt();
            int checker = game.takeTurn(players[currentPlayer], col);
            while(checker == BAD_MOVE)
            {
                System.out.println("\n     !! That is an invalid column !!\n");
                System.out.print("Enter Column Selection: ");
                col = in.nextInt();
                checker = game.takeTurn(players[currentPlayer], col);
            }
            if(checker == LOSING_MOVE)
                winner = 1;
            currentPlayer = (currentPlayer + 1) % NUM_PLAYERS;
            moveCount++;
            game.print();
        }
        System.out.println("\n     !! The winner is player " + players[currentPlayer] + " !!");
    }
}
