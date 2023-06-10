import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * A class that represents a sentence.
 */
public class Sentence {
  private final List<String> words_List;

  /**
   * Constructor.
   * @param words a string of words separated by spaces
   */
  public Sentence(String words) {
    words_List = new ArrayList<>();
    String[] wordArray = words.split("\\s+");
    if (wordArray.length == 1 && wordArray[0].equals("")) {
      return;
    }
    words_List.addAll(Arrays.asList(wordArray));
  }

  /**
   * A function that takes in a list of type T and returns a value of type R.
   * @param init initial value.
   * @param list a list of type T.
   * @param accumulator a function that takes in a value of type T and returns a value of type R.
   * @return a value of type R.
   * @param <T> type of the list.
   * @param <R> type of the return value.
   */
  private static <T, R> R fold (R init, List<T> list, Function <T,R> accumulator){
    R result = init;
    for (T element : list){
      result = accumulator.apply(element);
    }
    return result;
  }

  /**
   * Returns the number of words in the sentence.
   * @return int number of words.
   */
  public int getNumberOfWords() {
    AtomicInteger num = new AtomicInteger();
    fold(0, words_List, (word) -> {
      num.addAndGet(1);
      return num;
    });
    return num.get();
  }

  /**
   * Returns true if the word is a punctuation.
   * @param word a string to check.
   * @return true if the word is a punctuation.
   */
  private boolean isPunctuation(String word) {
    return word.matches("\\p{Punct}");
  }



  /**
   * Returns the longest word in the sentence.
   * @return string representation of the longest word
   */
  public String longestWord() {
    String longest = "";
    for (String word : words_List) {
      if (!isPunctuation(word) && word.length() > longest.length()) {
        longest = word;
      }
    }
    return longest;
  }

  /**
   * Returns a string representation of the sentence.
   * @return a string representation of the sentence
   */
  public String toString() {
    String result = "";
    for (int i = 0; i < words_List.size(); i++) {
      if (i == words_List.size() - 1) {
        result += words_List.get(i);
        break;
      }
      else {
        result += words_List.get(i) + " ";
      }
    }
    return result;
  }

  /**
   * Returns a copy of the sentence.
   * @return a new sentence with the same words.
   */
  public Sentence clone() {
    return new Sentence(this.toString());
  }

  /**
   * Merges two sentences.
   * @param other another sentence.
   * @return a new sentence with the words from this and other.
   */
  public Sentence merge(Sentence other) {
    String result = this.toString() + " " + other.toString();
    return new Sentence(result);
  }

  /**
   * Returns the number of punctuations in the sentence.
   * @return the number of punctuations in the sentence.
   */
  public int countPunctuation() {
    int count = 0;
    for (String word : words_List) {
      if (isPunctuation(word)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns the number of words that contain the letter 'z'.
   * @return the number of words that contain the letter 'z'.
   */
  public int countZWords() {
    int count = 0;
    for (String word : words_List) {
      if (word.contains("z") || word.contains("Z")){
        count++;
      }
    }
    return count;
  }

  /**
   * Converts the sentence to pig latin.
   * @return string representation of the sentence in pig latin.
   */
  public String pigLatin() {
    if (words_List.size() == 0) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    for (String word : words_List) {
      if (isPunctuation(word)) {
        result.append(" ").append(word);
      }
      else {
        // if 1st word is consonant
        if (! isVowel(String.valueOf(word.charAt(0)))) {
          // if 2nd word is also a consonant
          // move them to the end and add "ay"
          if (! isVowel(String.valueOf(word.charAt(1)))) {
            result.append(" ").append(word.substring(2)).append(word.substring(0, 2)).append("ay");
          }
          // if 2nd word is a vowel
          // move it to the end and add "ay"
          else if (isVowel(String.valueOf(word.charAt(1)))) {
            result.append(" ").append(word.substring(1)).append(word.charAt(0)).append("ay");
          }
        }
        // if 1st word is a vowel
        // add "way" to the end
        else if (isVowel(String.valueOf(word.charAt(0)))) {
          result.append(" ").append(word).append("way");
        }
      }
    }
    return result.toString().trim();
  }
  private boolean isVowel(String s) {
    return s.matches("[aeiouAEIOU]");
  }
}