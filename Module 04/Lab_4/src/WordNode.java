/**
 * WordNode is a class that represents a word in a sentence.
 */
public class WordNode extends AbstractNode {
  private String word;

  /**
   * This constructor takes a String and a Sentence as parameters.
   * @param word the String to be set as the word.
   * @param next the Sentence to be set as the next Sentence.
   */
  public WordNode(String word, Sentence next) {
    super(next);
    this.word = word;
  }

  /**
   * Returns the number of words in this sentence.
   * @return int the number of words in this sentence.
   */
  @Override
  public int getNumberOfWords() {
    return 1 + super.getNumberOfWords();
  }

  /**
   * Returns the longest word in this sentence.
   * @return String the longest word in this sentence.
   */
  @Override
  public String longestWord() {
    String longest = super.longestWord();
    if (word.length() > longest.length()) {
      return word.replaceAll("[^a-zA-Z0-9]", "");
    } else {
      return longest;
    }
  }

  /**
   * Returns a string representation of this sentence.
   * @return String a string representation of this sentence.
   */
  @Override
  public String toString() {
    if (next instanceof EmptyNode) {
      return word + ".";
    } else if (next instanceof PunctuationNode) {
      return word + next.toString();
    } else {
      return word + " " + next.toString();
    }
  }

  /**
   * Returns a clone of this sentence.
   * @return Sentence a clone of this sentence.
   */
  @Override
  public Sentence merge(Sentence other) {
    return new WordNode(word, next.merge(other));
  }

  /**
   * Merges this sentence with another sentence.
   * @return Sentence a clone of this sentence.
   */
  @Override
  public Sentence clone() {
    return new WordNode(word, next.clone());
  }

}