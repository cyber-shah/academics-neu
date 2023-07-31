import model.image.CustomImageState;
import view.gui.CustomEventsListener;

public interface GUIViewInterface {
  void addEventsListener(CustomEventsListener controller);
  void showMessage(String message);
  void updateImageDatabase(CustomImageState imageDatabase);
  void updateImageCanvas(CustomImageState image);
}
