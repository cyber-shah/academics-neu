package questionnaire;

public interface Question {
  public String getPrompt();
  public boolean isRequired();
  public String getAnswer();
  public void answer(String answer);
  public Question copy();
}