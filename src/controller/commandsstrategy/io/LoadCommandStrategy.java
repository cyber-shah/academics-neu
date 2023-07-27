package controller.commandsstrategy.io;

import controller.commandsstrategy.CommandStrategyInterface;
import controller.io.PPMImageLoader;
import model.ImageDatabaseInterface;
import model.image.BufferedImageWrapper;
import model.image.CustomImageState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    // 0. Validate the arguments
    if (commandsList.length < 3) {
      String message = "Please provide the command in the extension \n"
              + "load <image-path> <image-name>";
      throw new IllegalStateException(message);
    }

    // 1. Extract the extension
    String sourceImagePath = commandsList[1];
    String imageID = commandsList[2];

    Path path = Paths.get(sourceImagePath);
    String extension = path.getFileName().toString();
    extension = extension.substring(extension.lastIndexOf(".") + 1);

    // 1. Try loading the image
    CustomImageState newImage;

    // 2. if PPM, use the PPMImageLoader
    if (extension.equalsIgnoreCase("ppm")) {
      try {
        // 3. call the ImageLoader to load the image.
        PPMImageLoader loaderPPM = new PPMImageLoader();
        newImage = loaderPPM.load(sourceImagePath);
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }

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
