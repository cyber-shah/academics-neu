import controller.ImageLoader;
import model.Image;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;


public class TestImageLoader {
  private ImageLoader imageLoader;

  @Before
  public void setUp() throws IOException {
    imageLoader = new ImageLoader("C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Model 11 - Strategy Pattern\\HW8 CS5004 Starter\\code (3)\\Koala.ppm");
  }

  @Test
  public void testImageLoader() throws FileNotFoundException {
    Image image = imageLoader.load();
    System.out.println(image.toString());
  }
}
