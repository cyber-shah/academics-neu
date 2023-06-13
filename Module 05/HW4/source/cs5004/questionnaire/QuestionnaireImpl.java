package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class QuestionnaireImpl implements Questionnaire{
  private List<Question> questions;

  public QuestionnaireImpl() {
    questions = new ArrayList<Question>();
  }

  /**
   * Add a question to the questionnaire.
   *
   * @param identifier a name for the question <b>unique</b> within this questionnaire. Not null
   *                   or empty.
   * @param q          the {@link Question} to be added to the questionnaire
   */
  public void addQuestion(String identifier, Question q) {
    questions.add(q);
  }

  /**
   * Remove the question with the given identifier from the questionnaire.
   *
   * @param identifier the identifier of the question to be removed.
   * @throws NoSuchElementException if there is no question with the given identifier.
   */
  public void removeQuestion(String identifier) {

  }

  /**
   * Get the question with the given number, based on the order in which it was added to the
   * questionnaire, or the sorted order if the {@code sort()} method is called. The first question
   * is 1, second 2, etc.
   *
   * @param num the number of the question, counting from 1
   * @return the question
   * @throws IndexOutOfBoundsException if there is no such question num
   */
  public Question getQuestion(int num) {
    return null;
  }

  /**
   * Get the question with the given identifier (question having been previously added to the
   * questionnaire).
   *
   * @param identifier the identifier of the question
   * @return the question
   * @throws NoSuchElementException if there is no question with the identifier
   */
  public Question getQuestion(String identifier) {
    return null;
  }

  /**
   * Return a list of all required questions in the questionnaire.
   *
   * @return the required questions.
   */
  public List<Question> getRequiredQuestions() {
    return null;
  }

  /**
   * Return a list of all optional questions in the questionnaire.
   *
   * @return the optional questions.
   */
  public List<Question> getOptionalQuestions() {
    return null;
  }

  /**
   * Report if all required questions have some non-empty response.
   *
   * @return true if all required questions have responses, false otherwise.
   */
  public boolean isComplete() {
    return false;
  }

  /**
   * Return a list of just the responses to all the questions in the questionnaire.
   *
   * @return the responses
   */
  public List<String> getResponses() {
    return null;
  }

  /**
   * Produce a new questionnaire containing just the questions where the given predicate returns
   * true. The returned questionnaire is completely independent of this questionnaire. That is,
   * the questions in the returned questionnaire are <b>copies</b> of the original questions.
   *
   * @param pq the predicate
   * @return the new questionnaire
   */
  public Questionnaire filter(Predicate<Question> pq) {
    return null;
  }

  /**
   * Sort the questions according to the given comparator. Return values from
   * {@code getQuestion(int)} should reflect the new sorted order following sort.
   *
   * @param comp a comparator for Question
   */
  public void sort(Comparator<Question> comp) {

  }

  /**
   * Produce a single summary value based on the given folding function and
   * seed value.
   *
   * @param bf   the folding function
   * @param seed the seed value
   * @return the summary value
   */
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    return null;
  }
}