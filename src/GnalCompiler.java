import jamiebalfour.zpe.parser.ZenithParsingEngine;

public class GnalCompiler {

  private ZenithParsingEngine parser;

  public GnalCompiler(String code){
     parser = new ZenithParsingEngine(code, false, new GnalTokens());

  }

  public AST compile(){
    AST output = new AST();
    AST current = output;
    parser.getNextSymbol();
    while(parser.hasNext() && parser.getCurrentSymbol() != -128){

      current.next = compile_next();

      parser.getNextSymbol();
      current = current.next;

    }

    return output.next;
  }

  private AST compile_next(){
    if(parser.getCurrentSymbol() == GnalTokens.STRING_TYPE){
      return compile_string();
    }
    if(parser.getCurrentSymbol() == GnalTokens.PRINT){
      return compile_print();
    }
    if(parser.getCurrentSymbol() == GnalTokens.NUMBER_TYPE && (parser.peekAhead() == GnalTokens.ADD || parser.peekAhead() == GnalTokens.SUBTRACT || parser.peekAhead() == GnalTokens.MULTIPLY || parser.peekAhead() == GnalTokens.DIVIDE)){
      return compile_mathematical_expression();
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

  private AST compile_mathematical_expression(){
    AST node = new AST();

    node.left = new AST();
    node.left.type = GnalTokens.NUMBER_TYPE;

    node.left.value = parser.getCurrentWord();

    parser.getNextSymbol();

    node.type = parser.getCurrentSymbol();

    parser.getNextSymbol();

    if(parser.getCurrentSymbol() != GnalTokens.NUMBER_TYPE){
      System.err.println("Incorrect data type in mathematical expression");
      System.exit(1);
    }

    node.middle = new AST();
    node.middle.type = GnalTokens.NUMBER_TYPE;

    node.middle.value = parser.getCurrentWord();

    return node;
  }

}
