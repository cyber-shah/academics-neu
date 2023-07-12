package cs5004.marblesolitaire.view;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;

/**
 * This class represents the TriangleSolitaireTextView.
 * It extends the AbstractTextView class.
 * A part of the view.
 * This class is used to render the board in the triangle shape.
 */


public class TriangleSolitaireTextView extends AbstractTextView {

  /**
   * This is the constructor for the TriangleSolitaireTextView.
   *
   * @param model of the type MarbleSolitaireModelState.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
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
  public TriangleSolitaireTextView(MarbleSolitaireModelState model,
                                 Appendable outAppendable) throws IllegalArgumentException {

    super (model, outAppendable);
  }

  public String toString() {
    StringBuilder modelString = new StringBuilder();
    int boardSize = this.inModel.getBoardSize();

    for (int i = 0; i < boardSize; i++) {
      // 1. Render all the white spaces before first marble
      int k = 0;
      while (k < (boardSize - 1 - i)) {
        modelString.append(" ");
        k++;
      }

      for (int j = 0; j < boardSize; j++) {
        // 2. Render the marble
        if (this.inModel.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          modelString.append("O");
        }
        // 3. Render the empty slot
        else if (this.inModel.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          modelString.append("_");
        }

        // 4. Render the white spaces after the marble
        if (j < (boardSize - 1)) {
          if (this.inModel.getSlotAt(i, j + 1) != MarbleSolitaireModelState.SlotState.Invalid)
            modelString.append(" ");
        } else {
          break;
        }
      }

      // 4. Render the new line - except for the last line
      if (i < (boardSize - 1)) {
        modelString.append("\n");
      }
    }
    return modelString.toString();
  }

}
