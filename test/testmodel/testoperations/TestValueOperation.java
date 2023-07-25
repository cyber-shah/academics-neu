package testmodel.testoperations;

import model.image.CImage;
import model.image.CImageState;
import model.image.CPixel;
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
    CImage customImage = new CImage(2, 2, 255);

    CPixel pixel = new CPixel(1, 2, 3);
    customImage.setPixel(0, 0, pixel);
    CPixel pixel2 = new CPixel(6, 5, 4);
    customImage.setPixel(0, 1, pixel2);
    CPixel pixel3 = new CPixel(7, 9, 8);
    customImage.setPixel(1, 0, pixel3);
    CPixel pixel4 = new CPixel(10, 12, 12);
    customImage.setPixel(1, 1, pixel4);

    OperationInterface valueOperation = new ValueComponentOperation(customImage);
    CImageState result = valueOperation.applyOperation();

    assertEquals(3, result.getPixel(0, 0).getRed());
    assertEquals(3, result.getPixel(0, 0).getGreen());
    assertEquals(3, result.getPixel(0, 0).getBlue());
    assertEquals(6, result.getPixel(0, 1).getGreen());
    assertEquals(9, result.getPixel(1, 0).getBlue());
    assertEquals(12, result.getPixel(1, 1).getRed());
  }
}
