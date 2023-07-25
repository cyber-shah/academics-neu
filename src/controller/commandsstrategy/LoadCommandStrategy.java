package controller.commandsstrategy;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.CImageState;
import model.ImageDatabaseInterface;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class represents the load command strategy for the program.
 * It is responsible for parsing the arguments and then calling the appropriate method on the image.
 */
public class LoadCommandStrategy implements CommandStrategyInterface {

  /**
   * This method runs the command.
   * NOTE: this command calls on the ImageLoader from IO
   *       to load the image.
   *
   * @param commandsList    String[] a list of commands.
   * @param imageDatabase   ImageDatabaseInterface object.
   */
  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
    // Typical command line argument
    // load image-name image-destination format

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
    String format = args[2];

    if (format == SupportedFormats.JPG) {
      ImageLoaderInterface imageLoader = (ImageLoaderInterface) new JPGImageLoader();
    }
    else if (format == SupportedFormats.PNG) {
      ImageLoaderInterface imageLoader = (ImageLoaderInterface) new PNGImageLoader();
    }
    else if (format == SupportedFormats.PPM) {
      try {
        // 3. call the ImageLoader to load the image.
        CImageState newImage;
        // OPTIMIZE: We need to use the ImageLoaderFactory to get the correct ImageLoader.
        //           In the future if more format are added..
        ImageLoaderInterface imageLoader = (ImageLoaderInterface) new PPMImageLoader();
        newImage = imageLoader.load(sourceImagePath);
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
    else {
      throw new IllegalArgumentException("Format not supported!");
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
    // Typical command line argument
    // load image-name image-destination format

    String[] args = new String [3];
    if (commandsList.length < 3) {
      String message = "Please provide the command in the format"
              + "load <image-name> <image-destination> <format>";
      throw new IllegalStateException(message);
    }

    // image name
    args[0] = commandsList[1];
    // image destination
    args[1] = commandsList[2];
    // format
    args[2] = commandsList[3].toUpperCase();

    // OPTIMIZE: find a way to allow image path with the format " " and spaces in it.
    return args;
  }


  private enum SupportedFormats {
    PPM, PNG, JPG
  }
}
