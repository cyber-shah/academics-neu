package cs5004.questionnaire;

/**
 * Represents a short answer question. Extends the AbstractQuestion class.
 */
public class ShortAnswer extends AbstractQuestion {

  /**
   * Constructor.
   * @param q the prompt.
   * @param isRequired whether the question is required.
   */
  public ShortAnswer(String q, boolean isRequired) {
    super(q, isRequired);
  }

  /**
   * Constructor. Protected so that it can be used by the copy method.
   * @param q the prompt.
   * @param isRequired whether the question is required.
   * @param answer the answer.
   */
  protected ShortAnswer(String q, boolean isRequired, String answer) {
    super(q, isRequired, answer);
  }

  /**
   * Sets the answer to the given answer.
   * Validates if it is less than 280 characters.
   * @throws IllegalArgumentException if answer is more than 280 characters.
   * @param answer the answer.
   */
  @Override
  public void answer(String answer) {
    if (answer == null) {
      throw new IllegalArgumentException("Answer cannot be null");
    }
    // if length above 280
    if (answer.length() > 280) {
      throw new IllegalArgumentException("Short answers cannot be more than 280 characters");
    }
    // else set the answer
    else {
      super.setAnswer(answer);
    }
  }
}