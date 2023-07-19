import controller.ImageLoader;
import model.Image;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;


public class TestImageLoader03 {
  private ImageLoader imageLoader;

  @Test
  public void testImageLoader() throws FileNotFoundException {
    imageLoader = new ImageLoader("C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Model 11 - Strategy Pattern\\HW8 CS5004 Starter\\code (3)\\TestFile.ppm");
    Image image = imageLoader.load();
    System.out.println(image.toString());

    assertEquals(1024, image.getWidth());
    assertEquals(768, image.getHeight());

    assertEquals(786432, image.getPixelsList().length);
    assertEquals(255, image.getPixelsList()[0].getRed());
    assertEquals(101, image.getPixelsList()[0].getGreen());
    assertEquals(90, image.getPixelsList()[0].getBlue());

    assertEquals(null, image.getPixelsList()[786431].getRed());
  }
}
