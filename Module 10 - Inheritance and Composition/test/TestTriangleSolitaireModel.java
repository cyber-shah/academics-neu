import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.AbstractTextView;
import cs5004.marblesolitaire.view.TriangleSolitaireTextView;
import org.junit.Before;
import cs5004.marblesolitaire.model.hw07.TriangleSolitaireModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestTriangleSolitaireModel {
  private MarbleSolitaireModel model;
  private AbstractTextView view;

  @Before
  public void setUp() {
    model = new TriangleSolitaireModel();
  }

  @Test
  public void testConstructor() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.printf(model.getSlotAt(i,j).name() + ", ");
      }
      System.out.println();
    }
  }

  @Test
  public void testToString() {
    view = new TriangleSolitaireTextView(model);
    assertEquals(
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", view.toString());

    model.move(2, 0, 0, 0);


  }



}
