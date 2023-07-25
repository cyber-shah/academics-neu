package controller.commandsstrategy;

import model.image.CustomImageState;
import model.ImageDatabaseInterface;
import model.operations.ValueComponentOperation;

/**
 * This class represents the ValueCommandStrategy.
 * It is responsible for parsing the arguments and then calling the value method on the image.
 */
public class ValueCommandStrategy implements CommandStrategyInterface {

  /**
   * This method parses the arguments and then calls the value method on the image.
   *
   * @param commandsList  String[] list of inputs.
   * @param imageDatabase ImageDatabaseInterface object.
   */
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

    // 1. Validate the value.
    String sourceImageID = args[0];
    String destinationID = args[1];

    // 2. Once all the arguments are validated, call the brighten method.
    CustomImageState newImage = new ValueComponentOperation(imageDatabase.getImage(sourceImageID))
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }

  /**
   * This method validates the arguments passed to the command.
   *
   * @param commandsList commandsList object.
   */
  private String[] validateArguments(String[] commandsList) throws IllegalStateException {
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
