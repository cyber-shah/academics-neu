import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;
import org.junit.Before;
import cs5004.marblesolitaire.model.hw05.TriangleSolitaireModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestTriangleSolitaireModel {
  private MarbleSolitaireModel triangleSolitaireModel;

  @Before
  public void setUp() {
    triangleSolitaireModel = new TriangleSolitaireModel();
  }

  @Test
  public void testConstructor() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        System.out.printf(triangleSolitaireModel.getSlotAt(i,j).name());
      }
      System.out.println();
    }
  }


  @Test
  public void testToString() {
    System.out.println(triangleSolitaireModel.toString());
    assertEquals(
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", triangleSolitaireModel.toString());
  }

}
