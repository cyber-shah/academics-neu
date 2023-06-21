package cs5004.marblesolitaire.view;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;

public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState model;
  private StringBuilder String;
  // 1. constructor
  // 2. toString()
    //2.1 There should be no spaces after the last marble on each row
    //2.2 The string you return should not have a newline at the end of the last line.
  // 3. render

  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    // 1. constructor
    this.String = new StringBuilder();
    this.model = model;
  }

  @Override
  public String toString() {
    // 2. toString()
    int boardSize = model.getBoardSize();

    // for each row
    for (int i = 0; i < boardSize; i++) {
      // iterate over the column - for each column
      for (int j = 0; j < boardSize; j++) {
        // 1. if it is a marble, append 0
        if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          String.append("O");
        }
        // 2. if empty, append "_"
        else if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          String.append("_");
        }
        // 3. if j is less than 2 and it is invalid, append " "
        else if (j <= 2 && model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          String.append(" ");
        }
        // 4. if invalid, skip
        else if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          continue;
        }

        // if it's not in the last position, append a space
        if (j != boardSize - 1 && model.getSlotAt(i, j) != MarbleSolitaireModelState.SlotState.Invalid) {
          String.append(" ");
        }
      }
      // after every row, append a new line if it's not the last row
      if (i != boardSize - 1){
        String.append("\n");
      }
    }
    return String.toString();
  }
}
