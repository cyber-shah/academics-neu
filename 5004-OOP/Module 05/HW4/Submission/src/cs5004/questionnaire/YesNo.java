package cs5004.questionnaire;

/**
 * Represents a yes or no question. Extends the AbstractQuestion class.
 * Overrides the answer method to make sure that the answer is of the correct type.
 */
public class YesNo extends AbstractQuestion {

  /**
   * Constructor.
   *
   * @param question   the prompt.
   * @param isRequired whether the question is required.
   */
  public YesNo(String question, boolean isRequired) {
    super(question, isRequired);
  }

  /**
   * Constructor. Protected so that it can be used by the copy method.
   * @param question the prompt.
   * @param isRequired whether the question is required.
   * @param answer the answer.
   */
  protected YesNo(String question, boolean isRequired, String answer) {
    super(question, isRequired, answer);
  }

  /**
   * Validates the answer input whether its a yes or no.
   *
   * @param answer the answer.
   */
  @Override
  public void answer(String answer) {
    if (answer == null) {
      throw new IllegalArgumentException("Answer cannot be null");
    }

    // create an array of answers to iterate through
    String[] valid_answers = new String[] {"Yes", "No"};
    // for each option in valid answers
    for (String option : valid_answers) {
      // if any answer matches the name of that option
      if (option.equalsIgnoreCase(answer)) {
        // set the answer
        super.setAnswer(answer);
        return;
      }
    }
    // else throw error
    throw new IllegalArgumentException("Must be a Yes or No");
  }
}
