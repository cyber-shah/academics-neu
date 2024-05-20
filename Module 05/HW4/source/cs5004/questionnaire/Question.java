package cs5004.questionnaire;

/**
 * This interface represents a question in a questionnaire.
 * A question has a prompt, a boolean indicating whether it is required,
 * an answer, and a method to answer the question.
 * The answer method is overridden in the YesNo, Likert and ShortAnswer classes,
 * to ensure that the answer is of the correct type.
 */

public interface Question {

  /**
   * Returns the prompt for this question.
   * @return the prompt for this question.
   */
  public String getPrompt();

  /**
   * Returns whether this question is required.
   * @return whether this question is required.
   */
  public boolean isRequired();

  /**
   * Returns the answer to this question.
   * @return the answer to this question.
   */
  public String getAnswer();

  /**
   * Sets the answer to this question.
   * @param answer the answer to this question.
   */
  public void answer(String answer);

  /**
   * Makes a new copy of the question.
   * @return a new copy of the question.
   */
  public Question copy();
}