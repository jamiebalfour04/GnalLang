import jamiebalfour.zpe.parser.ZenithParsingEngine;

public class GnalCompiler {

  private ZenithParsingEngine parser;

  public GnalCompiler(String code){
     parser = new ZenithParsingEngine(code, false, new GnalTokens());
  }

}
