import jamiebalfour.zpe.parser.ZenithParsingEngine;

public class GnalCompiler {

  private ZenithParsingEngine parser;

  public GnalCompiler(String code){
     parser = new ZenithParsingEngine(code, false, new GnalTokens());

  }

  public AST compile(){
    AST output = new AST();
    AST current = output.next;
    parser.getNextSymbol();
    while(parser.hasNext() && parser.getCurrentSymbol() != -128){

      current = compile_next();

      parser.getNextSymbol();
      current = current.next;

    }

    return output;
  }

  private AST compile_next(){
    if(parser.getCurrentSymbol() == GnalTokens.STRING_TYPE){
      return compile_string();
    }
    if(parser.getCurrentSymbol() == GnalTokens.PRINT){
      return compile_print();
    }

    //This will crash it, but that's fine
    return null;

  }

  private AST compile_string(){
    AST node = new AST();
    node.type = GnalTokens.STRING_TYPE;

    node.value = parser.getCurrentWord();

    return node;
  }

  private AST compile_print(){
    AST node = new AST();
    node.type = GnalTokens.PRINT;

    parser.getNextSymbol();
    node.value = compile_next();

    return node;
  }

}
