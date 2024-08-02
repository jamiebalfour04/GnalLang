public class GnalRuntime {

  public GnalRuntime(String code){

    GnalCompiler c = new GnalCompiler(code);
    AST output = c.compile();
    AST current = output;
    while(current != null){
      run(current);
      current = current.next;
    }

  }

  private Object run(AST a){
    if(a.type == GnalTokens.PRINT){
      Object value = run((AST) a.value);
      if(value != null){
        System.out.println(value);
      }

      return value;
    } else if (a.type == GnalTokens.STRING_TYPE){
      return a.value;
    } else if (a.type == GnalTokens.NUMBER_TYPE){
      return a.value;
    } else if (a.type == GnalTokens.ADD){
      return Double.parseDouble(a.left.value.toString()) + Double.parseDouble(a.middle.value.toString());
    } else if (a.type == GnalTokens.SUBTRACT){
      return Double.parseDouble(a.left.value.toString()) - Double.parseDouble(a.middle.value.toString());
    } else if (a.type == GnalTokens.MULTIPLY){
      return Double.parseDouble(a.left.value.toString()) * Double.parseDouble(a.middle.value.toString());
    } else if (a.type == GnalTokens.DIVIDE){
      return Double.parseDouble(a.left.value.toString()) / Double.parseDouble(a.middle.value.toString());
    }

    return null;
  }

}
