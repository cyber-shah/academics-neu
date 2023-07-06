import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;

/**
 * Mock model class for testing the controller.
 * It is used to test the controller's ability to transmit the information correctly.
 */
public class MockSolitaireModel implements MarbleSolitaireModel {

  private StringBuilder log;

  /**
   * Constructor for the mock model.
   * @param log the log to be used
   */
  public MockSolitaireModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Appends the move to the log.
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(String.format("move (%d, %d) -> (%d, %d)\n", fromRow + 1, fromCol + 1, toRow + 1, toCol + 1));
  }

  /**
   * Returns true if the game is over, false otherwise.
   *
   * @return always returns false
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Returns a int that represents size of the board.
   * @return always returns 13
   */
  @Override
  public int getBoardSize() {
    return 7;
  }

  /**
   * Returns the current state of the game as enum.
   *
   * @return always returns null.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return SlotState.Invalid;
  }

  /**
   * Returns the score.
   *
   * @return always returns 0.
   */
  @Override
  public int getScore() {
    return 0;
  }
}
