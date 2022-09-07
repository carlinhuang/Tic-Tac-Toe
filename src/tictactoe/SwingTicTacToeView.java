package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import java.awt.*;

/**
 * The view for tic-tac-toe
 */
public class SwingTicTacToeView implements TicTacToeView {
  public JLabel showWinner, wel;
  public JLabel showTurn;
  public JButton[][] cells;
  public JButton exitButton,resetButton;
  public JPanel msgPanel, btnPanel,optPanel;
  public JFrame frame;
  public ArrayList<Integer> playerMove;

  /**
   * This is the constructor of tictactoe Controller
   * @param s
   */
  public SwingTicTacToeView(String s) {

    initBoard();
  }

  /**
   * initializes the board view
   */
  private void initBoard() {
    playerMove = new ArrayList<Integer>();
    btnPanel = new JPanel();
    optPanel = new JPanel();
    cells = new JButton[3][3];
    msgPanel = new JPanel();

    frame = new JFrame("Tic-Tac-Toe");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setResizable(false);

    msgPanel.setSize(400, 50);
    wel = new JLabel("new game");
    msgPanel.add(wel);
    frame.add(msgPanel, BorderLayout.NORTH);

    // 3*3 board JPanel init
    btnPanel.setLayout(new GridLayout(3, 3));
    // initial gamePanel
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        cells[row][col] = new JButton();
        cells[row][col].setBounds(125 + col * 100, 75 + row * 100, 100, 100);
        cells[row][col].setFont(new Font("Sans Serif", Font.PLAIN, 38));
        cells[row][col].setActionCommand("game button");
        btnPanel.add(cells[row][col]);
        btnPanel.setVisible(true);
      }
    }
    frame.add(btnPanel, BorderLayout.CENTER);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("exit button");
    resetButton = new JButton("Play Again");
    resetButton.setActionCommand("reset button");
    optPanel.setLayout(new FlowLayout());
    optPanel.setSize(100,50);
    optPanel.add(exitButton);
    //optPanel.add(resetButton);
    optPanel.setVisible(true);
    frame.add(optPanel,BorderLayout.SOUTH);

    frame.setVisible(true);
    frame.setResizable(true);
  }

  @Override
  public void showWinner(String s) {
    showWinner = new JLabel();
    if (s!=null) {
      showWinner.setText("Game over. " + s + " wins!");
    }
    showWinner.setFont(new Font("Sans Serif", Font.PLAIN, 22));
    msgPanel.add(showWinner);
    showWinner.setVisible(true);
    this.frame.repaint();
  }
  @Override
   public void showTie(){
     showWinner = new JLabel();
     showWinner.setText("Game over. It's a tie!");
     showWinner.setFont(new Font("Sans Serif", Font.PLAIN, 22));
     msgPanel.add(showWinner);
     showWinner.setVisible(true);
     this.frame.repaint();

   }
  @Override
   public void showTurn(String player){
     msgPanel.removeAll();
     showTurn = new JLabel();
    if (Objects.equals(player, "O")) {
      showTurn.setText("It's X's turn");
        }
    if(Objects.equals(player, "X")){
      showTurn.setText("It's O's turn");
    }
     msgPanel.add(showTurn);
     showTurn.setVisible(true);
   }

  ArrayList<Integer> move(ActionEvent e) {
    ArrayList<Integer> playerMove = new ArrayList<Integer>();
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (e.getSource() == cells[row][col]) {
          playerMove.add(row);
          playerMove.add(col);
        }
      }
    }
    return playerMove;
  }
  @Override
  public void playAt(String player, int row, int col) {
    cells[row][col].setText(player);
    cells[row][col].setVisible(true);
    this.frame.repaint();
  }

  @Override
  public void setListener(ActionListener listener) {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        cells[row][col].addActionListener(listener);
      }
    }
    exitButton.addActionListener(listener);
    resetButton.addActionListener(listener);
  }

}
