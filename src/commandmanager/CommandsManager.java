package commandmanager;

import controller.commandsstrategy.CommandStrategyInterface;

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
    commandsMap.put("LOAD", new controller.commandsstrategy.LoadCommandStrategy());
    commandsMap.put("SAVE", new controller.commandsstrategy.SaveCommandStrategy());
    commandsMap.put("BRIGHTEN", new controller.commandsstrategy.BrightenCommandStrategy());
    commandsMap.put("LUMA", new controller.commandsstrategy.LumaCommandStrategy());
    commandsMap.put("INTENSITY", new controller.commandsstrategy.IntensityCommandStrategy());
    commandsMap.put("VALUE", new controller.commandsstrategy.ValueCommandStrategy());
    commandsMap.put("COMPONENT", new controller.commandsstrategy.ComponentCommandStrategy());
    commandsMap.put("GREYSCALE", new controller.commandsstrategy.GreyscaleCommandStrategy());
    commandsMap.put("SEPIA", new controller.commandsstrategy.SepiaCommandStrategy());
    commandsMap.put("BLUR", new controller.commandsstrategy.BlurCommandStrategy());
    commandsMap.put("SHARPEN", new controller.commandsstrategy.SharpenCommandStrategy());
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
    for (String command : this.commandsMap.keySet()) {
      stringBuilder.append(command).append("\n");
    }
    stringBuilder.append("EXIT\n");
    stringBuilder.append("LIST-ALL-IMAGES\n");
    stringBuilder.append("LIST-ALL-COMMANDS\n");
    return stringBuilder.toString();
  }

}
