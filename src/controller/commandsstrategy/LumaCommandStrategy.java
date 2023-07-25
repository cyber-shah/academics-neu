package controller.commandsstrategy;

import model.image.CustomImageState;
import model.ImageDatabaseInterface;
import model.operations.LumaOperation;

/**
 * This class represents the LumaCommandStrategy class that implements the CommandStrategyInterface.
 * It is responsible for parsing the arguments and then calling the luma method on the image.
 */
public class LumaCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList,
                  ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    String[] args;
    try {
      args = validateArguments(commandsList);
    } catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }

    // 1. Set the sourceImageID and the destinationID.
    String sourceImageID = args[0];
    String destinationID = args[1];

    // 2. Once all the arguments are validated, call the luma method.
    CustomImageState newImage = new LumaOperation(imageDatabase.getImage(sourceImageID))
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }

  /**
   * This method checks if the command is valid.
   *
   * @param commandsList The list of commands.
   * @return True if the command is valid, false otherwise.
   */
  private String[] validateArguments(String[] commandsList)
          throws IllegalStateException {
    String[] args = new String[2];

    if (commandsList.length < 2) {
      throw new IllegalStateException("Too few arguments.");
    }
    // 1. Validate the sourceImageID.
    args[0] = commandsList[1];
    // 2. Validate the newImageID.
    args[1] = commandsList[2];
    return args;
  }
}
