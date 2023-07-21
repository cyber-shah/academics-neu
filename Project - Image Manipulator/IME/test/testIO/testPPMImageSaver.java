package testIO;

import controller.IO.ImageSaverInterface;
import controller.IO.PPMImageLoader;
import controller.IO.PPMImageSaver;
import model.Image.Image;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class testPPMImageSaver {
  /**
   * 1. test savePPMImage() with a valid file name
   * 2. test savePPMImage() with an invalid file name
   * 3. test savePPMImage() with a null file name
   * 4. test savePPMImage() with a null image
   */

  private Image loadImage;
  private String loadPath;
  private Image saveImage;
  private String savePath;
  private ImageSaverInterface saver;


  @Test
  public void testSavePPMImage() throws IOException {
    // 0. create the image to save
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.loadImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\testSavePPMImage4x4.ppm";
    saver.save(loadImage, savePath);

    // 2. load the saved image to check if it is the same as the original image
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\testSavePPMImage4x4.ppm";
    this.loadImage = new PPMImageLoader().load(loadPath);
    assertEquals(4, loadImage.getWidth());
    assertEquals(4, loadImage.getHeight());
    assertEquals(255, loadImage.getMaxValue());
    assertEquals(21, loadImage.getPixel(1, 1).getRed());

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
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.loadImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testSavePPMImage4x4";
    saver.save(loadImage, savePath);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSavePPMImageNullFileName() throws IOException {
    // 0. create the image to save
    this.loadPath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.loadImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = null;
    saver.save(loadImage, savePath);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSavePPMImageNullImage() throws IOException {
    // 0. create the image to save
    this.loadPath = null;
    this.loadImage = new PPMImageLoader().load(loadPath);

    ImageSaverInterface saver = new PPMImageSaver();
    // 1. save the image
    this.savePath = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testSavePPMImage4x4.ppm";
    saver.save(loadImage, savePath);
  }
}
