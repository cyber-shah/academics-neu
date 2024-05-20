package cs5004.marblesolitaire.model.hw07;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the Abstract Model.
 * It implements the MarbleSolitaireModel interface.
 * It is used to store the common fields and methods of the different models.
 */
public class AbstractModel implements MarbleSolitaireModel {
  protected Map<String, SlotState> board;
  protected int boardSize;

  /**
   * Constructor for AbstractRectangularModel.
   * Defaults to a 3x3 board with the empty slot in the centre.
   * New hashmap is created to represent the board.
   */
  public AbstractModel() {
    this.board = new HashMap<>();
    this.boardSize = 7;
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    try {
      // check if move is valid
      boolean validMove = isValidMove(fromRow, fromCol, toRow, toCol);

      // if move is valid and general checks are passed, update the board
      if (validMove) {
        // get the mid-point
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;

        // Perform the move by updating the board positions
        board.put(fromRow + "," + fromCol, SlotState.Empty);
        board.put(midRow + "," + midCol, SlotState.Empty);
        board.put(toRow + "," + toCol, SlotState.Marble);
      }
      else {
        throw new IllegalArgumentException("Invalid move");
      }
    }
    // else print what went wrong
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Tests whether there is a valid move that can be made from the given position.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @return true if there is a valid move that can be made from the given position.
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    try {
      boolean generalChecks = generalChecks(fromRow, fromCol, toRow, toCol);
      boolean checkMoveHorizontal = checkMoveHorizontal(fromRow, fromCol, toRow, toCol);
      boolean checkMoveVertical = checkMoveVertical(fromRow, fromCol, toRow, toCol);

      // check if move horizontal
      if (generalChecks && checkMoveHorizontal) {
        return true;
      }
      // else check if move vertical
      else if (generalChecks && checkMoveVertical) {
        return true;
      }
      else {
        throw new IllegalArgumentException("Invalid Move");
      }

    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * General checks to verify if a move is valid.
   *
   * @param fromRow the row number of the position to be moved from.
   * @param fromCol the column number of the position to be moved from.
   * @param toRow  the row number of the position to be moved to.
   * @param toCol the column number of the position to be moved to.
   * @return true if the horizontal move is valid.
   */
  protected boolean generalChecks(int fromRow, int fromCol, int toRow, int toCol) {
    SlotState fromSlot = board.get(fromRow + "," + fromCol);
    SlotState toSlot = board.get(toRow + "," + toCol);
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;
    SlotState midSlot = board.get(midRow + "," + midCol);


    // 1. check if the move is on board
    if (fromCol < 0 || fromCol > boardSize - 1
            || fromRow < 0 || fromRow >  boardSize - 1
            || toCol < 0 || toCol > boardSize - 1
            || toRow < 0 || toRow > boardSize - 1) {
      throw new IllegalArgumentException("Out of Bounds");
    }

    // 2. if from or to is invalid, throw an exception
    else if (fromSlot == SlotState.Invalid || toSlot == SlotState.Invalid) {
      throw new IllegalArgumentException("Either from or to is Invalid");
    }

    // 3. Check if the current position contains a marble and the destination is empty
    else if (fromSlot != SlotState.Marble || toSlot != SlotState.Empty) {
      throw new IllegalArgumentException("From is not a marble and ");
    }

    // 4. check if marble in between
    else if (midSlot != SlotState.Marble) {
      throw new IllegalArgumentException("No marble in between!");
    }

    return true;
  }

  /**
   * Checks to verify if a move is valid horizontally.
   *
   * @param fromRow the row number of the position to be moved from.
   * @param fromCol the column number of the position to be moved from.
   * @param toRow the row number of the position to be moved to.
   * @param toCol the column number of the position to be moved to.
   * @return true if the horizontal move is valid.
   */
  protected boolean checkMoveHorizontal(int fromRow, int fromCol, int toRow, int toCol) {
    return fromCol == toCol && Math.abs(toRow - fromRow) == 2;
  }

  /**
   * Checks to verify if a move is valid vertically.
   *
   * @param fromRow the row number of the position to be moved from.
   * @param fromCol the column number of the position to be moved from.
   * @param toRow the row number of the position to be moved to.
   * @param toCol the column number of the position to be moved to.
   * @return true if the vertical move is valid.
   */
  protected boolean checkMoveVertical(int fromRow, int fromCol, int toRow, int toCol) {
    return fromRow == toRow && Math.abs(toCol - fromCol) == 2;
  }

  /**
   * Checks to verify if a move is valid diagonally.
   *
   * @param fromRow the row number of the position to be moved from.
   * @param fromCol the column number of the position to be moved from.
   * @param toRow the row number of the position to be moved to.
   * @param toCol the column number of the position to be moved to.
   * @return true if the diagonal move is valid.
   */
  protected boolean checkMoveDiagonal(int fromRow, int fromCol, int toRow, int toCol) {
    return Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (board.get(i + "," + j) == SlotState.Marble) {
          if (hasValidMove(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Determines if a given position has a valid move.
   * ***** It calls isValidMove() for each direction. *****
   *
   * @param i the row of the position.
   * @param j the column of the position.
   * @return true if the position has a valid move, false otherwise.
   */
  private boolean hasValidMove(int i, int j) {
    int[][] directions = {{0, -2}, {0, 2}, {-2, 0}, {2, 0}, {-2, -2}, {-2, 2}, {2, -2}, {2, 2}};

    // for each direction in directions
    for (int[] direction : directions) {
      try {
        if (isValidMove(i, j, i + direction[0], j + direction[1])) {
          return true;
        }
      } catch (IllegalArgumentException e) {
        // Do nothing
      }
    }

    return false;
  }


  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.boardSize;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    // check if row and col are in bounds
    if (row < 0 || row > boardSize - 1 || col < 0 || col > boardSize - 1) {
      throw new IllegalArgumentException("Out of Bounds");
    }
    else {
      return board.get(row + "," + col);
    }
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        SlotState slot = board.get(i + "," + j);
        if (slot == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}