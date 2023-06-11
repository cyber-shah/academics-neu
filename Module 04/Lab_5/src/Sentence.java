import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
   * Returns the number of words in the sentence.
   * @return int number of words.
   */
  public int getNumberOfWords() {
    AtomicInteger num = new AtomicInteger();
    HigherOrderFunctions.fold(0, words_List, (word) -> {
      num.addAndGet(1);
      return num;
    });
    return num.get();
  }


  /**
   * Returns the longest word in the sentence.
   * @return string representation of the longest word
   */
  public String longestWord() {
    AtomicReference<String> longWord = new AtomicReference<>("");
    HigherOrderFunctions.filter(words_List, (word) -> {
      if (word.length() > longWord.get().length() && !isPunctuation(word)) {
        longWord.set(word);
      }
      return longWord;
    });
    return longWord.get();
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
    AtomicInteger count = new AtomicInteger();
    HigherOrderFunctions.fold(0, words_List, (word) -> {
      if (isPunctuation(word)) {
        count.getAndIncrement();
      }
      return count;
    });
    return count.get();
  }

  /**
   * Returns the number of words that contain the letter 'z'.
   * @return the number of words that contain the letter 'z'.
   */
  public int countZWords() {
    AtomicInteger count = new AtomicInteger();
    HigherOrderFunctions.fold(0, words_List, (word) -> {
      if (word.contains("z") || word.contains("Z")) {
        count.getAndIncrement();
      }
      return count;
    });
    return count.get();
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

  /**
   * Returns true if the word is a vowel.
   * @param s a string to check.
   * @return true if the word is a vowel.
   */
  private boolean isVowel(String s) {
    return s.matches("[aeiouAEIOU]");
  }


  /**
   * Returns true if the word is a punctuation.
   * @param word a string to check.
   * @return true if the word is a punctuation.
   */
  private boolean isPunctuation(String word) {
    return word.matches("\\p{Punct}");
  }

}