package questionnaire;

public class AbstractQuestion implements Question {

  public String getPrompt() {
    return null;
  }

  public boolean isRequired() {
    return false;
  }

  public String getAnswer() {
    return null;
  }

  public void answer(String answer) {
  }

  public Question copy() {
    return null;
  }
}
