package controller.commandsstrategy;

import model.image.CImageState;
import model.ImageDatabaseInterface;
import model.operations.BrightenOperation;

/**
 * This class represents the BrightenCommandStrategy.
 * implements the CommandStrategyInterface.
 * It is responsible for parsing the arguments and then calling the brighten method on the image.
 * Once the image is brightened, it is added to the imageDatabase.
 */
public class BrightenCommandStrategy implements CommandStrategyInterface {

  /**
   * This method parses the arguments and then calls the brighten method on the image.
   * Once the image is brightened, it is added to the imageDatabase.
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

    int value = Integer.parseInt(args[0]);
    String sourceImageID = args[1];
    String destinationID = args[2];

    // Once all the arguments are validated, call the brighten method.
    CImageState newImage = new BrightenOperation(imageDatabase.getImage(sourceImageID), value)
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
    String[] args = new String[3];

    if (commandsList.length < 3) {
      throw new IllegalStateException("Too few arguments.");
    }
    // 1. Validate the sourceImageID.
    args[0] = commandsList[1];
    // 2. Validate the value.
    args[1] = commandsList[2];
    // 3. Validate the newImageID.
    args[2] = commandsList[3];
    return args;
  }
}
