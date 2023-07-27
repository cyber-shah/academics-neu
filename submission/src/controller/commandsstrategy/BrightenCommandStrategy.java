package controller.commandsstrategy;

import model.image.CustomImageState;
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
    if (commandsList.length < 4) {
      throw new IllegalStateException("Must be of the format: \n"
              + " \"brighten value sourceImageID newImageID\"");
    }

    // 1. Set the arguments to variables.
    int value = Integer.parseInt(commandsList[1]);
    String sourceImageID = commandsList[2];
    String destinationID = commandsList[3];

    // 2. Once all the arguments are validated, call the bright method.
    CustomImageState newImage = new BrightenOperation(imageDatabase.getImage(sourceImageID), value)
            .applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
