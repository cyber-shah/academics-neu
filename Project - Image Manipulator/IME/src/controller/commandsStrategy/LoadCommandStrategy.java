package controller.commandsStrategy;

import controller.IO.ImageLoaderInterface;
import controller.IO.PPMImageLoader;
import model.Image.ImageState;
import model.ImageDatabaseInterface;

import java.io.IOException;
import java.util.Scanner;

public class LoadCommandStrategy implements CommandStrategyInterface {

  /**
   * This method runs the command.
   *
   * @param commandsList    String[] a list of commands.
   * @param imageDatabase   ImageDatabaseInterface object.
   */
  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
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

    // 3. call the ImageLoader to load the image.
    ImageState newImage;
    try {
      // HACK: This is a hack. We need to use the ImageLoaderFactory to get the correct
      // ImageLoader. in the future if more format are added, we need to use the factory.
      ImageLoaderInterface imageLoader = (ImageLoaderInterface) new PPMImageLoader();
      newImage = imageLoader.load(sourceImagePath);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    // 4. imageDatabase returns an imagestate object.
    // 5. add the imagestate object to the database.
    imageDatabase.addImage(imageID, newImage);
  }

  /**
   * This method validates the arguments passed to the command.
   *
   * @param commandsList String[] a list of commands.
   */
  private String[] validateArguments(String[] commandsList) throws IllegalStateException {
    String[] args = new String[2];
    // 1. Validate the sourceImagePath.
    if (commandsList.length < 2) {
      throw new IllegalStateException("sourceImagePath not found.");
    }
    args[0] = commandsList[1];
    args[1] = commandsList[2];
    return args;
    /*
    // 1. Validate the sourceImagePath.
    if (!scanner.hasNext()) {
      throw new IllegalStateException("sourceImagePath not found.");
    }
    args[0] = scanner.next();

    // 2. Validate the imageName
    if (!scanner.hasNext()) {
      throw new IllegalStateException("imageID not found.");
    }
    args[1] = scanner.next();

//    // 3. Validate that there are no more arguments.
//    if (scanner.hasNext()) {
//      throw new IllegalStateException("Too many arguments.");
//    }
    return args;*/
  }
}
