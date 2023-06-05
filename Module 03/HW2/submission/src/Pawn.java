/**
 * Name : Pranchal Shah.
 * Semester : Summer 2023.
 * This class represents a Pawn in chess.
 * Contains the following methods extending from AbstractChessPiece:
 *      Constructor Pawn
 *      boolean canMove
 *      boolean canKill
 */

public class Pawn extends AbstractChessPiece {

  private final Color color;
  private final int row;
  private final int column;

  /**
   * Constructor for Pawn.
   * @param row the row of the piece
   * @param column the column of the piece
   * @param color the color of the piece
   */
  public Pawn(int row, int column, Color color) {
    super(row, column, color);
    this.row = row;
    this.column = column;
    this.color = color;
    if (row < 0 || row > 7 || column < 0 || column > 7) {
      throw new IllegalArgumentException("Invalid row or column");
    }
  }

  /**
   * This method checks if the pawn can move to a certain position.
   * @param row the row of the position
   * @param column the column of the position
   * @return True if the pawn can move to the position
   */
  public boolean canMove(int row, int column) {
    if (this.row == row && this.column == column) {
      return false;
    } else if (this.column != column) {
      return false;
    } else if (row < 0 || row > 7 || column < 0 || column > 7) {
      return false;
    } else {
      switch (color) {
        case WHITE:
          if (this.row == 6) {
            return false;
          } else {
            if (this.row + 1 == row) {
              return true;
            }
          }
          break;
        case BLACK:
          if (this.row == 0) {
            return false;
          } else {
            if (this.row - 1 == row) {
              return true;
            }
          }
          break;
        default:
          return false;
      }

      return false;
    }
  }


  /**
   * This method checks if the pawn can kill another piece.
   * @param piece another instance of ChessPiece
   * @return True if the pawn can kill another piece
   */
  public boolean canKill(ChessPiece piece) {
    if (this.row == piece.getRow() && this.column == piece.getColumn()) {
      return false;
    }
    else if (this.color == piece.getColor()) {
      return false;
    }
    else {
      switch (color) {
        case WHITE:
          // if the piece is diagonally RIGHT
          if (this.row + 1 == piece.getRow() && this.column + 1 == piece.getColumn()) {
            return true;
          }
          // if the piece is diagonally LEFT
          else {
            return this.row + 1 == piece.getRow() && this.column - 1 == piece.getColumn();
          }
        case BLACK:
          // if the piece is diagonally RIGHT
          if (this.row - 1 == piece.getRow() && this.column + 1 == piece.getColumn()) {
            return true;
          }
          // if the piece is diagonally LEFT
          else {
            return this.row - 1 == piece.getRow() && this.column - 1 == piece.getColumn();
          }
        default:
          return false;
      }
    }
  }


}