/**
 * Name : Pranchal Shah.
 * Semester : Summer 2023.
 * This class represents a Rook in chess.
 * Contains the following methods extending from AbstractChessPiece:
 *      Constructor Rook
 *      boolean canMove
 *      boolean canKill
 */

public class Rook extends AbstractChessPiece {

  private final Color color;

  /**
   * Constructor for Rook.
   * @param row the row of the piece
   * @param column the column of the piece
   * @param color the color of the piece
   */
  public Rook(int row, int column, Color color) {
    super(row, column, color);
    this.color = color;
    if (row < 0 || row > 7 || column < 0 || column > 7) {
      throw new IllegalArgumentException("Invalid row or column");
    }
  }

  /**
   * This method checks if the rook can move to a certain position.
   * @param row the row of the position
   * @param column the column of the position
   * @return True if the rook can move to the position
   */
  @Override
  public boolean canMove(int row, int column) {
    return (canMoveVertically(row, column) || canMoveHorizontally(row, column));
  }

  /**
   * This method checks if the rook can kill another piece.
   * @param piece another instance of ChessPiece
   * @return True if the rook can kill another piece
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