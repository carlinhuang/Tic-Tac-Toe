package tictactoe;

public class main {

    /**
     * Run a tictactoe.TicTacToe game interactively on the console.
     *
     * @param args not used
     */
    public static void main(String[] args) {
      TicTacToeModel m = new TicTacToeModel();
      SwingTicTacToeView v = new SwingTicTacToeView("Tic-Tac-Toe");
      v.frame.setVisible(true);
      TicTacToeController c = new SwingTicTacToeController(v, m);
    }
  }
