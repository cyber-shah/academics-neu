package cs5004.marblesolitaire.model.hw05;

import java.util.HashMap;
import java.util.Map;

public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private Map<String, SlotState> board;
  private int boardSize;

  public EnglishSolitaireModel() {
    this.boardSize = 7;
    board = new HashMap<>();
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        // set top left square and top right square to invalid
        if (isInvalidPosition(i,j)) {
          board.put(i + "," + j, SlotState.Invalid);
        }
        else {
          board.put(i + "," + j, SlotState.Marble);
        }
      }
      // make the centre empty
      board.replace("3,3", SlotState.Empty);
    }
  }

  public EnglishSolitaireModel(int row, int col) {
    if (row < 0 || col < 0 || row > 6 || col > 6 || isInvalidPosition(row, col)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + col + ")");
    }

    this.boardSize = 7;
    board = new HashMap<>();
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        // set top left square and top right square to invalid
        if (isInvalidPosition(i,j)) {
          board.put(i + "," + j, SlotState.Invalid);
        }
        else {
          board.put(i + "," + j, SlotState.Marble);
        }
      }
    }
    // make the given position empty
    board.replace(row + "," + col, SlotState.Empty);
  }

  public boolean isInvalidPosition (int row, int col) {
    int sideRectangle = (boardSize / 2) - 1;

    // top rectangle range
    boolean isTopRectangle = row >= 0 && row < sideRectangle;
    // bottom rectangle range
    boolean isBottomRectangle = row >= (boardSize - sideRectangle) && row < boardSize;
    // Left rectangle range
    boolean isLeftRectangle = col >= 0 && col < sideRectangle;
    // right rectangle range
    boolean isRightRectangle = col >= (boardSize - sideRectangle) && col < boardSize;

    // is top left, or top right
    // or is bottom left or bottom right
    return isTopRectangle && (isLeftRectangle || isRightRectangle)
            || isBottomRectangle && (isLeftRectangle || isRightRectangle);
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
    if (fromCol < 0 || fromCol > boardSize - 1
            || fromRow < 0 || fromRow >  boardSize - 1
            || toCol < 0 || toCol > boardSize - 1
            || toRow < 0 || toRow > boardSize - 1) {
      throw new IllegalArgumentException("Out of Bounds");
    }

    // get the slot states for both coordinates
    SlotState fromSlot = board.get(fromRow + "," + fromCol);
    SlotState toSlot = board.get(toRow + "," + toCol);

    // if either of them is invalid, throw an exception
    if (fromSlot == SlotState.Invalid || toSlot == SlotState.Invalid) {
      throw new IllegalArgumentException("Either from or to is Invalid");
    }

    // if fromSlot is empty or toSlot is NOT empty,
    if (fromSlot == SlotState.Empty || toSlot != SlotState.Empty) {
      throw new IllegalArgumentException("Either from is empty or to is NOT empty");
    }

    // get the middle between from and TO
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;

    // get the slot slate there
    SlotState midSlot = board.get(midRow + "," + midCol);

    // if there is no marble between from and TO, throw exception
    if (midSlot != SlotState.Marble) {
      throw new IllegalArgumentException("No marble in between!");
    }

    // Perform the move by updating the board positions
    board.put(fromRow + "," + fromCol, SlotState.Empty);
    board.put(midRow + "," + midCol, SlotState.Empty);
    board.put(toRow + "," + toCol, SlotState.Marble);
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
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
    return board.get(row + "," + col);
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    return 0;
  }
}
