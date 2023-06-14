package cs5004.questionnaire;

public class ShortAnswer extends AbstractQuestion{

  public ShortAnswer(String q, boolean isRequired) {
    super(q, isRequired);
  }

  @Override
  public void answer(String answer) {
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