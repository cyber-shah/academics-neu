package view;

import java.io.IOException;

/**
 * This interface represents a View.
 * It represents a View that can be used to display messages.
 */
public class ViewImplementation implements ViewInterface {
  private Appendable outAppendable;

  /**
   * This is the constructor for the ViewImplementation class.
   *
   * @param out The output stream.
   */
  public ViewImplementation(Appendable out) {
    this.outAppendable = out;
  }

  /**
   * This method renders the message.
   *
   * @param message The message to be rendered.
   * @throws IOException If the message cannot be rendered.
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

