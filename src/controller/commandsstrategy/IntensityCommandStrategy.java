package controller.commandsstrategy;

import model.image.CustomImageState;
import model.ImageDatabaseInterface;
import model.operations.IntensityOperation;

/**
 * This class represents the IntensityCommandStrategy.
 * It is responsible for parsing the arguments and then calling the intensity method on the image.
 */
public class IntensityCommandStrategy implements CommandStrategyInterface {

  /**
   * This method runs the intensity command.
   *
   * @param commandsList The list of commands.
   * @param imageDatabase The image database.
   */
  @Override
  public void run(String[] commandsList,
                  ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 3) {
      throw new IllegalStateException("Too few arguments.");
    }

    // 1. Set the sourceImageID and the destinationID.
    String sourceImageID = commandsList[1];
    String destinationID = commandsList[2];

    // Once all the arguments are validated, call the intensity method.
    CustomImageState newImage = new IntensityOperation(imageDatabase.getImage(sourceImageID))
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
