package controller.commandsstrategy;

import model.ImageDatabaseInterface;

/**
 * This interface represents the command strategy for the program.
 * It is responsible for parsing the arguments and then calling the appropriate method on the image.
 * Implements the Command Pattern Design.
 */
public interface CommandStrategyInterface {

  /**
   * This method runs the command.
   * It parses the arguments and then calls the appropriate method on the image.
   * @see ImageDatabaseInterface
   *
   * @param commandsList the list of commands to run.
   * @param imageDatabase the image database to run the command on.
   */
  void run(String[] commandsList, ImageDatabaseInterface imageDatabase);
}
