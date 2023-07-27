package view;

import java.io.IOException;

/**
 * This interface represents a View.
 * It represents a View that can be used to display messages.
 */
public interface ViewInterface {

  void renderMessage(String message) throws IOException;
}
