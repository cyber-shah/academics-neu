package controller;

import commandmanager.CommandsManagerInterface;
import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import view.ViewInterface;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements the ControllerInterface and is responsible for taking in user input and
 * running the appropriate command.
 * @see ControllerInterface
 * @see ImageDatabaseInterface
 * @see CommandStrategyInterface
 */
public class ControllerImplementation implements ControllerInterface {
  private final ImageDatabaseInterface imageDatabase;
  private final ViewInterface view;
  private final Readable inReadable;
  private final CommandsManagerInterface commandsManager;

  /**
   * Default constructor, initializes all fields.
   * @param imageDatabase IModel object.
   * @param view IView object.
   * @param inReadable Readable object.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public ControllerImplementation(ImageDatabaseInterface imageDatabase,
                                  ViewInterface view, Readable inReadable,
                                  CommandsManagerInterface register)
          throws IllegalArgumentException {
    if (imageDatabase == null || view == null || inReadable == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    this.imageDatabase = imageDatabase;
    this.view = view;
    this.inReadable = inReadable;
    this.commandsManager = register;
  }


  /**
   * This method starts the controller.
   * It takes in user input and runs the appropriate command.
   */
  public void runProgram() {
    Scanner scanner = new Scanner(this.inReadable);
    this.commandsManager.registerAllCommands();

    // 0. Greet and kickoff the program.
    try {
      view.renderMessage("-----------------------------------------\n");
      view.renderMessage("Welcome to the Image Manipulation Program.\n");
      view.renderMessage("Enter a command or type \"list-all-commands\" to see all commands.\n"
              + "Type \"exit\" to exit the program.\n"
              + "Enter \"#\" before a command to comment it out.\n");
      view.renderMessage("Enter a command: \n");
      view.renderMessage(">>>");
    }
    catch (IOException e) {
      throw new IllegalStateException("Failed to transmit message.", e);
    }

    // Start reading commands
    while (scanner.hasNextLine()) {
      // 1. Read the command line
      String[] commandList = scanner.nextLine().split(" ");

      String command = commandList[0];
      command = command.toUpperCase();
      // BASIC COMMANDS THAT DO NOT REQUIRE A COMMAND OBJECT
      try {
        if (command.startsWith("#")) {
          continue;
        } else if (command.equals("EXIT")) {
          System.exit(0);
        } else if (command.equals("LIST-ALL-IMAGES")) {
          view.renderMessage(imageDatabase.getAllImageNames() + "\n>>>");
          continue;
        } else if (command.equals("LIST-ALL-COMMANDS")) {
          view.renderMessage(this.commandsManager.listAllCommands() + "\n>>>");
          continue;
        }
      } catch (IOException e) {
        throw new IllegalStateException("Failed to transmit message.", e);
      }

      // 2. Get the command object from the command registry
      CommandStrategyInterface commandStrategyObject =
              commandsManager.getCommandStrategy(commandList);
      if (commandStrategyObject == null) {
        try {
          view.renderMessage("Command: " + command + " not found.\n");
        } catch (IOException e) {
          throw new IllegalStateException("Failed to transmit message. Command not found.\n");
        }
        continue;
      }

      // 3. Run the command
      try {
        commandStrategyObject.run(commandList, this.imageDatabase);
        view.renderMessage("Command " + command + " executed successfully.\n\n>>>");
      } catch (Exception e) {
        try {
          view.renderMessage(e.getMessage() + "\n>>>");
        } catch (Exception e1) {
          e1.printStackTrace();
          throw new IllegalStateException("Failed to transmit message.", e1);
        }
      }
    }
  }

}
