import controller.ControllerImplementation;
import controller.ControllerInterface;
import model.ModelImplementation;
import model.ModelInterface;
import org.junit.Before;
import view.ViewImplementation;
import view.ViewInterface;


public class IMEtest {
  private ModelInterface model;
  private ViewInterface view;
  private ControllerInterface controller;

  @Before
  public void setUp() throws Exception {
    model = new ModelImplementation();
    view = new ViewImplementation(controller, (Appendable)System.out);
    controller = new ControllerImplementation(model, view, (Readable) System.in);
  }


}
