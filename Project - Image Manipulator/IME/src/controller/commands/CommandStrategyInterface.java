package controller.commands;

import model.ImageDatabaseInterface;

import java.util.Scanner;

/**
 * This interface represents a command that can be run on the model.
 * These use a command strategy pattern to allow for extensibility.
 * -------------------------------
 * NOTE: They call on the model to perform the command.
 * --------------------------------
 */
public interface CommandStrategyInterface {
  void run(Scanner scanner, ImageDatabaseInterface model);

}
