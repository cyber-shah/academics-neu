package testview;

import controller.ControllerGUI;
import controller.ControllerGUIInterface;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import model.image.CustomImageState;
import org.junit.Before;
import org.junit.Test;
import view.gui.CustomEvent;

import static org.junit.Assert.assertEquals;

public class ViewTest {
  private ImageDatabaseInterface model;
  private MockView mockView;
  private ControllerGUIInterface controller;
  String filePath;

  @Before
  public void setUp() {
    this.model = new ImageDatabase();
    this.mockView = new MockView();
    this.controller = new ControllerGUI(model, mockView);
    this.filePath = new String("C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\Project - Image Manipulator\\test\\testview\\testimage.jpg");
  }

  /**
   * We are testing if the handleEvent method in the controller.
   * is able to update the view with the correct image.
   */
  @Test
  public void testLoadEvent() {
    String filePath = new String("C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\Project - Image Manipulator\\test\\testview\\testimage.jpg");
    CustomEvent loadEvent = new CustomEvent(this, "io", "load",
            filePath, "tester", "null");

    controller.handleEvent(loadEvent);

    CustomImageState viewImage = mockView.image;
    CustomImageState modelImage = model.getImage("tester");

    assertEquals (viewImage, modelImage);
  }

  @Test
  public void testMessages() {
    String filePath = new String("testimage.jpg");
    CustomEvent loadEvent = new CustomEvent(this, "io", "load",
            filePath, "tester", "null");

    controller.handleEvent(loadEvent);

    assertEquals ("added an event listener\n"
                    + "Can't read input file!Image not found",
            mockView.log.toString());
  }

  @Test
  public void testSepiaEvent() {
    CustomEvent loadEvent = new CustomEvent(this, "io", "load",
            filePath, "tester", "null");

    controller.handleEvent(loadEvent);

    CustomEvent sepiaEvent = new CustomEvent(this, "color", "sepia",
            "null", "tester", "sepiaTester");

    controller.handleEvent(sepiaEvent);

    CustomImageState viewImage = mockView.image;
    CustomImageState modelImage = model.getImage("sepiaTester");
    assertEquals(viewImage, modelImage);
  }

  @Test
  public void testGrayscaleEvent() {
    CustomEvent loadEvent = new CustomEvent(this, "io", "load",
            filePath, "tester", "null");

    controller.handleEvent(loadEvent);

    CustomEvent grayscaleEvent = new CustomEvent(this, "greyscale", "luma",
            "null", "tester", "grayscaleTester");

    controller.handleEvent(grayscaleEvent);

    CustomImageState viewImage = mockView.image;
    CustomImageState modelImage = model.getImage("grayscaleTester");
    assertEquals(viewImage, modelImage);
  }

  @Test
  public void testBlurEvent() {
    CustomEvent loadEvent = new CustomEvent(this, "io", "load",
            filePath, "tester", "null");

    controller.handleEvent(loadEvent);

    CustomEvent blurEvent = new CustomEvent(this, "filter", "blur",
            "null", "tester", "blurTester");

    controller.handleEvent(blurEvent);

    CustomImageState viewImage = mockView.image;
    CustomImageState modelImage = model.getImage("blurTester");
    assertEquals(viewImage, modelImage);
  }


  @Test
  public void addEventsListener() {
    assertEquals(mockView.log.toString(),
            "added an event listener\n");
  }

}
