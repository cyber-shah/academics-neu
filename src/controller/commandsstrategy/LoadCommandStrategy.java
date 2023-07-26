package controller.commandsstrategy;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.BufferedImageWrapper;
import model.image.CustomImageState;
import model.ImageDatabaseInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

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
    // load format image-name image-destination
    if (commandsList.length < 3) {
      String message = "Please provide the command in the format \n"
              + "load <format> <image-destination> <image-name>";
      throw new IllegalStateException(message);
    }
    String format = commandsList[1];
    String sourceImagePath = commandsList[2];
    String imageID = commandsList[3];
    CustomImageState newImage;

    // 1. check if format is PPM
    // 2. if PPM, use the PPMImageLoader
    if (format.equalsIgnoreCase("ppm")) {
      try {
        // 3. call the ImageLoader to load the image.
        PPMImageLoader loaderPPM = new PPMImageLoader();
        newImage = loaderPPM.load(sourceImagePath);
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
    // 2. else use the BufferedImage class
    // 3. if not PPM use the BufferedImage class
    else {
      try {
        BufferedImage bufferedImage = ImageIO.read(new File(sourceImagePath));
        newImage = new BufferedImageWrapper(bufferedImage);
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
    // 4. Add the new image to the imageDatabase using the imageID.
    imageDatabase.addImage(imageID, newImage);
  }

}
