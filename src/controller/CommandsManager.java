package controller;

import controller.commandsstrategy.CommandStrategyInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the single source for CommandManagement all the commands.
 * Commands registered are of the type CommandStrategyInterface.
 * @see CommandStrategyInterface
 */
public class CommandsManager implements CommandsManagerInterface{

  private Map<String, CommandStrategyInterface> commandsMap;

  /**
   * Default constructor, initializes the commandsMap.
   */
  public CommandsManager() {
    commandsMap = new HashMap<>();
  }

  /**
   * This method registers the commands.
   * @param commandName The name of the command.
   * @param commandStrategy The command strategy.
   */
  @Override
  public void registerCommand(String commandName, CommandStrategyInterface commandStrategy) {
    commandsMap.put(commandName, commandStrategy);
  }

  /**
   * This method registers all the commands.
   */
  @Override
  public void registerAllCommands() {
    commandsMap.put("LOAD", new controller.commandsstrategy.LoadCommandStrategy());
    commandsMap.put("SAVE", new controller.commandsstrategy.SaveCommandStrategy());
    commandsMap.put("BRIGHTEN", new controller.commandsstrategy.BrightenCommandStrategy());
    commandsMap.put("LUMA", new controller.commandsstrategy.LumaCommandStrategy());
    commandsMap.put("INTENSITY", new controller.commandsstrategy.IntensityCommandStrategy());
    commandsMap.put("VALUE", new controller.commandsstrategy.ValueCommandStrategy());
    commandsMap.put("COMPONENT", new controller.commandsstrategy.ComponentCommandStrategy());
  }

  /**
   * This method returns the command strategy.
   * @param commandName The name of the command.
   * @return The command strategy if it exists, null otherwise.
   */
  @Override
  public CommandStrategyInterface getCommandStrategy(String commandName)
          throws IllegalArgumentException {
    CommandStrategyInterface command = commandsMap.getOrDefault(commandName, null);
    if (command == null) {
      return null;
    }
    return command;
  }

  /**
   * This method returns a list of all the commands.
   * @return A list of all the commands.
   */
  @Override
  public String listAllCommands() {
    StringBuilder stringBuilder = new StringBuilder();
    for (String command : this.commandsMap.keySet()) {
      stringBuilder.append(command).append("\n");
    }
    stringBuilder.append("EXIT\n");
    stringBuilder.append("LIST-ALL-IMAGES\n");
    stringBuilder.append("LIST-ALL-COMMANDS\n");
    return stringBuilder.toString();
  }

}
