package controller.commandsStrategy;

import model.Image.ImageState;
import model.ImageDatabaseInterface;
import model.Operations.IntensityOperation;

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
    String[] args;
    try {
      args = validateArguments(commandsList);
    } catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }

    String sourceImageID = args[0];
    String destinationID = args[1];

    // Once all the arguments are validated, call the intensity method.
    ImageState newImage = new IntensityOperation(imageDatabase.getImage(sourceImageID)).
            applyOperation();

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }

  /**
   * This method checks if the command is valid.
   *
   * @param commandsList The list of commands.
   * @return True if the command is valid, false otherwise.
   */
  private String[] validateArguments(String[] commandsList)
          throws IllegalStateException {
    String[] args = new String[3];

    if (commandsList.length < 2) {
      throw new IllegalStateException("Too few arguments.");
    }
    // 1. Validate the sourceImageID.
    args[0] = commandsList[1];
    // 2. Validate the newImageID.
    args[1] = commandsList[2];
    return args;
  }
}
