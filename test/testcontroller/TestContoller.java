package testcontroller;

import controller.ControllerImplementation;
import controller.ControllerInterface;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import org.junit.Before;
import org.junit.Test;
import view.ViewImplementation;
import view.ViewInterface;

import java.io.StringReader;

/**
 * This class tests the ControllerImplementation class.
 */
public class TestContoller {
  private ImageDatabaseInterface imageDatabase;
  private ViewInterface view;
  private Readable inReadable;

  @Before
  public void setUp() {
    imageDatabase = new ImageDatabase();
    StringBuilder viewLog = new StringBuilder();
    view = new ViewImplementation(viewLog);
  }
  @Test
  public void testLoadCommand() {
    inReadable = new StringReader("LOAD\n");
    ControllerInterface controller = new ControllerImplementation(imageDatabase, view, inReadable);
  }
}
