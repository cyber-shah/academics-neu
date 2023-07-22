package testcontroller.mocks;

import commandmanager.CommandsManagerInterface;
import controller.commandsstrategy.CommandStrategyInterface;

import java.util.HashMap;
import java.util.Map;

public class MockCommandsManager implements CommandsManagerInterface {
  private final StringBuilder log = new StringBuilder();
  private final Map<String, CommandStrategyInterface> commandsMap = new HashMap<>();

  @Override
  public void registerCommand(String commandName, CommandStrategyInterface commandStrategy) {
  }

  @Override
  public CommandStrategyInterface getCommandStrategy(String[] commandsList) throws IllegalArgumentException {
    for (int i = 0; i < commandsList.length; i++) {
      log.append("Args " + i + ": " + commandsList[i] + " ");
    }
    return new MockCommandStrategy();
  }

  @Override
  public String listAllCommands() {
    return null;
  }

  @Override
  public void registerAllCommands() {
    commandsMap.put("LOAD", new MockCommandStrategy());
    commandsMap.put("SAVE", new MockCommandStrategy());
    commandsMap.put("BRIGHTEN", new MockCommandStrategy());
    commandsMap.put("LUMA", new MockCommandStrategy());
    commandsMap.put("INTENSITY", new MockCommandStrategy());
    commandsMap.put("VALUE", new MockCommandStrategy());
    commandsMap.put("COMPONENT", new MockCommandStrategy());
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
