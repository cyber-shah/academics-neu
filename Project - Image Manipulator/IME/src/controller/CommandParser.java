package controller;

import controller.commandsStrategy.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The CommandParser class is responsible for parsing the user input and returning the appropriate
 * command. The PARSING logic DOES NOT need to change in the future if we add new commands.
 */
public class CommandParser {
  private final Readable inReadable;
  private Appendable outAppendable;
  private final CommandRegistryManager commandRegistryManager;

  public CommandParser(Readable inReadable, Appendable outAppendable) {
    this.inReadable = inReadable;
    this.outAppendable = outAppendable;
    this.commandRegistryManager = new CommandRegistryManager();
    // Register your commands in the command registry
    registerCommands();
  }

  /**
   * Register all commands in the command registry.
   * ---------------------
   * REVIEW:
   * This is the only place where you need to change
   * the code if you add new commands.
   * ---------------------
   */
  private void registerCommands() {
    commandRegistryManager.registerCommand("LOAD", new LoadCommandStrategy());
    commandRegistryManager.registerCommand("VALUE", new ValueCommandStrategy());
    commandRegistryManager.registerCommand("SAVE", new SaveCommandStrategy());
    commandRegistryManager.registerCommand("EXIT", new ExitCommandStrategy());
  }

  public ArrayList<CommandStrategyInterface> parse(String command) {
    Scanner scanner = new Scanner(this.inReadable);
    ArrayList<CommandStrategyInterface> commandStrategies = new ArrayList<CommandStrategyInterface>();

    // 1. Read the command line
    while (scanner.hasNextLine()) {
      String commandLine = scanner.nextLine();

      if (commandLine.startsWith("#")) {
        continue;
      }

      String[] commandParts = commandLine.split(" ");
      String commandTypeString = commandParts[0].toUpperCase();

      CommandStrategyInterface commandStrategyObj = commandRegistryManager.getCommand(commandTypeString);
      if (commandStrategyObj == null) {
        throw new IllegalArgumentException("Invalid command");
      }
      commandStrategies.add(commandStrategyObj);
    }
    return commandStrategies;
  }

}
