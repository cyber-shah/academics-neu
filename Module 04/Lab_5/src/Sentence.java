import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  private boolean isPunctuation(String word) {
    return word.matches("\\p{Punct}");
  }

  public int getNumberOfWords() {
    return words_List.size();
  }

  public String longestWord() {
    String longest = "";
    for (String word : words_List) {
      if (!isPunctuation(word) && word.length() > longest.length()) {
        longest = word;
      }
    }
    return longest;
  }

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

  public Sentence clone() {
    return new Sentence(this.toString());
  }

  public void merge(Sentence other) {
    String result = this.toString();
  }

}