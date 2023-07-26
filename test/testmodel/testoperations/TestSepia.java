package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.CustomImageState;
import model.image.PPMImage;
import model.operations.BlurOperation;
import model.operations.OperationInterface;
import model.operations.SepiaOperation;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test for Sepia.
 * 1. Test for Sepia with a valid image.
 */
public class TestSepia {

  @Test
  public void testSepiaValid() throws FileNotFoundException {
    ImageLoaderInterface<PPMImage> loader = new PPMImageLoader();
    PPMImage customImage = (PPMImage) loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface sepia = new SepiaOperation(customImage);
    CustomImageState sepiaImage = sepia.applyOperation();

    assertEquals(29, sepiaImage.getPixel(1, 1).getRed());
    assertEquals(39, sepiaImage.getPixel(2, 2).getGreen());
    assertEquals(40, sepiaImage.getPixel(3, 3).getBlue());
  }
}
