package testcontroller.mocks;

import view.ViewInterface;

import java.io.IOException;

/**
 * This class implements the ViewInterface and is used for testing.
 * It has a log that can be used to check if the view is rendering the correct message.
 */
public class MockViewImplementation implements ViewInterface {
  private final StringBuilder log = new StringBuilder();

  @Override
  public void renderMessage(String message) throws IOException {
    String[] messages = message.split(" ");
    for (int i = 0; i < messages.length; i++) {
      log.append("Args " + i + messages[i]);
    }
  }

  /**
   * EXTRA method to get the log.
   *
   * @return the log.
   */
  public String getLog() {
    return log.toString();
  }
}
