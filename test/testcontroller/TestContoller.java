package testcontroller;

import commandmanager.CommandsManagerInterface;
import controller.ControllerImplementation;
import controller.ControllerInterface;
import model.ImageDatabaseInterface;
import org.junit.Before;
import org.junit.Test;
import testcontroller.mocks.MockCommandStrategy;
import testcontroller.mocks.MockCommandsManager;
import testcontroller.mocks.MockImageDatabase;
import testcontroller.mocks.MockViewImplementation;
import view.ViewInterface;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the ControllerImplementation class.
 */
public class TestContoller {
  private MockViewImplementation mockView;
  private final MockCommandsManager mockCommandsManager = new MockCommandsManager();

  /**
   * This is what it will do
   * 1. call the controller's run method
   * - wait for the next command
   * 2. parse the string in inReadable, parse the command
   * 3. Find the command inside commandsManager
   * 4. go to the commandsStrategyObject and call run
   * 5. commandsStrategy will further parse all the inputs and call the MODEL - operation
   * 6. and hit MODELOPERATION.applyOperation()
   * 7. add the new image to the imageDatabase
   * 8. wait for the next command
   */

  /**
   * Here's how we will test it:
   * 1. call the controller's run method
   * 2. parse the string in inReadable, parse the command
   * 3. Find the command inside commandsManager
   * ### Before returning the actual object from the commandsManager,
   * appends the string to a log -------------------------
   * 4. go to the commandsStrategy and call run
   * 5. The mockCommandsStrategy will call mockOperation
   * 6. where it will return nothing --------------------
   * 7.
   *
   */
  @Test
  public void testLoadCommand() {
    mockView = new MockViewImplementation();
    MockImageDatabase mockImageDatabase = new MockImageDatabase();
    // 1. put the commands in a string
    Readable inReadable = new StringReader("LOAD abc.ppm abc\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(mockImageDatabase, mockView, inReadable, mockCommandsManager);
    controller.runProgram();
    assertEquals("Args 0: LOAD Args 1: abc.ppm Args 2: abc ", mockCommandsManager.getLog());
  }

  @Test
  public void testSaveCommand() {
    mockView = new MockViewImplementation();
    MockImageDatabase mockImageDatabase = new MockImageDatabase();
    // 1. put the commands in a string
    Readable inReadable = new StringReader("SAVE abc.ppm abc\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(mockImageDatabase, mockView, inReadable, mockCommandsManager);
    controller.runProgram();
    assertEquals("Args 0: SAVE Args 1: abc.ppm Args 2: abc ", mockCommandsManager.getLog());
  }

  @Test
  public void testBrightenCommand() {
    mockView = new MockViewImplementation();
    MockImageDatabase mockImageDatabase = new MockImageDatabase();
    // 1. put the commands in a string
    Readable inReadable = new StringReader("BRIGHTEN abc.ppm abc\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(mockImageDatabase, mockView, inReadable, mockCommandsManager);
    controller.runProgram();
    assertEquals("Args 0: BRIGHTEN Args 1: abc.ppm Args 2: abc ", mockCommandsManager.getLog());
  }

  @Test
  public void testLumaCommand() {
    mockView = new MockViewImplementation();
    MockImageDatabase mockImageDatabase = new MockImageDatabase();
    // 1. put the commands in a string
    Readable inReadable = new StringReader("LUMA abc.ppm abc\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(mockImageDatabase, mockView, inReadable, mockCommandsManager);
    controller.runProgram();
    assertEquals("Args 0: LUMA Args 1: abc.ppm Args 2: abc ", mockCommandsManager.getLog());
  }

  @Test
  public void testIntensityCommand() {
    mockView = new MockViewImplementation();
    MockImageDatabase mockImageDatabase = new MockImageDatabase();
    // 1. put the commands in a string
    Readable inReadable = new StringReader("INTENSITY abc.ppm abc\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(mockImageDatabase, mockView, inReadable, mockCommandsManager);
    controller.runProgram();
    assertEquals("Args 0: INTENSITY Args 1: abc.ppm Args 2: abc ", mockCommandsManager.getLog());
  }

  @Test
  public void testValueCommand() {
    mockView = new MockViewImplementation();
    MockImageDatabase mockImageDatabase = new MockImageDatabase();
    // 1. put the commands in a string
    Readable inReadable = new StringReader("VALUE abc.ppm abc\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(mockImageDatabase, mockView, inReadable, mockCommandsManager);
    controller.runProgram();
    assertEquals("Args 0: VALUE Args 1: abc.ppm Args 2: abc ", mockCommandsManager.getLog());
  }

  @Test
  public void testComponentCommand() {
    mockView = new MockViewImplementation();
    MockImageDatabase mockImageDatabase = new MockImageDatabase();
    // 1. put the commands in a string
    Readable inReadable = new StringReader("COMPONENT red abc.ppm abc\n");
    // 2. pass the string to the controller
    ControllerInterface controller = new ControllerImplementation(mockImageDatabase, mockView, inReadable, mockCommandsManager);
    controller.runProgram();
    assertEquals("Args 0: COMPONENT Args 1: red Args 2: abc.ppm Args 3: abc ", mockCommandsManager.getLog());
  }
}
