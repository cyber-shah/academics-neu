/**
 * ChessPiece interface
 * This interface supports all the basic methods that
 * a chess piece should have.
 */
public interface ChessPiece {

  /**
   * Get the row of the piece.
   * @return the row of the piece
   */
  int getRow();

  /**
   * Get the column of the piece.
   * @return the Column of the piece
   */
  int getColumn();

  /**
   * Returns the color of the chess piece.
   * @return the color of the piece
   */
  Color getColor();

  /**
   * This method checks if the piece can move to a certain position.
   * @param row the row of the position
   * @param column the column of the position
   * @return True if the piece can move to the position
   */
  boolean canMove(int row, int column);

  /**
   * This method checks if the piece can kill another piece.
   * @param piece another instance of ChessPiece
   * @return True if the piece can kill another piece
   */
  boolean canKill(ChessPiece piece);
}