package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 * The controller for tic-tac-toe.
 */
public class SwingTicTacToeController implements TicTacToeController, ActionListener {
  private SwingTicTacToeView v;
  private TicTacToeModel m;
  private ArrayList<Integer> playerMove = new ArrayList<Integer>();

  /**
   * This is the constructor of tictactoe Controller
   *
   * @param v the tictactoe.TicTacToeView
   * @param m an tictactoe.TicTacToeModel
   */
  public SwingTicTacToeController(SwingTicTacToeView v, TicTacToeModel m)
      throws IllegalArgumentException {
    if (v == null || m == null) {
      throw new IllegalArgumentException("View and Model aren't set up yet");
    }
    this.v = v;
    this.m = m;
    v.setListener(this);
    v.frame.setVisible(true);
  }

  /**
   * Execute a single game of tic tac toe given a tic tac toe Model. When the game is over, the
   * playGame method ends.
   * @return
   */
    @Override
    public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
        // read from the input textfield
      case "game button":
        while (!m.isGameOver()) {
          JButton source = (JButton) e.getSource();
          source.setEnabled(false);
          playerMove = v.move(e);
          m.move(playerMove.get(0), playerMove.get(1));
          v.playAt(m.getTurn().toString(), playerMove.get(0), playerMove.get(1));

          v.playAt(
              m.getMarkAt(playerMove.get(0), playerMove.get(1)).toString(),
              playerMove.get(0),
              playerMove.get(1));
          v.showTurn(m.getMarkAt(playerMove.get(0), playerMove.get(1)).toString());
          break;
        }
        if (m.isGameOver()){
        v.msgPanel.removeAll();
        if(m.getWinner()!=null){
          v.showWinner(m.getWinner().toString());
          break;
        }
        v.showTie();}
        break;
      case "exit button":
        System.exit(0);
        break;
    }
  }
}




