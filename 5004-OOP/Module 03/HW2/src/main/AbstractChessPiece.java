/**
 * ChessPiece interface
 * This interface supports all the basic methods that a chess piece should have.
 * This class implements the ChessPiece interface.
 * This class contains the following methods:
 *     Constructor AbstractChessPiece
 *     int getRow
 *     int getColumn
 *     Color getColor
 *     boolean canMoveVertically
 *     boolean canMoveHorizontally
 *     boolean canMoveDiagonally
 *     boolean helperChecks
 */

public abstract class AbstractChessPiece implements ChessPiece {

  private final Color color;
  private final int row;
  private final int column;

  /**
   * Constructor for AbstractChessPeice.
   * @param row the row of the piece
   * @param column the column of the piece
   * @param color the color of the piece
   */
  public AbstractChessPiece(int row, int column, Color color) {
    this.row = row;
    this.column = column;
    this.color = color;
  }

  /**
   * Get the row of the piece.
   * @return the row of the piece
   */
  public int getRow() {
    return row;
  }

  /**
   * Get the column of the piece.
   * @return the Column of the piece
   */
  public int getColumn() {
    return column;
  }

  /**
   * Returns the color of the chess piece.
   * @return the color of the piece
   */
  public Color getColor() {
    return color;
  }

  /**
   * This method contains certain checks that are common to all pieces.
   * Before checking if the piece can move to a certain position, this method checks
   * if the position is out of bound or if the position is the same as the current position.
   * @param newRow int new row of the piece
   * @param newColumn int new column of the piece
   * @return True if the position is valid
   */
  public boolean helperChecks(int newRow, int newColumn) {
    // check if out of bound
    if (newRow < 0 || newRow > 7 || newColumn < 0 || newColumn > 7) {
      return true;
    }
    // check if same position
    else {
      return this.row == newRow && this.column == newColumn;
    }
  }

  /**
   * This method checks if the piece can move to a certain position.
   * @param newRow the row of the position
   * @param newColumn the column of the position
   * @return True if the piece can move to the position
   */
  public boolean canMoveDiagonally(int newRow, int newColumn) {
    if (helperChecks(newRow, newColumn)) {
      return false;
    }
    // check diagonal
    else {
      return (Math.abs(this.row - newRow) == Math.abs(this.column - newColumn));
    }
  }

  /**
   * This method checks if the piece can HORIZONTALLY.
   * @param newRow the row of the position
   * @param newColumn the column of the position
   * @return True if the piece can move to the position
   */
  public boolean canMoveHorizontally(int newRow, int newColumn) {
    if (helperChecks(newRow, newColumn)) {
      return false;
    }
    // check horizontal
    else {
      return (this.row == newRow || this.column == newColumn);
    }
  }

  /**
   * This method checks if the piece can move VERTICALLY.
   * @param newRow the row of the position
   * @param newColumn the column of the position
   * @return True if the piece can move to the position
   */
  public boolean canMoveVertically(int newRow, int newColumn) {
    if (helperChecks(newRow, newColumn)) {
      return false;
    }
    // check vertical
    else {
      return (this.row == newRow || this.column == newColumn);
    }
  }
}