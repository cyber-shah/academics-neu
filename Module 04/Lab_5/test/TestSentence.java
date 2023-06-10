import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSentence {
  private Sentence s1;
  private Sentence s2;
  private Sentence s3;
  private Sentence s4;

  @Before
  public void setUp() {
    s1 = new Sentence("This is a test !");
    s2 = new Sentence("");
    s3 = new Sentence("! @ , & *");
    s4 = new Sentence("OneWord");
  }

  @Test
  public void testGetNumberOfWords() {
    assertEquals(5, s1.getNumberOfWords());
    assertEquals(0, s2.getNumberOfWords());
    assertEquals(5, s3.getNumberOfWords());
    assertEquals(1, s4.getNumberOfWords());
  }

  @Test
  public void testLongestWord() {
    // base case
    assertEquals("This", s1.longestWord());

    // two words of the same length
    Sentence s6 = new Sentence("Test longest longest words");
    assertEquals("longest", s6.longestWord());

    // empty string
    assertEquals("", s2.longestWord());

    // only punctuations
    assertEquals("", s3.longestWord());

    // one word
    assertEquals("OneWord", s4.longestWord());
  }

  @Test
  public void testToString() {
    assertEquals("This is a test !", s1.toString());
    assertEquals("", s2.toString());
    assertEquals("! @ , & *", s3.toString());
    assertEquals("OneWord", s4.toString());
  }
}