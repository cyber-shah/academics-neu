package view.gui;

/**
 * This interface represents the CustomEvents that the view will generate.
 * and pass to the controller.
 * SOLID : why are we using CustomEvents? and not key events? or mouse events? or any other events?
 *         So that this is not platform dependent. We can use this interface to generate events
 *         for any platform. Like gestures for mobile devices, key events for desktops, etc.
 */
public interface CustomEventsListener {

  /**
   * This method will handle the load event.
   * Based on the event, the controller will decide what to do.
   *
   * @param event the event to be handled.
   */
  void handleEvent(CustomEvent event);
}
