package controller;

import model.ImageDatabaseInterface;
import controller.commandsStrategy.CommandStrategyInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class implements the ControllerInterface and is responsible for taking in user input and
 * running the appropriate command.
 * @see ControllerInterface
 * @see ImageDatabaseInterface
 * @see CommandStrategyInterface
 */
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
    this.commandRegistry = new HashMap<>();
  }

  /**
   * Registers all commands.
   */
  private void registerCommands() {
    commandRegistry.put("load", new controller.commandsStrategy.LoadCommandStrategy());
    commandRegistry.put("save", new controller.commandsStrategy.SaveCommandStrategy());
    commandRegistry.put("brighten", new controller.commandsStrategy.BrightenCommandStrategy());
    commandRegistry.put("exit", new controller.commandsStrategy.ExitCommandStrategy());
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
    this.registerCommands();

    while (scanner.hasNextLine()) {
      // 1. Read the command line
      // NOTE : Changed it to next line beacuse next was taking in all extra commands after what was needed.
      // eg. load koala.ppm koala 66272 was valid till koala but it was considering 66272 as a part of the next command.
      String commandList[] = scanner.nextLine().split(" ");

      String command = commandList[0];
      // ignore comments
      if (command.startsWith("#")) {
        continue;
      }

      // 2. Get the command object from the command registry
      CommandStrategyInterface commandStrategyObject = commandRegistry.getOrDefault(command, null);
      if (commandStrategyObject == null) {
        write("Command not found.");
        continue;
      }

      // 3. Run the command
      try {
        commandStrategyObject.run(commandList, this.model);
      }
      catch (Exception e) {
        write(e.getMessage());
      }
    }
  }
}
