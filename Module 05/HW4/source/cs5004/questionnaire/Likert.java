package cs5004.questionnaire;

/**
 * This class represents a Likert question in a questionnaire.
 * A Likert question is a question that asks the user to choose from a set of five options.
 * The options are Strongly Agree, Agree, Neutral, Disagree, and Strongly Disagree.
 * The user must enter one of these five options, case-insensitive.
 */
public class Likert extends AbstractQuestion {

  /**
   * Constructor.
   *
   * @param question the question prompt.
   * @param isRequired whether the question is required.
   */
  public Likert(String question, boolean isRequired) {
    super(question, isRequired);
  }

  /**
   * Validates that the answer is one of the five Likert response options, case-insensitive.
   * If the answer is valid, sets the answer attribute to the answer.
   * If the answer is invalid, prints "Invalid answer!" to the console.
   *
   * @param answer the answer to validate.
   */
  @Override
  public void answer(String answer) {
    // create an array of all valid options
    LikertResponseOption[] options = LikertResponseOption.values();
    // for each option in the array
    for (LikertResponseOption option : options) {
      // if option equals answer AKA input, set it as attribute
      if (option.getText().equalsIgnoreCase(answer)) {
/*         cannot use this.answer = option.getText(); because there is no answer attribute here
         we are only using super classes attributes and assigning them values.
         if we would wish to do use this.answer = option.getText(); we would need to create
         an answer attribute in this class. but then the super classes method will not
         be able to access them, rendering all super class methods useless!*/
        this.setAnswer(option.getText());
        return;
      }
    }
    // if no option equals answer
    throw new IllegalArgumentException ("Invalid answer!");
  }
}
