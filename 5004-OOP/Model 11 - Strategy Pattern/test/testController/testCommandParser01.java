package testController;

import controller.CommandParser;
import controller.CommandType;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class testCommandParser01 {

  CommandParser commandParser;

  @Test
  public void testCommandParser() {
    String args = """
            #load koala.ppm and call it 'koala'
            load images/koala.ppm koala
            #load koala.ppm and call it 'koala'
            load images/koala.ppm koala
            #create a greyscale using only the value component, as an image koala-greyscale
            value-component koala koala-greyscale#create a greyscale using only the value component, as an image koala-greyscale
            value-component koala koala-greyscale""";
    StringBuilder stringBuilder = new StringBuilder();
    commandParser = new CommandParser(new StringReader(args), stringBuilder);

    commandParser.parse();
    assertEquals(CommandType.LOAD, commandParser.getCommandType());
  }

}
