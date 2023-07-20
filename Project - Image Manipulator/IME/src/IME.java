import controller.ControllerImplementation;
import view.ViewImplementation;

public class IME {

  public static void main(String[] args) {
    ControllerImplementation controller = new ControllerImplementation();
    ViewImplementation view = new ViewImplementation(controller, System.out);
    ModelImplementation model = new ModelImplementation();

    controller.go();
  }
}
