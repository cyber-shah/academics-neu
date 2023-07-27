package controller.commandsstrategy.colortransformation;

import model.ImageDatabaseInterface;
import model.operations.color.GreyscaleColor;
import model.operations.color.SepiaColor;
import model.operations.OperationInterface;

public class ColorFactory {
  public static OperationInterface createFilter(String operation, String sourceImageID,
                                                ImageDatabaseInterface imageDatabase) {

    if (operation.equalsIgnoreCase("sepia")) {
      return new SepiaColor(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("greyscale")) {
      return new GreyscaleColor(imageDatabase.getImage(sourceImageID));
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: sepia or greyscale");
    }
  }

}
