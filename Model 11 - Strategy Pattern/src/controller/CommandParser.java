package controller;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandParser {
  private final Readable inReadable;
  private Appendable outAppendable;
  private ArrayList<Command> commandsList;

  public CommandParser(Readable inReadable, Appendable outAppendable) {
    this.inReadable = inReadable;
    this.outAppendable = outAppendable;
  }

  public void parse() {
    // 1. Read the command from the user
    Scanner scanner = new Scanner(this.inReadable);

    while (scanner.hasNextLine()) {
      String command = scanner.nextLine();
      // 2. Parse the command
      if (command.startsWith("#")) {
        // ignore comments
        continue;
      }
      String[] commandParts = command.split(" ");
      String commandTypeString = commandParts[0].toUpperCase();

      CommandType commandType = null;
      try {
        commandType = CommandType.valueOf(commandTypeString);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid command");
      }

      switch (commandType) {
        case LOAD -> this.loadImage(commandParts);
        case VALUE -> this.valueImage(commandParts);
        case SAVE -> this.saveImage(commandParts);
        case OVERWRITE -> this.overwriteImage(commandParts);
        case BRIGHTEN -> this.brightenImage(commandParts);
        default -> throw new IllegalArgumentException("Invalid command");
      }
    }
  }

  private void loadImage(String[] commandParts) {
    Command command = new Command(CommandType.LOAD, commandParts[1], commandParts[2], null);
    this.commandsList.add(command);
  }

  private void valueImage(String[] commandParts) {
    Command command = new Command(CommandType.VALUE, commandParts[1], commandParts[2], commandParts[3]);
    this.commandsList.add(command);
  }

  private void saveImage(String[] commandParts) {
    Command command = new Command(CommandType.SAVE, commandParts[1], commandParts[2], commandParts[3]);
    this.commandsList.add(command);
  }

  private void overwriteImage(String[] commandParts) {
    Command command = new Command(CommandType.OVERWRITE, commandParts[1], commandParts[2], null);
    this.commandsList.add(command);
  }

  private void brightenImage(String[] commandParts) {
    Command command = new Command(CommandType.BRIGHTEN, commandParts[1], commandParts[2], commandParts[3]);
    this.commandsList.add(command);
  }

  public ArrayList<Command> getCommandsList() {
    return commandsList;
  }

}



