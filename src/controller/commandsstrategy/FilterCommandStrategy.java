package controller.commandsstrategy;

import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import model.operations.Filters.BlurFilter;
import model.operations.Filters.SepiaFilter;
import model.operations.Filters.SharpenFilter;

/**
 * This class is the strategy for the Sepia command.
 * @see CommandStrategyInterface
 */
public class FilterCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 3) {
      throw new IllegalStateException("Must be of the format: \n"
              + " \"filter <operation> <sourceImageID> <newImageID>\"");
    }

    // 1. Set the sourceImageID and the destinationID.
    String operation = commandsList[1];
    String sourceImageID = commandsList[2];
    String destinationID = commandsList[3];

    // 2. Check the operation and apply the filter.
    CustomImageState newImage;
    if (operation.equalsIgnoreCase("blur")) {
      newImage = new BlurFilter(imageDatabase.getImage(sourceImageID))
              .applyOperation();
    }
    else if (operation.equalsIgnoreCase("sharpen")) {
      newImage = new SharpenFilter(imageDatabase.getImage(sourceImageID))
              .applyOperation();
    }
    else if (operation.equalsIgnoreCase("sepia")) {
      newImage = new SepiaFilter(imageDatabase.getImage(sourceImageID))
              .applyOperation();
    }
    else if (operation.equalsIgnoreCase("greyscale")) {
      newImage = new SepiaFilter(imageDatabase.getImage(sourceImageID))
              .applyOperation();
    }
    else {
      throw new IllegalStateException("Invalid operation. "
              + "Must be one of: blur, sharpen, sepia, greyscale");
    }

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }
}
