package cs5004.marblesolitaire.view;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;

import java.io.IOException;

/**
 * This class represents the MarbleSolitaireTextView.
 * It implements the MarbleSolitaireView interface.
 * A part of the view.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState model;
  private final StringBuilder modelString;

  /**
   * This is the constructor for the MarbleSolitaireTextView.
   *
   * @param model of the type MarbleSolitaireModelState.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    // 1. constructor
    this.modelString = new StringBuilder();
    this.model = model;
  }

  /**
   * Returns the string representation of the game.
   *
   * @return The string representation of the game.
   */
  @Override
  public String toString() {
    // 2. toString()
    int boardSize = model.getBoardSize();
    int sideRectangle = (boardSize / 2) - 1;
    // for each row
    for (int i = 0; i < boardSize; i++) {

      // iterate over the column - for each column
      for (int j = 0; j < boardSize; j++) {
        // 1. if it is a marble, append 0
        if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          modelString.append("O");
        }
        // 2. if empty, append "_"
        else if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          modelString.append("_");
        }
        // 3. for all invalid before square starts, append space
        else if (j <= sideRectangle
                && model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          modelString.append(" ");
        }
        // 4. if invalid, after the square
        else if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          continue;
        }

        // logic to append space ------------------------------------
        // if invalid but before side rectangle
        if (j < sideRectangle
                && model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          modelString.append(" ");
        }

        // if it's not in the last position
        if (j != boardSize - 1) {
          // and append a space only if next one is not invalid
          if (model.getSlotAt(i, j + 1) == MarbleSolitaireModelState.SlotState.Invalid) {
            continue;
          } else if (model.getSlotAt(i, j) != MarbleSolitaireModelState.SlotState.Invalid) {
            modelString.append(" ");
          }
        }
      }
      // after every row, append a new line if it's not the last row
      if (i != boardSize - 1) {
        modelString.append("\n");
      }
    }
    return modelString.toString();
  }

  @Override
  public void renderBoard() throws IOException {

  }

  @Override
  public void renderMessage(String message) throws IOException {

  }
}
