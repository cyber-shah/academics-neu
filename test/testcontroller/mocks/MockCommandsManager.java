package testcontroller.mocks;

import controller.CommandsManagerInterface;
import controller.commandsstrategy.CommandStrategyInterface;

import java.util.Map;

public class MockCommandsManager implements CommandsManagerInterface {
  private StringBuilder log;
  private Map<String, CommandStrategyInterface> commandsMap;

  @Override
  public void registerCommand(String commandName, CommandStrategyInterface commandStrategy) {
  }

  @Override
  public CommandStrategyInterface getCommandStrategy(String commandName) throws IllegalArgumentException {
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
}
