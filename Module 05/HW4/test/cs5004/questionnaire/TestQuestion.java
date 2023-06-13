package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestQuestion {
  private cs5004.questionnaire.Likert l1;
  private cs5004.questionnaire.Likert l2;

  @Before
  public void setUp() {
    l1 = new cs5004.questionnaire.Likert("I like the weather", true);
    l2 = new cs5004.questionnaire.Likert("I love everyone around me", false);
  }

  @Test
  public void testGetPrompt() {
    assertEquals("I like the weather", l1.getPrompt());
    assertEquals("I love everyone around me", l2.getPrompt());
  }

  @Test
  public void testIsRequired() {
    assertTrue(l1.isRequired());
    assertFalse(l2.isRequired());
  }

  @Test
  public void testGetAnswer() {
    assertEquals("", l1.getAnswer());
    l1.answer("Disagree");
    assertEquals("Disagree", l1.getAnswer());
    assertEquals("", l2.getAnswer());
  }

}
