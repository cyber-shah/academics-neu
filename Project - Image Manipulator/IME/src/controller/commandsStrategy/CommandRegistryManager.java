package controller.commandsStrategy;

import java.util.HashMap;
import java.util.Map;


public class CommandRegistryManager {
  private final Map<String, CommandStrategyInterface> commandsMap;

  /**
   * Constructor.
   */
  public CommandRegistryManager() {
    commandsMap = new HashMap<String, CommandStrategyInterface>();
  }

  /**
   * Register a command.
   *
   * @param name Name of command to register.
   * @param commandStrategy Command to register.
   */
  public void registerCommand(String name, CommandStrategyInterface commandStrategy) {
    if (commandsMap.containsKey(name)) {
      throw new IllegalArgumentException("registerCommand " + name + " already exists.");
    }
    commandsMap.put(name, commandStrategy);
  }

  /**
   * Get a command by name.
   *
   * @param name Name of command to get.
   * @return Command object.
   * @throws IllegalArgumentException If command not found.
   */
  public CommandStrategyInterface getCommand(String name) {
    if (!commandsMap.containsKey(name)) {
      throw new IllegalArgumentException("getCommand " + name + " not found.");
    }
    return commandsMap.get(name);
  }

  /**
   * Unregister a command.
   *
   * @param name Name of command to unregister.
   * @throws IllegalArgumentException If command not found.
   */
  public void unregisterCommand(String name) throws IllegalArgumentException {
    if (!commandsMap.containsKey(name)) {
      throw new IllegalArgumentException("unregisterCommand " + name + " not found.");
    }
    commandsMap.remove(name);
  }

  /**
   * List all commands.
   *
   * @return String of all commands.
   */
  public String listCommands() {
    StringBuilder sb = new StringBuilder();
    for (String key : commandsMap.keySet()) {
      sb.append(key + "\n");
    }
    return sb.toString();
  }

  public Map<String, CommandStrategyInterface> getCommandMap() {
    return commandsMap;
  }
}
