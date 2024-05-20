package testmodel;

import model.ImageDatabase;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import model.image.PPMImage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the ImageDatabase class.
 */
public class TestCustomImageMutableDatabase {
  private ImageDatabaseInterface model;
  private CustomImageState image;

  @Test
  public void testAddImageValid() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    String names = model.getAllImageNamesString();
    assertEquals("koala\n", names);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageNullName() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage(null, image);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageDuplicateName() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.addImage("koala", image);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageNullImage() {
    model = new ImageDatabase();

    model.addImage("koala", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageNullNameNullImage() {
    model = new ImageDatabase();

    model.addImage(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageNullNameDuplicateImage() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.addImage(null, image);
  }

  @Test
  public void testGetImageValid() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    CustomImageState image2 = model.getImage("koala");
    assertEquals(image, image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageNullName() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.getImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageInvalidName() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.getImage("panda");
  }

  @Test
  public void testRemoveImageValid() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.removeImage("koala");
    String names = model.getAllImageNamesString();
    assertEquals("", names);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImageNullName() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.removeImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImageInvalidName() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.removeImage("panda");
  }

  @Test
  public void testGetAllImageNames() {
    model = new ImageDatabase();
    image = new PPMImage(4,4,255);

    model.addImage("koala", image);
    model.addImage("panda", image);
    model.addImage("cat", image);
    model.addImage("dog", image);
    String names = model.getAllImageNamesString();
    assertEquals("panda\nkoala\ncat\ndog\n", names);
  }

  @Test
  public void testGetAllImageNamesEmpty() {
    model = new ImageDatabase();
    String names = model.getAllImageNamesString();
    assertEquals("", names);
  }
}
