package commandmanager;

import controller.commandsstrategy.CommandStrategyInterface;

/**
 * This interface represents a CommandsManager.
 * It represents a CommandsManager that can be used to register commands.
 */
public interface CommandsManagerInterface {

  /**
   * This method registers a command.
   *
   * @param commandName the name of the command.
   * @param commandStrategy the command strategy.
   */
  void registerCommand(String commandName, CommandStrategyInterface commandStrategy);

  /**
   * This method returns the command strategy for the given command name.
   *
   * @param commandsList the list of commands to get the command from.
   * @return the command strategy for the given command name.
   * @throws IllegalArgumentException if the command name is not registered.
   */
  CommandStrategyInterface getCommandStrategy(String[] commandsList)
          throws IllegalArgumentException;

  /**
   * This method returns a string representation of all the commands.
   *
   * @return a string representation of all the commands.
   */
  String listAllCommands();

  /**
   * This method registers all the commands in the class.
   */
  void registerAllCommands();
}
