/**
 * AbstractNode.
 * This is a super class for WordNode and PunctuationNode.
 */
public abstract class AbstractNode implements Sentence {
  protected Sentence next;

  /**
   * This constructor takes a Sentence as a parameter.
   * @param next the Sentence to be set as the next Sentence.
   */
  public AbstractNode(Sentence next) {
    this.next = next;
  }

  /**
   * Returns the number of words in this sentence.
   * @return int the number of words in this sentence.
   */
  @Override
  public int getNumberOfWords() {
    return next.getNumberOfWords();
  }

  /**
   * Returns the longest word in this sentence.
   * @return String the longest word in this sentence.
   */
  @Override
  public String longestWord() {
    return next.longestWord();
  }

  /**
   * Returns a string representation of this sentence.
   * @return String a string representation of this sentence.
   */
  @Override
  public String toString() {
    return next.toString();
  }

  /**
   * Returns a clone of this sentence.
   * @return Sentence a clone of this sentence.
   */
  @Override
  public abstract Sentence clone();

  /**
   * Merges this sentence with another sentence.
   * @param other the sentence to merge with this sentence.
   * @return Sentence the merged sentence.
   */
  @Override
  public abstract Sentence merge(Sentence other);

}