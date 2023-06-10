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

  @Test
  public void testClone() {
    // base case
    Sentence s5 = s1.clone();
    assertEquals(s1.toString(), s5.toString());
    assertEquals(s1.getNumberOfWords(), s5.getNumberOfWords());
    assertEquals(s1.longestWord(), s5.longestWord());

    // empty string
    Sentence s6 = s2.clone();
    assertEquals(s2.toString(), s6.toString());
    assertEquals(s2.getNumberOfWords(), s6.getNumberOfWords());
    assertEquals(s2.longestWord(), s6.longestWord());

    // only punctuations
    Sentence s7 = s3.clone();
    assertEquals(s3.toString(), s7.toString());
    assertEquals(s3.getNumberOfWords(), s7.getNumberOfWords());
    assertEquals(s3.longestWord(), s7.longestWord());
  }

  @Test
  public void testMerge() {
    // base case
    Sentence s5 = new Sentence("This is another test");
    Sentence s6 = s1.merge(s5);
    assertEquals("This is a test ! This is another test", s6.toString());
    assertEquals(9, s6.getNumberOfWords());
    assertEquals("another", s6.longestWord());

    // two empty strings
    Sentence s7 = s2.merge(s2);
    assertEquals("", s7.toString());
    assertEquals(0, s7.getNumberOfWords());
    assertEquals("", s7.longestWord());

    // one empty string
    Sentence s8 = s1.merge(s2);
    assertEquals("This is a test !", s8.toString());
    assertEquals(5, s8.getNumberOfWords());
    assertEquals("This", s8.longestWord());
  }

  @Test
  public void testCountPunctuation() {
    // base case
    assertEquals(1, s1.countPunctuation());

    // empty string
    assertEquals(0, s2.countPunctuation());

    // only punctuations
    assertEquals(5, s3.countPunctuation());

    // one word
    assertEquals(0, s4.countPunctuation());
  }

  @Test
  public void testCountZWords() {
    // base case - no z words
    assertEquals(0, s1.countZWords());

    // empty string
    assertEquals(0, s2.countZWords());

    // only punctuations
    assertEquals(0, s3.countZWords());

    // multiple z words
    Sentence sZ = new Sentence("Zombie is a Zebra");
    assertEquals(2, sZ.countZWords());

    // multiple z but one word
    Sentence sZ1 = new Sentence("zzzzzzz");
    assertEquals(1, sZ1.countZWords());
  }

  @Test
  public void testPigLatin() {
    // base case
    assertEquals("histay isway away esttay !", s1.pigLatin());

    // empty string
    assertEquals("", s2.pigLatin());

    // only punctuations
    assertEquals("! @ , & *", s3.pigLatin());

    // one word
    assertEquals("OneWordway", s4.pigLatin());
  }
}