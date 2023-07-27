package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.CustomImageState;
import model.image.PPMImage;
import model.operations.filters.BlurFilter;
import model.operations.OperationInterface;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test for Blur.
 * 1. Test for Blur with a valid image.
 * 2. Test for Blur with null pixels.
 */
public class TestBlur {

  @Test
  public void testBlurValid() throws FileNotFoundException {
    ImageLoaderInterface<PPMImage> loader = new PPMImageLoader();
    PPMImage customImage = (PPMImage) loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface blur = new BlurFilter(customImage);
    CustomImageState blurImage = blur.applyOperation();

    assertEquals(49, blurImage.getPixel(1, 1).getRed());
    assertEquals(55, blurImage.getPixel(2, 2).getGreen());
    assertEquals(46, blurImage.getPixel(3, 3).getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurNullPixels() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    OperationInterface blur = new BlurFilter(customImage);
    CustomImageState blurImage = blur.applyOperation();
  }


}
