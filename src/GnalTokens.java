import jamiebalfour.zpe.parser.Tokeniser;
import jamiebalfour.zpe.parser.ZenithParsingEngine;

import java.util.ArrayList;

public class GnalTokens implements Tokeniser {

  public static byte STRING_TYPE = 0;
  public static byte NUMBER_TYPE = 0;
  public static byte BOOLEAN_TYPE = 0;


  @Override
  public byte stringToByteCode(String s) {
    switch (s) {
      case "string" -> {
        return GnalTokens.STRING_TYPE;
      }
      case "number" -> {
        return GnalTokens.NUMBER_TYPE;
      }
      case "boolean" -> {
        return GnalTokens.BOOLEAN_TYPE;
      }
    }
    //Unknown
    return -1;
  }

  @Override
  public String symbolToString(int i) {
    return "";
  }

  @Override
  public String delimiterCharacters() {
    return "";
  }

  @Override
  public String quoteTypes() {
    return "";
  }

  @Override
  public String[] listOfSubsequentCharacters() {
    return new String[0];
  }

  @Override
  public String[] listOfBoundWords() {
    return new String[0];
  }

  @Override
  public String[] listOfWhitespaces() {
    return new String[0];
  }

  @Override
  public ArrayList<ZenithParsingEngine.MultilineComment> listOfComments() {
    return null;
  }
}
