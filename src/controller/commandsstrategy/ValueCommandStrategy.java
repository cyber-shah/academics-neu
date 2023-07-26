package controller.commandsstrategy;

import model.image.CustomImageState;
import model.ImageDatabaseInterface;
import model.operations.ValueOperation;

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
    if (commandsList.length < 2) {
      throw new IllegalStateException("Too few arguments.");
    }

    // 1. Validate the value.
    String sourceImageID = commandsList[1];
    String destinationID = commandsList[2];

    // 2. Once all the arguments are validated, call the Value Operation method.
    CustomImageState newImage = new ValueOperation(imageDatabase.getImage(sourceImageID))
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
