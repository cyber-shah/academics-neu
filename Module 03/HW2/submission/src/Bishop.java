/**
 * This class represents a bishop in chess.
 * Contains the following methods extending from AbstractChessPiece:
 *      Consturctor Bishop
 *      boolean canMove
 *      boolean canKill
 */
public class Bishop extends AbstractChessPiece {
  private final Color color;
  /**
   * Constructor for Bishop.
   * @param row the row of the piece
   * @param column the column of the piece
   * @param color the color of the piece
   */

  public Bishop(int row, int column, Color color) {
    super(row, column, color);
    this.color = color;
    if (row < 0 || row > 7 || column < 0 || column > 7) {
      throw new IllegalArgumentException("Invalid row or column");
    }
  }

  /**
   * This method checks if the bishop can move to a certain position.
   * @param row the row of the position
   * @param column the column of the position
   * @return True if the bishop can move to the position
   */
  public boolean canMove(int row, int column) {
    return canMoveDiagonally(row, column);
  }

  /**
   * This method checks if the bishop can kill another piece.
   * @param piece another instance of ChessPiece
   * @return True if the bishop can kill another piece
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    // check if the piece is in the same diagonal
    // and the piece is not the same color
    if (this.color == piece.getColor()) {
      return false;
    }
    return (canMove(piece.getRow(), piece.getColumn()));
  }
}