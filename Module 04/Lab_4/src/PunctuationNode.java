/**
 * This class represents a punctuation node.
 */

public class PunctuationNode extends AbstractNode {
  /**
   * The punctuation of this PunctuationNode.
   */

  private String punctuation;

  /**
   * This constructor takes a String and a Sentence as parameters.
   * @param punctuation the String to be set as the punctuation.
   * @param next the Sentence to be set as the next Sentence.
   */
  public PunctuationNode(String punctuation, Sentence next) {
    super(next);
    this.punctuation = punctuation;
  }

  /**
   * Returns the number of words in this sentence.
   * @return int the number of words in this sentence.
   */
  @Override
  public String toString() {
    return punctuation + super.toString();
  }

  /**
   * Returns a clone of this sentence.
   * @return Sentence a clone of this sentence.
   */
  @Override
  public Sentence clone() {
    return new PunctuationNode(punctuation, next.clone());
  }

  /**
   * Merges this sentence with another sentence.
   * @param other the sentence to merge with this sentence.
   * @return Sentence the merged sentence.
   */
  public Sentence merge(Sentence other) {
    return new PunctuationNode(punctuation, next.merge(other));
  }
}