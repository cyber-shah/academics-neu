package cs5004.marblesolitaire.model.hw05;

import java.util.HashMap;
import java.util.Map;

public class AbstractSquareSolitaireModel implements MarbleSolitaireModel {
  private final Map<String, SlotState> board;
  private final int boardSize;






  /**
   * Constructs a {@code AbstractSquareSolitaireModel} object.
   *
   * @param armThickness the arm thickness of the board.
   * @param row the row of the empty slot.
   * @param col the column of the empty slot.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number,
   *                                 or the empty cell position is invalid.
   */
  public AbstractSquareSolitaireModel(int armThickness, int row, int col) {
    // check if armThickness is odd
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be an odd number");
    }
    this.boardSize = armThickness + (armThickness - 1) * 2;
    // check if row and col are valid
    if (row < 0 || col < 0 || isInvalidPosition(row, col)) {
      throw new IllegalArgumentException("Invalid empty cell position^^ (" + row + "," + col + ")");
    }
    board = new HashMap<>();
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
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


  /**
   * Tests if the given position is invalid.
   *
   * @param row row to check against.
   * @param col column to check against.
   * @return true if the given position is invalid, false otherwise.
   */
  public boolean isInvalidPosition(int row, int col) {
    int sideRectangle = (boardSize / 3);
    // check if row and col are valid
    if (row < 0 || col < 0 || row > boardSize - 1 || col > boardSize - 1) {
      return true;
    }
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

}
