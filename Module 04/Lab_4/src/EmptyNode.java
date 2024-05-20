/**
 * This class represents an empty node.
 */

public class EmptyNode implements Sentence {

  /**
   * Returns the number of words in this sentence.
   * @return int the number of words in this sentence.
   */
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Returns the longest word in this sentence.
   * @return String the longest word in this sentence.
   */
  public String longestWord() {
    return "";
  }

  /**
   * Returns a string representation of this sentence.
   * @return String a string representation of this sentence.
   */
  public String toString() {
    return ".";
  }

  /**
   * Returns a clone of this sentence.
   * @return Sentence a clone of this sentence.
   */
  public Sentence clone() {
    return new EmptyNode();
  }

  /**
   * Merges this sentence with another sentence.
   * @param other the sentence to merge with this sentence.
   * @return Sentence the merged sentence.
   */
  public Sentence merge(Sentence other) {
    return other.clone();
  }
}