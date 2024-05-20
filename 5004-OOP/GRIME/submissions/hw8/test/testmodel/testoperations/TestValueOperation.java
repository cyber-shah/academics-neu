package testmodel.testoperations;

import model.image.Image;
import model.image.ImageState;
import model.image.Pixel;
import model.operations.OperationInterface;
import model.operations.ValueComponentOperation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for Value operation.
 */
public class TestValueOperation {

  @Test
  public void testValueOperation() {
    Image image = new Image(2, 2, 255);

    Pixel pixel = new Pixel(1, 2, 3);
    image.setPixel(0, 0, pixel);
    Pixel pixel2 = new Pixel(6, 5, 4);
    image.setPixel(0, 1, pixel2);
    Pixel pixel3 = new Pixel(7, 9, 8);
    image.setPixel(1, 0, pixel3);
    Pixel pixel4 = new Pixel(10, 12, 12);
    image.setPixel(1, 1, pixel4);

    OperationInterface valueOperation = new ValueComponentOperation(image);
    ImageState result = valueOperation.applyOperation();

    assertEquals(3, result.getPixel(0, 0).getRed());
    assertEquals(3, result.getPixel(0, 0).getGreen());
    assertEquals(3, result.getPixel(0, 0).getBlue());
    assertEquals(6, result.getPixel(0, 1).getGreen());
    assertEquals(9, result.getPixel(1, 0).getBlue());
    assertEquals(12, result.getPixel(1, 1).getRed());
  }
}
