package cs5004.marblesolitaire.model.hw05;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the English Solitaire Model.
 * It implements the MarbleSolitaireModel interface.
 */
public class EnglishSolitaireModel extends AbstractRectangularModel {

  /**
   * Constructor for EnglishSolitaireModel.
   * Creates a 7 sized board with the empty slot in the centre.
   */
  public EnglishSolitaireModel() {
    this(3, 3);
  }

  /**
   * Constructor for EnglishSolitaireModel.
   * Creates a 7 sized board with the empty slot at the given position.
   *
   * @param row row for empty slot.
   * @param col column for empty slot.
   * @throws IllegalArgumentException if the given position is invalid.
   */
  public EnglishSolitaireModel(int row, int col) throws IllegalArgumentException {
    this(3, row, col);
  }

  /**
   * Constructor for EnglishSolitaireModel.
   * Creates a board with the given boardSize and empty slot in the centre.
   *
   * @param armThickness size of the board.
   * @throws IllegalArgumentException if the given boardSize is invalid.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this (armThickness, (armThickness + (armThickness - 1) * 2) / 2,
            (armThickness + (armThickness - 1) * 2) / 2);
  }
  
  /**
   * Constructor for EnglishSolitaireModel.
   * Creates a board with the given arm thickness and empty slot in the input.
   *
   * @param armThickness arm thickness of the board.
   * @param row row for empty slot.
   * @param col column for empty slot.
   * @throws IllegalArgumentException if the given position is invalid.
   */
  public EnglishSolitaireModel(int armThickness, int row, int col) throws IllegalArgumentException {
    super();

    // assign values to fields
    this.board = new HashMap<>();
    this.boardSize = armThickness + (armThickness - 1) * 2;

    // check if armThickness is odd
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be an odd number");
    }

    // check if row and col are valid
    if (row < 0 || col < 0 || isInvalidPosition(row, col)) {
      throw new IllegalArgumentException("Invalid empty cell position^^ (" + row + "," + col + ")");
    }
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

}
