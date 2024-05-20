package view.scripting;

import java.io.IOException;

/**
 * This interface represents a View.
 * It represents a View that can be used to display messages.
 */
public interface ViewInterface {

  /**
   * This method renders the message.
   *
   * @param message The message to be rendered.
   * @throws IOException If the message cannot be rendered.
   */
  void renderMessage(String message) throws IOException;
}
