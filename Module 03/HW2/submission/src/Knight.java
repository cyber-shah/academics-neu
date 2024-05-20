/**
 * This class represents a knight in chess.
 * Contains the following methods extending from AbstractChessPiece:
 *      Consturctor Knight
 *      boolean canMove
 *      boolean canKill
 */

public class Knight extends AbstractChessPiece {

  private final Color color;

  /**
   * Constructor for Knight.
   * @param row the row of the piece
   * @param column the column of the piece
   * @param color the color of the piece
   */
  public Knight(int row, int column, Color color) {
    super(row, column, color);
    this.color = color;
    if (row < 0 || row > 7 || column < 0 || column > 7) {
      throw new IllegalArgumentException("Invalid row or column");
    }
  }

  /**
   * This method checks if the knight can move to a certain position.
   * @param row the row of the position
   * @param column the column of the position
   * @return True if the knight can move to the position
   */
  @Override
  public boolean canMove(int row, int column) {
    // check if out of bound or same position
    if (helperChecks(row, column)) {
      return false;
    }
    // check if move is L-shaped
    // difference is 2 and 1
    else if (Math.abs(this.getRow() - row) == 2 && Math.abs(this.getColumn() - column) == 1) {
      return true;
    }
    // difference is 1 and 2
    else {
      return Math.abs(this.getRow() - row) == 1 && Math.abs(this.getColumn() - column) == 2;
    }
  }

  /**
   * This method checks if the knight can kill another piece.
   * @param piece another instance of ChessPiece
   * @return True if the knight can kill another piece
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    // if the piece is not the same color
    if (this.color == piece.getColor()) {
      return false;
    }
    // canMove eventually checks if it's the same position
    return canMove(piece.getRow(), piece.getColumn());
  }
}
