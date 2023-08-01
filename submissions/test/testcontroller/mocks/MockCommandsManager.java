package testcontroller.mocks;

import commandmanager.CommandsManagerInterface;
import controller.commandsstrategy.CommandStrategyInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the CommandsManagerInterface.
 * It is used to test the ControllerImplementation class.
 */
public class MockCommandsManager implements CommandsManagerInterface {
  private final StringBuilder log = new StringBuilder();
  private final Map<String, CommandStrategyInterface> commandsMap = new HashMap<>();

  @Override
  public void registerCommand(String commandName, CommandStrategyInterface commandStrategy) {
    // do nothing
  }

  @Override
  public CommandStrategyInterface getCommandStrategy(String[] commandsList)
          throws IllegalArgumentException {
    for (int i = 0; i < commandsList.length; i++) {
      log.append("Args " + i + ": " + commandsList[i] + " ");
    }
    return null;
  }

  @Override
  public String listAllCommands() {
    return null;
  }

  @Override
  public void registerAllCommands() {
    // do nothing
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
