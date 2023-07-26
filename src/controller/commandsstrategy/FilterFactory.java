package controller.commandsstrategy;

import model.ImageDatabaseInterface;
import model.operations.Filters.*;
import model.operations.OperationInterface;

public class FilterFactory {

  public static OperationInterface createFilter(String operation, String sourceImageID,
                                                ImageDatabaseInterface imageDatabase) {
    if (operation.equalsIgnoreCase("blur")) {
      return new BlurFilter(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("sharpen")) {
      return new SharpenFilter(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("sepia")) {
      return new SepiaFilter(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("greyscale")) {
      return new GreyscaleFilter(imageDatabase.getImage(sourceImageID));
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: blur, sharpen, sepia, greyscale");
    }
  }
}
