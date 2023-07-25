package testio;

import controller.io.ImageSaverInterface;
import controller.io.PPMImageLoader;
import controller.io.PPMImageSaver;
import model.image.PPMImage;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Test class for PPMImageSaver.
 *    * 1. test savePPMImage() with a valid file name
 *    * 2. test savePPMImage() with an invalid file name
 *    * 3. test savePPMImage() with a null file name
 *    * 4. test savePPMImage() with a null image
 */
public class TestPPMExtendedCustomImageSaver {

  private PPMImage loadCustomImage;
  private String loadPath;
  private PPMImage saveCustomImage;
  private String savePath;
  private ImageSaverInterface saver;


  @Test
  public void testSavePPMImage() throws IOException {
    // 0. create the image to save
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.loadCustomImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\testSavePPMImage4x4.ppm";
    saver.save(loadCustomImage, savePath);

    // 2. load the saved image to check if it is the same as the original image
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\testSavePPMImage4x4.ppm";
    this.loadCustomImage = new PPMImageLoader().load(loadPath);
    assertEquals(4, loadCustomImage.getWidth());
    assertEquals(4, loadCustomImage.getHeight());
    assertEquals(255, loadCustomImage.getMaxValue());
    assertEquals(21, loadCustomImage.getPixel(1, 1).getRed());

    // 3. Delete the file
    File fileToDelete = new File(loadPath);
    if (fileToDelete.delete()) {
      System.out.println("File deleted successfully.");
    } else {
      System.out.println("Failed to delete the file.");
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSavePPMImageInvalidFileName() throws IOException {
    // 0. create the image to save
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.loadCustomImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testSavePPMImage4x4";
    saver.save(loadCustomImage, savePath);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSavePPMImageNullFileName() throws IOException {
    // 0. create the image to save
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.loadCustomImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = null;
    saver.save(loadCustomImage, savePath);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSavePPMImageNullImage() throws IOException {
    // 0. create the image to save
    this.loadPath = null;
    this.loadCustomImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testSavePPMImage4x4.ppm";
    saver.save(loadCustomImage, savePath);
  }
}
