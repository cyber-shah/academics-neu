package controller.commandsstrategy;

import model.ImageDatabaseInterface;

/**
 * This interface represents the command strategy for the program.
 * It is responsible for parsing the arguments and then calling the appropriate method on the image.
 * Implements the Command Pattern Design.
 */
public interface CommandStrategyInterface {
  void run(String[] commandsList, ImageDatabaseInterface imageDatabase);
}
