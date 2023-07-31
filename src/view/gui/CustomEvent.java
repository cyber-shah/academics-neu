package view.gui;

/**
 * This class is like a wrapper for the EventObject class.
 * It will be used to generate custom events.
 * SOLID : why are we using CustomEvents? and not key events? or mouse events? or any other events?
 *        So that this is not platform dependent. We can use this interface to generate events
 *        for any platform. Like gestures for mobile devices, key events for desktops, etc.
 */
public class CustomEvent extends java.util.EventObject {

  private String event;

  /**
   * Constructor for the CustomEvent class.
   *
   * @param source the source of the event.
   * @param event the event.
   */
  public CustomEvent(Object source, String event) {
    super(source);
    this.event = event;
  }

  /**
   * Getter for the event.
   *
   * @return the event.
   */
  public String getEvent() {
    return this.event;
  }

}
