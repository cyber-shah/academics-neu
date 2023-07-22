package controller;

import controller.commandsstrategy.CommandStrategyInterface;

/**
 * This interface represents a CommandsManager.
 * It represents a CommandsManager that can be used to register commands.
 */
public interface CommandsManagerInterface {

  void registerCommand(String commandName, CommandStrategyInterface commandStrategy);

  CommandStrategyInterface getCommandStrategy(String commandName)
          throws IllegalArgumentException;

  String listAllCommands();

  void registerAllCommands();
}
