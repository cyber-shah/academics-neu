package view;

/**
 * This interface represents the CustomEvents that the view will generate and pass to the controller.
 * SOLID : why are we using CustomEvents? and not key events? or mouse events? or any other events?
 *         So that this is not platform dependent. We can use this interface to generate events
 *         for any platform. Like gestures for mobile devices, key events for desktops, etc.
 */
public interface CustomEvents {
  // FIXME : convert these into a factory or a map later on.
  // or maybe use it with the commandmanager or something.
  void handleLoadEvent();
  void handleSaveEvent();
}
