package model;

import model.commands.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * model.CommandRegistry class.
 *
 * <p>Stores all commands and allows for registering, getting, and unregistering commands.
 * Managing commands is done through the model.CommandRegistry.
 */
public class CommandRegistry {
  private Map<String, Command> commands;

  /**
   * Constructor.
   */
  public CommandRegistry() {
    commands = new HashMap<String, Command>();
  }

  /**
   * Register a command.
   *
   * @param name Name of command to register.
   * @param command Command to register.
   */
  public void registerCommand(String name, Command command) {
    if (commands.containsKey(name)) {
      throw new IllegalArgumentException("registerCommand " + name + " already exists.");
    }
    commands.put(name, command);
  }

  /**
   * Get a command by name.
   *
   * @param name Name of command to get.
   * @throws IllegalArgumentException If command not found.
   */
  public void getCommand(String name) {
    if (!commands.containsKey(name)) {
      throw new IllegalArgumentException("getCommand " + name + " not found.");
    }
    commands.get(name).execute();
  }

  /**
   * Unregister a command.
   *
   * @param name Name of command to unregister.
   * @throws IllegalArgumentException If command not found.
   */
  public void unregisterCommand(String name) throws IllegalArgumentException {
    if (!commands.containsKey(name)) {
      throw new IllegalArgumentException("unregisterCommand " + name + " not found.");
    }
    commands.remove(name);
  }

  /**
   * List all commands.
   *
   * @return String of all commands.
   */
  public String listCommands() {
    StringBuilder sb = new StringBuilder();
    for (String key : commands.keySet()) {
      sb.append(key + "\n");
    }
    return sb.toString();
  }
}
