package testcontroller;

import controller.CommandsManagerInterface;
import controller.ControllerImplementation;
import controller.ControllerInterface;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import org.junit.Before;
import org.junit.Test;
import testcontroller.mocks.MockCommandsManager;
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
  private final CommandsManagerInterface mockCommandsManager = new MockCommandsManager();

  @Before
  public void setUp() {
    imageDatabase = new ImageDatabase();
    StringBuilder viewLog = new StringBuilder();
    view = new ViewImplementation(viewLog);
  }
  @Test
  public void testLoadCommand() {
    // 1. put the commands in a string
    inReadable = new StringReader("LOAD\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(imageDatabase, view, inReadable, mockCommandsManager);
  }
}
