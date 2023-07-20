package controller;

import model.commands.*;

/**
 * The CommandParser class is responsible for parsing the user input and returning the appropriate
 * command. The PARSING logic DOES NOT need to change in the future if we add new commands.
 */
public class CommandParser {
  private final Readable inReadable;
  private Appendable outAppendable;
  private CommandRegistryManager commandRegistryManager;



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
    commandRegistryManager.registerCommand("LOAD", new Load());
    commandRegistryManager.registerCommand("VALUE", new Value());
    commandRegistryManager.registerCommand("SAVE", new Save());
  }


  public Command parse(String command) {
    // TODO implement here
    return null;
  }
}
