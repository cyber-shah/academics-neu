package controller;

import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import view.scripting.ViewInterface;

/**
 * This interface represents the controller for the program.
 * It is responsible for taking in user input and running the appropriate command.
 */
public interface ControllerInterface {

  /**
   * This method starts the controller.
   * It takes in user input and runs the appropriate command.
   * @see ImageDatabaseInterface
   * @see CommandStrategyInterface
   * @see ViewInterface
   */
  void runProgram();
}
