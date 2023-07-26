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
    if (!commandsList[0].equals("red") && !commandsList[0].equals("green") && !commandsList[0].equals("blue")) {
      throw new IllegalStateException("Invalid color component.");
    } else if (commandsList.length < 3) {
      throw new IllegalStateException("Too few arguments.");
    }

    // 1. Validate the color component.
    String component = commandsList[1];
    String sourceImageID = commandsList[2];
    String destinationID = commandsList[3];

    // Once all the arguments are validated, call the Color component method.
    CustomImageState newImage = new ColorComponentOperation(
            imageDatabase.getImage(sourceImageID), component).applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
