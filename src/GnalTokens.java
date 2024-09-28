import jamiebalfour.zpe.parser.Tokeniser;
import jamiebalfour.zpe.parser.ZenithParsingEngine;

import java.util.ArrayList;

public class GnalTokens implements Tokeniser {

  final static String INTEGER_REGEX = "[+-]?\\d+";
  final static String DOUBLE_REGEX = "[+-]?\\d*\\.?\\d+([eE][+-]?\\d+)?";
  final static String STRING_REGEX = "(\"(.|\\s)*\")|('(.\\s)*')";

  public static byte STRING_TYPE = 0;
  public static byte NUMBER_TYPE = 1;
  public static byte PRINT = 2;
  public static byte ADD = 3;
  public static byte SUBTRACT = 4;
  public static byte DIVIDE = 5;
  public static byte MULTIPLY = 6;


  /**
   * The tokeniser requires a word to be passed
   * @param s The word or string passed in
   * @return the byte code for the word, if appropriate
   */
  @Override
  public byte stringToByteCode(String s) {
    if(s.matches(STRING_REGEX)){
      return GnalTokens.STRING_TYPE;
    }
    if(s.matches(INTEGER_REGEX) || s.matches(DOUBLE_REGEX)){
      return GnalTokens.NUMBER_TYPE;
    }
    switch (s) {
      case "+" -> {
        return GnalTokens.ADD;
      }
      case "-" -> {
        return GnalTokens.SUBTRACT;
      }
      case "*" -> {
        return GnalTokens.MULTIPLY;
      }
      case "/" -> {
        return GnalTokens.DIVIDE;
      }
      case "print" -> {
        return GnalTokens.PRINT;
      }
    }
    //Unknown symbol
    return -1;
  }

  @Override
  public String symbolToString(int i) {
    return "";
  }

  @Override
  public String delimiterCharacters() {
    return " +-*/\r\n" + System.lineSeparator();
  }

  @Override
  public String quoteTypes() {
    return "\"'";
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
    return new String[]{" ", "" + '\n', "" + '\r', "\r\n", "" + '\t', "" + ((char) 160), System.lineSeparator()};
  }

  @Override
  public ArrayList<ZenithParsingEngine.ZPEComment> listOfComments() {
    ArrayList<ZenithParsingEngine.ZPEComment> a = new ArrayList<>();
    a.add(new ZenithParsingEngine.ZPEComment("/*", "*/"));
    a.add(new ZenithParsingEngine.ZPEComment("//", System.lineSeparator()));
    return a;
  }
}
