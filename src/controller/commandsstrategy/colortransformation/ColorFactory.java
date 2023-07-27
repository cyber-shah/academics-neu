package controller.commandsstrategy.colortransformation;

import model.ImageDatabaseInterface;
import model.operations.Filters.BlurFilter;
import model.operations.Filters.GreyscaleFilter;
import model.operations.Filters.SepiaFilter;
import model.operations.Filters.SharpenFilter;
import model.operations.OperationInterface;

public class ColorFactory {
  public static OperationInterface createFilter(String operation, String sourceImageID,
                                                ImageDatabaseInterface imageDatabase) {

    if (operation.equalsIgnoreCase("sepia")) {
      return new SepiaFilter(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("greyscale")) {
      return new GreyscaleFilter(imageDatabase.getImage(sourceImageID));
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: sepia or greyscale");
    }
  }

}
