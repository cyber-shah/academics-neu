package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.CImage;
import model.operations.BrightenOperation;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test for Intensity operation.
 * 1. Test for intensity with a valid image.
 * 2. Test for intensity with a null pixels.
 */
public class TestIntensityOperation {

  @Test
  public void testIntensityValid() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    BrightenOperation brighten = new BrightenOperation(customImage, 10);
    CImage brightenedCustomImage = (CImage) brighten.applyOperation();

    assertEquals(31, brightenedCustomImage.getPixel(1, 1).getRed());
    assertEquals(43, brightenedCustomImage.getPixel(2, 2).getGreen());
    assertEquals(54, brightenedCustomImage.getPixel(3, 3).getBlue());
  }
}
