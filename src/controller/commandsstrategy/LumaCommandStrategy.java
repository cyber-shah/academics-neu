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
    if (commandsList.length < 3) {
      throw new IllegalStateException("Too few arguments.");
    }

    // 1. Set the sourceImageID and the destinationID.
    String sourceImageID = commandsList[1];
    String destinationID = commandsList[2];

    // 2. Once all the arguments are validated, call the luma method.
    CustomImageState newImage = new LumaOperation(imageDatabase.getImage(sourceImageID))
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
