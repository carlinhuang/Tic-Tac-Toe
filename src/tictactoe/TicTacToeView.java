package tictactoe;
import java.awt.event.ActionListener;

/**
 * The interface for our view class
 */
public interface TicTacToeView {

  /**
   * Set the listener for any actions.
   */
  void setListener(ActionListener listener);

  /**
   * Shows the winner of the game
   * @param player a string that represents the winning player
   */
  void showWinner(String player);

  /**
   * Shows the tie message of the game
   */
  void showTie();

  /**
   * Shows the turn of the current move
   * @param player a string that represents the player
   */
  void showTurn(String player);


  /**
   * Shows in view when play at that position
   * @param player a string that represents the player
   * @param row row number of the position
   * @param col column number of the position
   */
  void playAt(String player, int row, int col);
}
