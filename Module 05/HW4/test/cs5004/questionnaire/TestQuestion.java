package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestQuestion {
  private cs5004.questionnaire.Likert l1;
  private cs5004.questionnaire.Likert l2;
  private cs5004.questionnaire.ShortAnswer s1;
  private cs5004.questionnaire.ShortAnswer s2;
  private cs5004.questionnaire.YesNo y1;
  private cs5004.questionnaire.YesNo y2;

  @Before
  public void setUp() {
    // Likert
    l1 = new cs5004.questionnaire.Likert("I like the weather", true);
    // empty question
    l2 = new cs5004.questionnaire.Likert("", false);

    // ShortAnswer
    s1 = new cs5004.questionnaire.ShortAnswer("What is your favorite color?", false);
    // only special chars
    s2 = new cs5004.questionnaire.ShortAnswer("?!@", false);

    // YesNo
    y1 = new cs5004.questionnaire.YesNo("Did you walk to work today?", true);
    // only white space
    y2 = new cs5004.questionnaire.YesNo("   ", false);
  }

  @Test
  public void testGetPrompt() {
    // base case for each class of question
    assertEquals("I like the weather", l1.getPrompt());
    assertEquals("What is your favorite color?", s1.getPrompt());
    assertEquals("Did you walk to work today?", y1.getPrompt());

    // empty question
    assertEquals("", l2.getPrompt());

    // only special chars
    assertEquals("?!@", s2.getPrompt());

    // only white space
    assertEquals("   ", y2.getPrompt());
  }

  @Test
  public void testIsRequired() {
    // base case for each class of question
    assertTrue(l1.isRequired());
    assertFalse(s1.isRequired());
    assertTrue(y1.isRequired());
  }

  @Test
  public void testGetAnswer() {
    // when it has not been answered
    assertEquals("", l1.getAnswer());
    assertEquals("", s1.getAnswer());
    assertEquals("", y1.getAnswer());
  }

  @Test
  public void testAnswer() {
    // when it has been answered with valid input
    l1.answer("Strongly Agree");
    assertEquals("Strongly Agree", l1.getAnswer());

    s1.answer("Blue");
    assertEquals("Blue", s1.getAnswer());

    y1.answer("No");
    assertEquals("No", y1.getAnswer());


    // Check for case-sensitive enums
    l1.answer("STroNgLy DisAgrEe");
    y1.answer("yEs");
    assertEquals("Strongly Disagree", l1.getAnswer());
    assertEquals("Yes", y1.getAnswer());

    // answer is exactly 280 words
    StringBuilder answer = new StringBuilder();
    answer.append("a".repeat(280));
    s1.answer(answer.toString());
    assertEquals(answer.toString(), s1.getAnswer());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAnswerErrors() {
    // answer is too long
    s1.answer("a".repeat(281));

    // answer doesn't match enum, slightly off
    l1.answer("Strongly Disagree!");
    y1.answer("Yess");

    // completely off
    l1.answer("I don't know");
    y1.answer("Yeah");
  }

}
