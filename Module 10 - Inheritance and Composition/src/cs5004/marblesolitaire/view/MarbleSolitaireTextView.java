package cs5004.marblesolitaire.view;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;


/**
 * This class represents the MarbleSolitaireTextView.
 * It implements the MarbleSolitaireView interface.
 * A part of the view.
 */
public class MarbleSolitaireTextView extends AbstractTextView {

  /**
   * This is the constructor for the MarbleSolitaireTextView.
   *
   * @param model of the type MarbleSolitaireModelState.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {

    super(model);
  }

  /**
   * Constructor that takes in two parameters.
   *
   * @param model of the type MarbleSolitaireModelState.
   * @param outAppendable of the type Appendable.
   * @throws IllegalArgumentException if the model or outAppendable is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model,
                                 Appendable outAppendable) throws IllegalArgumentException {

    super(model, outAppendable);
  }


  /**
   * Returns the string representation of the game.
   *
   * @return The string representation of the game.
   */
  @Override
  public String toString() {
    StringBuilder modelString = new StringBuilder();
    int boardSize = inModel.getBoardSize();
    int sideRectangle = (boardSize / 2) - 1;
    // for each row
    for (int i = 0; i < boardSize; i++) {

      // iterate over the column - for each column
      for (int j = 0; j < boardSize; j++) {
        // 1. if it is a marble, append 0
        if (inModel.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          modelString.append("O");
        }
        // 2. if empty, append "_"
        else if (inModel.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          modelString.append("_");
        }
        // 3. for all invalid before square starts, append space
        else if (j <= sideRectangle
                && inModel.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          modelString.append(" ");
        }
        // 4. if invalid, after the square
        else if (inModel.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          continue;
        }

        // logic to append space ------------------------------------
        // if invalid but before side rectangle
        if (j < sideRectangle
                && inModel.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          modelString.append(" ");
        }

        // if it's not in the last position
        if (j != boardSize - 1) {
          // and append a space only if next one is not invalid
          if (inModel.getSlotAt(i, j + 1) == MarbleSolitaireModelState.SlotState.Invalid) {
            continue;
          } else if (inModel.getSlotAt(i, j) != MarbleSolitaireModelState.SlotState.Invalid) {
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

}
