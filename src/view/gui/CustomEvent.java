package view.gui;
import java.util.UUID;

/**
 * This class is like a wrapper for the EventObject class.
 * It will be used to generate custom events.
 * SOLID : why are we using CustomEvents? and not key events? or mouse events? or any other events?
 *        So that this is not platform dependent. We can use this interface to generate events
 *        for any platform. Like gestures for mobile devices, key events for desktops, etc.
 */
public class CustomEvent extends java.util.EventObject {

  private final String eventName;
  private final String filePath;
  private final String sourceID;
  private final String destID;


  /**
   * Constructor for the custom event.
   * This is non-mutable.
   *
   * @param source the source of the event.
   * @param eventName the name of the event.
   * @param filePath the path of the file.
   * NOTE : This constructor also generates a random sourceID and destID.
   *        This is done to make sure that the event has the same format as the
   *        scripting commands. This allows us to use the same controller for both
   *        the GUI and the scripting commands.
   */
  public CustomEvent(Object source, String eventName, String filePath) {
    super(source);
    this.eventName = eventName;
    this.filePath = filePath;
    this.sourceID = UUID.randomUUID().toString();
    this.destID = UUID.randomUUID().toString();
  }

  /**
   * Getter for the event name.
   */
  public String getEventName() {
    return eventName;
  }

  /**
   * Getter for the file path.
   */
  public String getFilePath() {
    return filePath;
  }

  /**
   * Getter for the source ID.
   */
  public String getSourceID() {
    return sourceID;
  }

  /**
   * Getter for the destination ID.
   */
  public String getDestID() {
    return destID;
  }


}
