package cs5004.marblesolitaire.view;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;

import java.io.IOException;

public class AbstractTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState model;
  private final Appendable outAppendable;



  /**
   * This method renders the board to the provided data destination.
   * The board should be rendered exactly in the format produced by the toString method above.
   *
   * @throws IOException if transmission of the board to the provided data destination fails.
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      this.outAppendable.append("\n" + this.toString());
    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException("Failed to transmit board data.", e);
    }
  }

  /**
   *  this method can be used to show an arbitrary message.
   *  allowing this view to show messages determined by whoever uses it.
   *
   * @param message the message to be transmitted.
   * @throws IOException if transmission of the board to the provided data destination fails.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.outAppendable.append(message);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException("Failed to transmit message.", e);
    }
  }
}
