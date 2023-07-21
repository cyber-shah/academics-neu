package testModel;

import model.Image.Image;
import model.ImageDatabase;
import model.Image.ImageState;
import model.ImageDatabaseInterface;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the ImageDatabase class.
 * @see ImageDatabase
 * 1. Test for addImage with a valid image and a valid name.
 * 2. Test for addImage with a valid image and a null name.
 * 3. Test for addImage with a valid image and a duplicate name.
 * 4. Test for addImage with a null image and a valid name.
 * 5. Test for addImage with a null image and a null name.
 * 6. Test for addImage with a null image and a duplicate name.
 * 7. Test for getImage with a valid name.
 * 8. Test for getImage with a null name.
 * 9. Test for getImage with a name that does not exist.
 * 10. Test for removeImage with a valid name.
 * 11. Test for removeImage with a null name.
 * 12. Test for removeImage with a name that does not exist.
 */
public class testImageDatabase {
  private ImageDatabaseInterface model;
  private ImageState image;

  @Test
  public void testAddImageValid() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    String names = model.getAllImageNames();
    assertEquals("koala\n", names);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageNullName() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage(null, image);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageDuplicateName() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

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
    image = new Image(4,4,255);

    model.addImage("koala", image);
    model.addImage(null, image);
  }

  @Test
  public void testGetImageValid() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    ImageState image2 = model.getImage("koala");
    assertEquals(image, image2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageNullName() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    model.getImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageInvalidName() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    model.getImage("panda");
  }

  @Test
  public void testRemoveImageValid() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    model.removeImage("koala");
    String names = model.getAllImageNames();
    assertEquals("", names);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImageNullName() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    model.removeImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImageInvalidName() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    model.removeImage("panda");
  }

  @Test
  public void testGetAllImageNames() {
    model = new ImageDatabase();
    image = new Image(4,4,255);

    model.addImage("koala", image);
    model.addImage("panda", image);
    model.addImage("cat", image);
    model.addImage("dog", image);
    String names = model.getAllImageNames();
    assertEquals("panda\nkoala\ncat\ndog\n", names);
  }

  @Test
  public void testGetAllImageNamesEmpty() {
    model = new ImageDatabase();
    String names = model.getAllImageNames();
    assertEquals("", names);
  }
}
