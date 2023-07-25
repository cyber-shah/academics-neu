package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.PPMImage;
import model.operations.LumaOperation;
import model.operations.OperationInterface;
import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

/**
 * Test for Luma.
 * 1. Test for Luma with a valid image.
 */
public class TestLumaOperation {

  @Test
  public void testLuma() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    PPMImage customImage = (PPMImage) loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(22, customImage.getPixel(1, 1).getGreen());
    assertEquals(22, customImage.getPixel(1, 1).getBlue());

    OperationInterface luma = new LumaOperation(customImage);
    PPMImage lumaCustomImage = (PPMImage) luma.applyOperation();

    assertEquals(21, lumaCustomImage.getPixel(1, 1).getRed());
    assertEquals(21, lumaCustomImage.getPixel(1, 1).getGreen());
    assertEquals(21, lumaCustomImage.getPixel(1, 1).getBlue());

  }
}
