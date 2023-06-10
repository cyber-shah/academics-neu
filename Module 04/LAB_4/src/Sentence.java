/**
 * This interface represents a sentence.
 * A sentence is a sequence of words.
 */
public interface Sentence {

  /**
   * Returns the number of words in this sentence.
   * @return int the number of words in this sentence.
   */
  int getNumberOfWords();

  /**
   * Returns the longest word in this sentence.
   * @return String the longest word in this sentence.
   */
  String longestWord();

  /**
   * Returns a string representation of this sentence.
   * @return String a string representation of this sentence.
   */
  String toString();

  /**
   * Returns a clone of this sentence.
   * @return Sentence a clone of this sentence.
   */
  Sentence clone();

  /**
   * Merges this sentence with another sentence.
   * @param other the sentence to merge with this sentence.
   * @return Sentence the merged sentence.
   */
  Sentence merge(Sentence other);
}
