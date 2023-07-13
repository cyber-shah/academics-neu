package cs5004.marblesolitaire.model.hw05;

import java.util.HashMap;

public class TriangleSolitaireModel extends AbstractModel {

  /**
   * Constructor for TriangleSolitaireModel.
   * Creates a 5 sized board with the empty slot at 0,0.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Constructor for TriangleSolitaireModel.
   * Creates a board of the given size with the empty slot at 0,0.
   *
   * @param boardSize size of the board.
   * @throws IllegalArgumentException if the given boardSize is invalid.
   */
  public TriangleSolitaireModel(int boardSize) throws IllegalArgumentException {
    this(boardSize, 0, 0);
  }

  /**
   * Constructor for TriangleSolitaireModel.
   * Creates a board of size 5 with the empty slot at the given position.
   *
   * @param row row for empty slot.
   * @param col column for empty slot.
   * @throws IllegalArgumentException if the given position is invalid.
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {
    this(5, row, col);
  }

  /**
   * Constructor for TriangleSolitaireModel.
   * Creates a board of the given size with the empty slot at the given position.
   *
   * @param boardSize size of the board.
   * @param row       row for empty slot.
   * @param col       column for empty slot.
   * @throws IllegalArgumentException if the given boardSize or position is invalid.
   */
  public TriangleSolitaireModel(int boardSize, int row, int col) throws IllegalArgumentException {
    super();
    this.boardSize = boardSize;
    this.board = new HashMap<>();

    // check if boardSize is valid
    if (boardSize < 1) {
      throw new IllegalArgumentException("Board size must be a positive number");
    }

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (j > i) {
          board.put(i + "," + j, SlotState.Invalid);
          ;
        } else {
          board.put(i + "," + j, SlotState.Marble);
        }
      }
    }

    // check if position is valid
    if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + col + ")");
    } else if (board.get(row + "," + col) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + col + ")");
    }
    // set empty slot
    board.put(row + "," + col, SlotState.Empty);
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
  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    boolean generalChecks = super.generalChecks(fromRow, fromCol, toRow, toCol);
    boolean checkMoveHorizontal = super.checkMoveHorizontal(fromRow, fromCol, toRow, toCol);
    boolean checkMoveVertical = super.checkMoveVertical(fromRow, fromCol, toRow, toCol);
    boolean checkMoveDiagonal = super.checkMoveDiagonal(fromRow, fromCol, toRow, toCol);

    if (generalChecks && checkMoveHorizontal) {
      return true;
    } else if (generalChecks && checkMoveVertical) {
      return true;
    } else if (generalChecks && checkMoveDiagonal) {
      return true;
    } else {
      return false;
    }
  }


}

