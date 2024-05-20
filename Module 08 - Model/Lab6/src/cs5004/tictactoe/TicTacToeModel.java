package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a tic-tac-toe game Model.
 * It has a 3x3 board and two players X and O.
 */
public class TicTacToeModel implements TicTacToe {

  private final Player[][] board;
  private int x_count;
  private int o_count;


  /**
   * Constructor.
   *
   */
  public TicTacToeModel() {
    x_count = 0;
    o_count = 0;
    // create a new board
    board = new Player[3][3];
    // initialize all values to null
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        board[i][j] = null;
      }
    }
  }

  /**
   * Execute a move in the position specified by the given row and column.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException if the game is over
   */
  public void move(int r, int c) throws IllegalArgumentException, IllegalStateException {
    if (r < 0 || r > 3 || c > 3 || c < 0) {
      throw new IllegalArgumentException("Out of Bounds");
    }
    // 1. verify if game is over
    if (isGameOver()) {
      throw new IllegalStateException("Game is over, cannot move!");
    }
    // 1.1 verify the row and column entered
    else if (board[r][c] != null ) {
      throw new IllegalArgumentException("Space already occupied!");
    }
    // 2. check whose turn is it and add that mark in the row and column
    // if everything passes
    Player this_turn = this.getTurn();
    board[r][c] = this_turn;
    // 3. increase count
    if (this_turn == Player.X) {
      x_count ++;
    }
    else {
      o_count ++;
    }
  }

  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return the {@link Player} whose turn it is
   */
  @Override
  public Player getTurn() {
    // 1. check the total number of X and O
    if (x_count > o_count) {
      return Player.O;
    }
    else if (o_count > x_count) {
      return Player.X;
    }
    // if both are same x plays
    else {
      return Player.X;
    }
  }

  /**
   * Return whether the game is over. The game is over when either the board is full, or
   * one player has won.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    if (this.getWinner() != null) {
      return true;
    }
    // iterate over the entire list
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // if a cell is null, game is not over
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    // if no cell is empty, game over, return true
    return true;
  }

  /**
   * Return the winner of the game, or {@code null} if there is no winner. If the game is not
   * over, returns {@code null}.
   *
   * @return the winner, or null if there is no winner
   */
  @Override
  public Player getWinner() {
    // Check rows for a winner
    for (int i = 0; i < 3; i++) {
      Player firstCell = board[i][0];
      if (firstCell != null && board[i][1] == firstCell && board[i][2] == firstCell) {
        return firstCell;
      }
    }

    // Check columns for a winner
    for (int j = 0; j < 3; j++) {
      Player firstCell = board[0][j];
      if (firstCell != null && board[1][j] == firstCell && board[2][j] == firstCell) {
        return firstCell;
      }
    }

    // Check diagonals for a winner
    Player centerCell = board[1][1];
    if (centerCell != null) {
      if ((board[0][0] == centerCell && board[2][2] == centerCell)
              || (board[0][2] == centerCell && board[2][0] == centerCell)) {
        return centerCell;
      }
    }

    // No winner found
    return null;
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current game board
   */
  @Override
  public Player[][] getBoard() {
    // create a new empty board
    Player[][] new_board = new Player[3][3];
    // copy all values into the new board
    for (int i = 0; i < 3; i++) {
      System.arraycopy(board[i], 0, new_board[i], 0, 3);
    }
    return new_board;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   */
  @Override
  public Player getMarkAt(int r, int c) {
    if (r > 3 || r < 0 || c > 3 || c < 0) {
      throw new IllegalArgumentException("Out of Bounds");
    }
    return board[r][c];
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
    //    *
    //    List<String> rows = new ArrayList<>();
    //    for(Player[] row : getBoard()) {
    //      List<String> rowStrings = new ArrayList<>();
    //      for(Player p : row) {
    //        if(p == null) {
    //          rowStrings.add(" ");
    //        } else {
    //          rowStrings.add(p.toString());
    //        }
    //      }
    //      rows.add(" " + String.join(" | ", rowStrings));
    //    }
    //    return String.join("\n-----------\n", rows);
    //    ***********
  }
}
