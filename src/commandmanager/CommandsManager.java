package commandmanager;

import controller.commandsstrategy.CommandStrategyInterface;
import controller.commandsstrategy.filter.FilterCommandStrategy;
import controller.commandsstrategy.greyscale.GreyscaleCommandStrategy;
import controller.commandsstrategy.io.LoadCommandStrategy;
import controller.commandsstrategy.io.SaveCommandStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the single source for CommandManagement all the commands.
 * Commands registered are of the type CommandStrategyInterface.
 * @see CommandStrategyInterface
 */
public class CommandsManager implements CommandsManagerInterface {

  private Map<String, CommandStrategyInterface> commandsMap;

  /**
   * Default constructor, initializes the commandsMap.
   */
  public CommandsManager() {
    commandsMap = new HashMap<>();
  }

  /**
   * This method registers the command passed.
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
    // IO operations --------------------------------------
    commandsMap.put("LOAD", new LoadCommandStrategy());
    commandsMap.put("SAVE", new SaveCommandStrategy());
    // GREYSCALE operations -----------------------------------
    commandsMap.put("GREYSCALE", new GreyscaleCommandStrategy());
    // BRIGHTEN operations ------------------------------------
    commandsMap.put("BRIGHTEN", new controller.commandsstrategy.BrightenCommandStrategy());
    // FILTER ----------------------------------------------------
    commandsMap.put("FILTER", new FilterCommandStrategy());
  }

  /**
   * This method is a getter for the command strategy.
   * @param commandList The command list.
   * @return The command strategy if found, null otherwise.
   */
  @Override
  public CommandStrategyInterface getCommandStrategy(String[] commandList) {
    String commandName = commandList[0].toUpperCase();
    return commandsMap.getOrDefault(commandName, null);
  }

  /**
   * This method converts the commandsMap to a string.
   * @return String of all the commands registered.
   */
  @Override
  public String listAllCommands() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("greyscale <component> <image-id> <new-image-id>\n");
    stringBuilder.append("          components = 1. luma\n");
    stringBuilder.append("                       2. intensity\n");
    stringBuilder.append("                       3. value\n");
    stringBuilder.append("                       4. red\n");
    stringBuilder.append("                       5. green\n");
    stringBuilder.append("                       6. blue\n");
    stringBuilder.append("brighten <factor> <image-id> <new-image-id>\n");
    stringBuilder.append("filter <operation> <image-id> <new-image-id>\n");
    stringBuilder.append("       operations = 1. blur\n");
    stringBuilder.append("                    2. sharpen\n");
    stringBuilder.append("                    3. sepia\n");
    stringBuilder.append("                    4. greyscale\n");
    stringBuilder.append("exit\n");
    stringBuilder.append("list-all-images\n");
    stringBuilder.append("list-all-commands\n");
    return stringBuilder.toString();
  }

}
