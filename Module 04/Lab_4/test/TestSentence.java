import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the Sentence interface.
 */

public class TestSentence {
  /**
   * These are the sentences used for testing.
   */
  Sentence s1 = new WordNode("Hello", new WordNode("World", new EmptyNode()));
  Sentence s2 = new WordNode("This", new PunctuationNode(",", new WordNode("has",
          new PunctuationNode(".", new WordNode("punctuation", new EmptyNode())))));
  Sentence s3 = new WordNode("This", new WordNode("has", new WordNode("no",
          new WordNode("punctuation", new EmptyNode()))));


  @Test
  public void testGetNumberOfWords() {
    assertEquals(2, s1.getNumberOfWords());
    assertEquals(3, s2.getNumberOfWords());
    assertEquals(4, s3.getNumberOfWords());
  }

  @Test
  public void testLongestWord() {
    assertEquals("World", s1.longestWord());
    assertEquals("punctuation", s2.longestWord());
    assertEquals("punctuation", s3.longestWord());
  }

  @Test
  public void testToString() {
    assertEquals("Hello World.", s1.toString());
    assertEquals("This,has.punctuation.", s2.toString());
    assertEquals("This has no punctuation.", s3.toString());
  }

  @Test
  public void testClone() {
    Sentence s1Clone = s1.clone();
    Sentence s2Clone = s2.clone();
    Sentence s3Clone = s3.clone();

    assertEquals(s1.toString(), s1Clone.toString());
    assertEquals(s2.toString(), s2Clone.toString());
    assertEquals(s3.toString(), s3Clone.toString());
  }

  @Test
  public void testMerge() {
    Sentence s1Clone = s1.clone();
    Sentence s2Clone = s2.clone();
    Sentence s3Clone = s3.clone();

    Sentence s1s2 = s1.merge(s2);
    Sentence s2s3 = s2.merge(s3);
    Sentence s3s1 = s3.merge(s1);

    assertEquals("Hello World This,has.punctuation.", s1s2.toString());
    assertEquals("This,has.punctuation This has no punctuation.", s2s3.toString());
    assertEquals("This has no punctuation Hello World.", s3s1.toString());

    assertEquals(s1Clone.toString(), s1.toString());
    assertEquals(s2Clone.toString(), s2.toString());
    assertEquals(s3Clone.toString(), s3.toString());
  }

}
