package controller.commandsstrategy.io;

import controller.commandsstrategy.CommandStrategyInterface;
import controller.io.ImageSaverInterface;
import controller.io.PPMImageSaver;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class implements the CommandStrategyInterface and is responsible for saving an image.
 * @see CommandStrategyInterface
 * @see ImageDatabaseInterface
 */
public class SaveCommandStrategy implements CommandStrategyInterface {

  /**
   * This method runs the command.
   * NOTE: This method calls the ImageSaver to save the image.
   *       Also uses the BufferedWrite to write the image to the file.
   *
   * @param commandsList String[] a list of commands.
   * @param model        ImageDatabaseInterface the model to be used.
   * @throws IllegalArgumentException if the image cannot be saved.
   */
  @Override
  public void run(String[] commandsList, ImageDatabaseInterface model) {
    // 0. Validate all the arguments.
    if (commandsList.length != 3) {
      throw new IllegalArgumentException("Please provide the command in this extension \n"
              + " \"save <destImagePath> <imageID> \"");
    }

    // 1. Extract the extension
    // FIXME : modify the scripting files to include this change.
    //         change the order of the arguments.
    String destImagePath = commandsList[1];
    String imageID = commandsList[2];
    Path path = Paths.get(destImagePath);
    String extension = path.getFileName().toString();
    extension = extension.substring(extension.lastIndexOf(".") + 1);


    // 1. if imageID is not present in the model
    if (!model.containsImage(imageID)) {
      throw new IllegalArgumentException("Image not found");
    }
    CustomImageState imageWrite = model.getImage(imageID);

    // 2. if extension is PPM
    if (extension.equalsIgnoreCase("PPM")) {
      // 3. call the ImageSaver to save the image.
      ImageSaverInterface imageSaver = new PPMImageSaver();
      try {
        imageSaver.save(imageWrite, destImagePath);
      } catch (Exception e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }

    // 4. if extension is not PPM
    else {
      // 5. call the ImageIO to write the image to the file.
      try {
        BufferedImage bufferedImage = imageWrite.getBufferedImage();
        if (!ImageIO.write(bufferedImage, extension, new java.io.File(destImagePath))) {
          throw new IllegalArgumentException("Unsupported image format: " + extension);
        }
      }
      catch (Exception e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }
}
