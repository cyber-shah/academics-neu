package testModel.testOperations;

import controller.IO.ImageLoaderInterface;
import controller.IO.PPMImageLoader;
import model.Image.Image;
import model.Operations.BrightenOperation;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test for Intensity operation.
 *
 * 1. Test for intensity with a valid image.
 * 2. Test for intensity with a null pixels.
 */
public class testIntensityOperation {

  @Test
  public void testIntensityValid() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    Image image = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, image.getPixel(1, 1).getRed());
    assertEquals(33, image.getPixel(2, 2).getGreen());
    assertEquals(44, image.getPixel(3, 3).getBlue());

    BrightenOperation brighten = new BrightenOperation(image, 10);
    Image brightenedImage = (Image) brighten.applyOperation();

    assertEquals(31, brightenedImage.getPixel(1, 1).getRed());
    assertEquals(43, brightenedImage.getPixel(2, 2).getGreen());
    assertEquals(54, brightenedImage.getPixel(3, 3).getBlue());
  }
}
