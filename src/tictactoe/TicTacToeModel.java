package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This is the model of the tic-tac-toe game
 * It implements the actual functionality of the game and is text-based
 * It uses a board and count of round
 */
public class TicTacToeModel implements TicTacToe {
  private final Player[][] board;
  private int count;

  /**
   * Constructs a tic-tac-toe model and initializes it to the board of the game and the count of round
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.count = 1;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
            row -> " " + Arrays.stream(row).map(
                p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
    /**********
     List<String> rows = new ArrayList<>();
     for(Player[] row : getBoard()) {
     List<String> rowStrings = new ArrayList<>();
     for(Player p : row) {
     if(p == null) {
     rowStrings.add(" ");
     } else {
     rowStrings.add(p.toString());
     }
     }
     rows.add(" " + String.join(" | ", rowStrings));
     }
     return String.join("\n-----------\n", rows);
     ************/
  }

  /**
   * Tells whether the position is on board
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @return whether the position is on board
   */
  boolean notOnBoard(int r, int c){
    return r<0 || r>2 || c<0 || c>2;
  }



  @Override
  public void move(int r, int c) throws IllegalStateException, IllegalArgumentException{
    if (isGameOver()){
      throw new IllegalStateException("Game is over");
    }
    else if (notOnBoard(r,c)){
      throw new IllegalArgumentException("Invalid row or column number");
    }
    else if(board[r][c] != null){
      throw new IllegalArgumentException("Position occupied");
    }

    if (count % 2 != 0){
      board[r][c] = Player.X;
    }
    else{
      board[r][c] = Player.O;
    }
    count ++;
  }

  @Override
  public Player getTurn() {
    if (count % 2 != 0){
      return Player.X;
    }
    else{
      return Player.O;
    }
  }

  @Override
  public boolean isGameOver() {
    if (count == 10) {
      return true;
    }
    if (count <= 5){
      return false;
    }
    return (getWinner() != null);
  }

  @Override
  public Player getWinner() {
    //diagonal
    if ((board[0][0] != null && board[0][0] == board[1][1] && board[0][0] == board[2][2])||
        (board[0][2]!= null && board[0][2] == board[1][1] && board[0][2] == board[2][0]
        )){
      return board[1][1];
    }
    //row
    for (int i=0; i<3; i++){
      if (board[i][0] != null && board[i][0] == board[i][1] && board[i][0] == board[i][2]){
        return board[i][0];
      }
    }
    //column
    for (int j=0; j<3; j++){
      if (board[0][j] != null && board[0][j] == board[1][j] && board[0][j] == board[2][j]){
        return board[0][j];
      }
    }
    return null;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] copyBoard = new Player[3][3];
    for (int i=0; i<3; i++){
      System.arraycopy(board[i], 0, copyBoard[i], 0, 3);
    }
    return copyBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) throws IllegalArgumentException{
    if(notOnBoard(r,c)){
      throw new IllegalArgumentException("Invalid row or column number");
    }
    return board[r][c];
  }
}

