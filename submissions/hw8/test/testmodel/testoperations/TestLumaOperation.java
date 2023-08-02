package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.Image;
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
    Image image = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, image.getPixel(1, 1).getRed());
    assertEquals(22, image.getPixel(1, 1).getGreen());
    assertEquals(22, image.getPixel(1, 1).getBlue());

    OperationInterface luma = new LumaOperation(image);
    Image lumaImage = (Image) luma.applyOperation();

    assertEquals(21, lumaImage.getPixel(1, 1).getRed());
    assertEquals(21, lumaImage.getPixel(1, 1).getGreen());
    assertEquals(21, lumaImage.getPixel(1, 1).getBlue());

  }
}
