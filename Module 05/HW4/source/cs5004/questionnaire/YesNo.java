package cs5004.questionnaire;

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

  @Override
  public void answer(String answer) {
    // create an array of answers to iterate through
    YesNoOptions[] valid_answers = YesNoOptions.values();
    // for each option in valid answers
    for (YesNoOptions options : valid_answers) {
      // if any answer matches the name of that option
      if (options.name().equalsIgnoreCase(answer)) {
        // set the answer
        super.setAnswer(options.name());
        return;
      }
    }
    // else throw error
    throw new IllegalArgumentException("Must be a Yes or No");
  }
}

enum YesNoOptions {Yes, No};
