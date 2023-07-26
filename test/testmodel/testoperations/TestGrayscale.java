package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.CustomImageState;
import model.image.PPMImage;
import model.operations.Filters.GreyscaleFilter;
import model.operations.OperationInterface;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test for grayscale.
 * 1. Test for grayscale with a valid image.
 */
public class TestGrayscale {

  @Test
  public void testGrayscaleValid() throws FileNotFoundException {
    ImageLoaderInterface<PPMImage> loader = new PPMImageLoader();
    PPMImage customImage = (PPMImage) loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface grayscale = new GreyscaleFilter(customImage);
    CustomImageState grayscaleImage = grayscale.applyOperation();

    assertEquals(22, grayscaleImage.getPixel(1, 1).getRed());
    assertEquals(22, grayscaleImage.getPixel(1, 1).getGreen());
    assertEquals(22, grayscaleImage.getPixel(1, 1).getBlue());
    assertEquals(33, grayscaleImage.getPixel(2, 2).getGreen());
    assertEquals(33, grayscaleImage.getPixel(2, 2).getRed());
    assertEquals(33, grayscaleImage.getPixel(2, 2).getBlue());
  }
}
