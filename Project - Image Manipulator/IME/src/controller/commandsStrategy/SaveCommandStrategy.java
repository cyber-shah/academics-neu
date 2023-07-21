package controller.commandsStrategy;

import controller.IO.ImageSaverInterface;
import controller.IO.PPMImageSaver;
import model.ImageDatabaseInterface;

import java.util.Scanner;

public class SaveCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface model) {
    // 1. Get the name of the file to load from the user.
    String[] args;
    // 0. Validate all the arguments.
    try {
      args = validateArguments(commandsList);
    } catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }

    // 2. Get the ID to be used with the image.
    String sourceImagePath = args[0];
    String imageID = args[1];

    // 3. call the ImageSaver to save the image.
    ImageSaverInterface imageSaver = new PPMImageSaver();
    try {
      imageSaver.save(model.getImage(imageID), sourceImagePath);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * This method validates the arguments passed to the command.
   *
   * @param commandsList String[] a list of commands.
   * @return String[] a list of arguments.
   * @throws IllegalStateException if the arguments are not valid.
   */
  private String[] validateArguments(String[] commandsList) throws IllegalStateException {
    String[] args = new String[2];
    // 1. Validate the sourceImagePath.
    if (commandsList.length < 2) {
      throw new IllegalStateException("sourceImagePath not found.");
    }
    // 2. Set the sourceImagePath
    args[0] = commandsList[1];

    // 3. Validate the imageID.
    if (commandsList.length < 3) {
      throw new IllegalStateException("imageID not found.");
    }
    // 4. Set the imageID.
    args[1] = commandsList[2];
    return args;
  }
}
