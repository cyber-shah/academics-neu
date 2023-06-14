package cs5004.questionnaire;

/**
 * Represents an abstract question.
 * Which is then extended by the YesNo, Likert and ShortAnswer classes.
 * By overriding the answer method, we can make sure that the answer is
 * of the correct type.
 */
public class AbstractQuestion implements Question {

  private final String question;
  private final boolean isRequired;
  private String answer;

  /**
   * Constructor.
   *
   * @param question the prompt.
   * @param isRequired whether the question is required.
   */
  public AbstractQuestion(String question, boolean isRequired) {
    this.question = question;
    this.isRequired = isRequired;
    this.answer = "";
  }

  /**
   * Constructor. Protected so that it can be used by the copy method.
   * @param question the prompt.
   * @param isRequired whether the question is required.
   * @param answer the answer.
   */
  protected AbstractQuestion(String question, boolean isRequired, String answer) {
    this.question = question;
    this.isRequired = isRequired;
    this.answer = answer;
  }

  /**
   * Getter for the prompt.
   * @return the prompt.
   */
  public String getPrompt() {
    return this.question;
  }

  /**
   * Getter for whether the question is required.
   * @return whether the question is required.
   */
  public boolean isRequired() {
    return this.isRequired;
  }

  /**
   * Getter for the answer.
   * @return the answer.
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Sets the answer to the given answer.
   * @param answer the answer.
   */
  public void answer(String answer) {
    this.answer = answer;
  }

  /**
   * Makes a new copy of the question.
   * @return the copy.
   */
  public Question copy() {
    if (this instanceof YesNo) {
      return new YesNo(this.question, this.isRequired, this.answer);
    }
    else if (this instanceof Likert) {
      return new Likert(this.question, this.isRequired, this.answer);
    }
    else {
      return new ShortAnswer(this.question, this.isRequired, this.answer);
    }
  }

  /**
   * Protected setter for the answer.
   * @param answer the answer.
   */
  protected void setAnswer(String answer) {
    this.answer = answer;
  }
}
