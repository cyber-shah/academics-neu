package controller;

import model.ImageDatabaseInterface;
import controller.commands.CommandStrategyInterface;
import controller.commands.CommandRegistryManager;

import java.util.Map;
import java.util.Scanner;

public class ControllerImplementation implements ControllerInterface {
  private final ImageDatabaseInterface model;
  private final Appendable view;
  private final Readable inReadable;
  private final Map<String, CommandStrategyInterface> commandRegistry;

  /**
   * Default constructor, initializes all fields.
   * @param model IModel object.
   * @param view IView object.
   * @param inReadable Readable object.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public ControllerImplementation(ImageDatabaseInterface model, Appendable view, Readable inReadable)
          throws IllegalArgumentException {
    if (model == null || view == null || inReadable == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.inReadable = inReadable;

    this.commandRegistry = new CommandRegistryManager().getCommandMap();
  }


  private void write(String string) {
    try {
      this.view.append(string);
    } catch (Exception e) {
      throw new IllegalStateException("Could not write to view.");
    }
  }

  public void go() {
    Scanner scanner = new Scanner(this.inReadable);

    while (scanner.hasNextLine()) {
      // 1. Read the command line
      String command = scanner.next();
      // 2. Get the command object from the command registry
      CommandStrategyInterface commandStrategyObject = commandRegistry.getOrDefault(command, null);
      if (commandStrategyObject == null) {
        write("Command not found.");
        continue;
      }

      // 3. Run the command
      try {
        commandStrategyObject.run(scanner, this.model);
      }
      catch (Exception e) {
        write(e.getMessage());
      }
    }
  }
}
