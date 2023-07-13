package cs5004.marblesolitaire.view;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;

import java.io.IOException;

/**
 * This class represents the AbstractTextView.
 * It implements the MarbleSolitaireView interface.
 */
public class AbstractTextView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState inModel;
  protected Appendable outAppendable;


  /**
   * This is the constructor for the AbstractTextView.
   * Defaults appendable to System.out.
   *
   * @param inModel of the type MarbleSolitaireModelState.
   * @throws IllegalArgumentException if the model is null.
   */
  public AbstractTextView(MarbleSolitaireModelState inModel) throws IllegalArgumentException {
    if (inModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.inModel = inModel;
    this.outAppendable = System.out;
  }


  /**
   * This is the constructor for the AbstractTextView.
   *
   * @param inModel of the type MarbleSolitaireModelState.
   * @param outAppendable of the type Appendable.
   * @throws IllegalArgumentException if the model or outAppendable is null.
   */
  public AbstractTextView(MarbleSolitaireModelState inModel,
                          Appendable outAppendable) throws IllegalArgumentException {
    if (inModel == null || outAppendable == null) {
      throw new IllegalArgumentException("Appendable or model cannot be null");
    }
    this.inModel = inModel;
    this.outAppendable = outAppendable;
  }

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
