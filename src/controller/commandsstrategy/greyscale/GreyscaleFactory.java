package controller.commandsstrategy.greyscale;

import model.ImageDatabaseInterface;
import model.operations.*;
import model.operations.Filters.GreyscaleFilter;

public class GreyscaleFactory {
  public static OperationInterface createFilter(String[] commandsList,
                                                ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    if (commandsList.length < 4) {
      throw new IllegalStateException("Too few arguments. Must be of the format: \n"
              + " \"greyscale <operation/color> <sourceImageID> <newImageID>\"");
    }

    // 1. Set the sourceImageID and the destinationID.
    String operation = commandsList[1];
    String sourceImageID = commandsList[2];
    String destinationID = commandsList[3];

    if (operation.equalsIgnoreCase("luma")) {
      return new LumaOperation(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("intensity")) {
      return new IntensityOperation(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("value")) {
      return new ValueOperation(imageDatabase.getImage(sourceImageID));
    } else if (operation.equalsIgnoreCase("red")) {
      return new ColorComponentOperation(imageDatabase.getImage(sourceImageID), "red");
    } else if (operation.equalsIgnoreCase("green")) {
      return new ColorComponentOperation(imageDatabase.getImage(sourceImageID), "green");
    } else if (operation.equalsIgnoreCase("blue")) {
      return new ColorComponentOperation(imageDatabase.getImage(sourceImageID), "blue");
    } else {
      throw new IllegalArgumentException("Invalid operation. "
              + "Must be one of: luma, intensity, value, red, green, blue");
    }
  }
}
