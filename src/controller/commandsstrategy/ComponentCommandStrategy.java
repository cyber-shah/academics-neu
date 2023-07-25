package controller.commandsstrategy;

import model.image.CustomImageState;
import model.ImageDatabaseInterface;
import model.operations.ColorComponentOperation;

/**
 * This class represents the component command that implements the CommandStrategyInterface.
 * It is responsible for parsing the arguments.
 * Then calling the Component Operation method on the image.
 */
public class ComponentCommandStrategy implements CommandStrategyInterface {

  /**
   * This method parses the arguments and then calls the Component Operation method on the image.
   * Once the image is brightened, it is added to the imageDatabase.
   *
   * @param commandsList  String[] list of inputs.
   * @param imageDatabase ImageDatabaseInterface object.
   */
  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    String[] args;
    try {
      args = validateArguments(commandsList);
    } catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }
    String component = args[0];
    String sourceImageID = args[1];
    String destinationID = args[2];


    // Once all the arguments are validated, call the Color component method.
    CustomImageState newImage = new ColorComponentOperation(
            imageDatabase.getImage(sourceImageID), component).applyOperation();

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

    // 1. Validate the color component.
    args[0] = commandsList[1];
    if (!args[0].equals("red") && !args[0].equals("green") && !args[0].equals("blue")) {
      throw new IllegalStateException("Invalid color component.");
    }

    // 2. Set the ImageID.
    args[1] = commandsList[2];

    // 3. Set the newImageID.
    args[2] = commandsList[3];
    return args;
  }
}
