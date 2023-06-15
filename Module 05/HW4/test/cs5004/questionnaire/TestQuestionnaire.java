package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Questionnaire interface.
 */
public class TestQuestionnaire {

  private QuestionnaireImpl q1;
  private QuestionnaireImpl q2;
  private QuestionnaireImpl q3;

  @Before
  public void setUp() {
    q1 = new QuestionnaireImpl();
    q2 = new QuestionnaireImpl();
    q3 = new QuestionnaireImpl();
  }

  @Test
  public void testAddQuestion() {
    // base case
    q1.addQuestion("q1.0", new YesNo("Did you walk to work today?", true));
    assertEquals("Did you walk to work today?", q1.getQuestion(1).getPrompt());

    // overwrite question
    q1.addQuestion("q1.0", new Likert("Do you like walk to work?", true));
    assertEquals("Do you like walk to work?", q1.getQuestion(2).getPrompt());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddQuestionNullIdentifier() {
    // null identifier
    q1.addQuestion(null, new YesNo("Did you walk to work today?", true));
    // empty identifier
    q1.addQuestion("", new YesNo("Did you walk to work today?", true));
    // null question
    q1.addQuestion("q1.0", null);
  }

  @Test
  public void testGetRequiredQuestions() {
    // Mixed required and not required questions
    q1.addQuestion("q1.0", new YesNo("Did you walk to work today?", true));
    q1.addQuestion("q1.1", new Likert("Do you like walk to work?", false));
    q1.addQuestion("q1.2", new ShortAnswer("What is your favorite color?", true));
    assertEquals(2, q1.getRequiredQuestions().size());

    // no required questions
    q2.addQuestion("q2.0", new YesNo("Did you walk to work today?", false));
    q2.addQuestion("q2.1", new Likert("Do you like walk to work?", false));
    q2.addQuestion("q2.2", new ShortAnswer("What is your favorite color?", false));
    assertEquals(0, q2.getRequiredQuestions().size());

    // no questions
    assertEquals(0, q3.getRequiredQuestions().size());
  }

  @Test
  public void testGetOptionalQuestions() {
    // Mixed required and not required questions
    q1.addQuestion("q1.0", new YesNo("Did you walk to work today?", true));
    q1.addQuestion("q1.1", new Likert("Do you like walk to work?", false));
    q1.addQuestion("q1.2", new ShortAnswer("What is your favorite color?", true));
    assertEquals(1, q1.getOptionalQuestions().size());

    // no required questions
    q2.addQuestion("q2.0", new YesNo("Did you walk to work today?", false));
    q2.addQuestion("q2.1", new Likert("Do you like walk to work?", false));
    q2.addQuestion("q2.2", new ShortAnswer("What is your favorite color?", false));
    assertEquals(3, q2.getOptionalQuestions().size());

    // no questions
    assertEquals(0, q3.getOptionalQuestions().size());
  }

  @Test
  public void testIsComplete() {
    // base case
    q1.addQuestion("q1.0", new YesNo("Did you walk to work today?", true));
    q1.addQuestion("q1.1", new Likert("Do you like walk to work?", false));
    q1.addQuestion("q1.2", new ShortAnswer("What is your favorite color?", true));
    assertFalse(q1.isComplete());
    q1.getQuestion(1).answer("Yes");
    q1.getQuestion(2).answer("Agree");
    q1.getQuestion(3).answer("blue");
    assertTrue(q1.isComplete());

    // not complete
    q2.addQuestion("q2.0", new YesNo("Did you walk to work today?", true));
    q2.addQuestion("q2.1", new Likert("Do you like walk to work?", false));
    q2.addQuestion("q2.2", new ShortAnswer("What is your favorite color?", true));
    assertFalse(q2.isComplete());
    q2.getQuestion(1).answer("Yes");
    q2.getQuestion(2).answer("Agree");
    assertFalse(q2.isComplete());

    // no questions
    assertTrue(q3.isComplete());
  }

  @Test
  public void testGetResponses() {
    // base case
    q1.addQuestion("q1.0", new YesNo("Did you walk to work today?", true));
    q1.addQuestion("q1.1", new Likert("Do you like walk to work?", false));
    q1.addQuestion("q1.2", new ShortAnswer("What is your favorite color?", true));
    q1.getQuestion(1).answer("Yes");
    q1.getQuestion(2).answer("Agree");
    q1.getQuestion(3).answer("blue");
    assertEquals("Yes", q1.getResponses().get(0));
    assertEquals("Agree", q1.getResponses().get(1));
    assertEquals("blue", q1.getResponses().get(2));

    // no questions
    assertEquals(0, q3.getResponses().size());
  }

  @Test
  public void testRemoveQuestion() {
    // base case
    q1.addQuestion("q1.0", new YesNo("Did you walk to work today?", true));
    q1.addQuestion("q1.1", new Likert("Do you like walk to work?", false));
    q1.addQuestion("q1.2", new ShortAnswer("What is your favorite color?", true));
    q1.addQuestion("q1.3", new YesNo("Like the weather?", true));
    q1.addQuestion("q1.4", new Likert("Coding is fun", false));
    q1.addQuestion("q1.5", new ShortAnswer("Give a short answer", true));
    q1.addQuestion("q1.6", new YesNo("I like Blue", true));

    // remove first question
    q1.removeQuestion("q1.0");
    assertEquals("Do you like walk to work?", q1.getQuestion(1).getPrompt());
    assertEquals("What is your favorite color?", q1.getQuestion(2).getPrompt());
    assertEquals("Like the weather?", q1.getQuestion(3).getPrompt());
    assertEquals("Coding is fun", q1.getQuestion(4).getPrompt());
    assertEquals("Give a short answer", q1.getQuestion(5).getPrompt());
    assertEquals("I like Blue", q1.getQuestion(6).getPrompt());

    // remove last question
    q1.removeQuestion("q1.6");
    assertEquals("Do you like walk to work?", q1.getQuestion(1).getPrompt());
    assertEquals("What is your favorite color?", q1.getQuestion(2).getPrompt());
    assertEquals("Like the weather?", q1.getQuestion(3).getPrompt());
    assertEquals("Coding is fun", q1.getQuestion(4).getPrompt());
    assertEquals("Give a short answer", q1.getQuestion(5).getPrompt());

    // remove middle question
    q1.removeQuestion("q1.3");
    assertEquals("Do you like walk to work?", q1.getQuestion(1).getPrompt());
    assertEquals("What is your favorite color?", q1.getQuestion(2).getPrompt());
    assertEquals("Coding is fun", q1.getQuestion(3).getPrompt());
    assertEquals("Give a short answer", q1.getQuestion(4).getPrompt());


    System.out.println(q1.toString());
    // remove all questions
    q1.removeQuestion("q1.1");
    q1.removeQuestion("q1.2");
    q1.removeQuestion("q1.4");
    q1.removeQuestion("q1.5");
    assertEquals("", q1.toString());


  }

  @Test
  public void testGetQuestion() {
    // base case
    q1.addQuestion("q1.0", new YesNo("Did you walk to work today?", true));
    q1.addQuestion("q1.1", new Likert("Do you like walk to work?", false));
    q1.addQuestion("q1.2", new ShortAnswer("What is your favorite color?", true));

    // using identifier
    assertEquals("Did you walk to work today?", q1.getQuestion("q1.0").getPrompt());
    assertEquals("Do you like walk to work?", q1.getQuestion("q1.1").getPrompt());
    assertEquals("What is your favorite color?", q1.getQuestion("q1.2").getPrompt());

    // using index
    assertEquals("Did you walk to work today?", q1.getQuestion(1).getPrompt());
    assertEquals("Do you like walk to work?", q1.getQuestion(2).getPrompt());
    assertEquals("What is your favorite color?", q1.getQuestion(3).getPrompt());
  }
}
