package testmodel.testoperations;

import controller.io.ImageLoaderInterface;
import controller.io.PPMImageLoader;
import model.image.CImage;
import model.image.CImageState;
import model.operations.BrightenOperation;
import model.operations.OperationInterface;
import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

/**
 * Test for brighten.
 * 1. Test for brighten with a valid image.
 * 2. Test for brighten with a null pixels.
 * 4. Test for brighten with a valid image and a factor greater than 1.
 * 5. Test for brighten with a valid image and a factor less than 1.
 * 7. Test for brighten with a valid image and a factor equal to 0.
 * 8. Test with maximum value of color channel.
 */
public class TestBrightenOperation {

  @Test
  public void testBrightenValid() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface brighten = new BrightenOperation(customImage, 10);
    CImageState brightenedImage = brighten.applyOperation();

    assertEquals(31, brightenedImage.getPixel(1, 1).getRed());
    assertEquals(43, brightenedImage.getPixel(2, 2).getGreen());
    assertEquals(54, brightenedImage.getPixel(3, 3).getBlue());
  }

  @Test(expected = NullPointerException.class)
  public void testBrightenNullPixels() {
    CImage customImage = new CImage(4, 4, 255);
    OperationInterface brighten = new BrightenOperation(customImage, 10);
    CImageState brightenedImage = brighten.applyOperation();
  }

  @Test
  public void testBrightenFactorMax() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface brighten = new BrightenOperation(customImage, 255);
    CImageState brightenedImage = brighten.applyOperation();

    assertEquals(255, brightenedImage.getPixel(1, 1).getRed());
    assertEquals(255, brightenedImage.getPixel(2, 2).getGreen());
    assertEquals(255, brightenedImage.getPixel(3, 3).getBlue());
  }

  @Test
  public void testBrightenFactorMin() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface brighten = new BrightenOperation(customImage, -255);
    CImageState brightenedImage = brighten.applyOperation();

    assertEquals(0, brightenedImage.getPixel(1, 1).getRed());
    assertEquals(0, brightenedImage.getPixel(2, 2).getGreen());
    assertEquals(0, brightenedImage.getPixel(3, 3).getBlue());
  }

  @Test
  public void testBrightenFactorGreaterThanMax() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface brighten = new BrightenOperation(customImage, 300);
    CImageState brightenedImage = brighten.applyOperation();

    assertEquals(255, brightenedImage.getPixel(1, 1).getRed());
    assertEquals(255, brightenedImage.getPixel(2, 2).getGreen());
    assertEquals(255, brightenedImage.getPixel(3, 3).getBlue());
  }

  @Test
  public void testBrightenNormal() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());
  }

  @Test
  public void testDarken() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface brighten = new BrightenOperation(customImage, -20);
    CImageState brightenedImage = brighten.applyOperation();

    assertEquals(1, brightenedImage.getPixel(1, 1).getRed());
    assertEquals(13, brightenedImage.getPixel(2, 2).getGreen());
    assertEquals(24, brightenedImage.getPixel(3, 3).getBlue());
  }

  @Test
  public void testBrightenZero() throws FileNotFoundException {
    ImageLoaderInterface loader = new PPMImageLoader();
    CImage customImage = loader.load("test/testIO/test4x4.ppm");

    // check the original image
    assertEquals(21, customImage.getPixel(1, 1).getRed());
    assertEquals(33, customImage.getPixel(2, 2).getGreen());
    assertEquals(44, customImage.getPixel(3, 3).getBlue());

    OperationInterface brighten = new BrightenOperation(customImage, 0);
    CImageState brightenedImage = brighten.applyOperation();

    assertEquals(21, brightenedImage.getPixel(1, 1).getRed());
    assertEquals(33, brightenedImage.getPixel(2, 2).getGreen());
    assertEquals(44, brightenedImage.getPixel(3, 3).getBlue());
  }
}
