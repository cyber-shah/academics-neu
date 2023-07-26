package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.CustomImageState;
import model.image.PPMImage;
import model.operations.OperationInterface;
import model.operations.SepiaOperation;
import model.operations.SharpenOperation;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test for sharpen.
 * 1. Test for sharpen with a valid image.
 */
public class TestSharpen {

  @Test
  public void testSharpenValid() throws FileNotFoundException {
    ImageLoaderInterface<PPMImage> loader = new PPMImageLoader();
    PPMImage customImage = (PPMImage) loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface sharpen = new SharpenOperation(customImage);
    CustomImageState sharpenedImage = sharpen.applyOperation();

    assertEquals(65, sharpenedImage.getPixel(1, 1).getRed());
    assertEquals(44, sharpenedImage.getPixel(2, 2).getGreen());
    assertEquals(38, sharpenedImage.getPixel(3, 3).getBlue());

  }
}
