public String jEscape(String stuff){
  if(stuff == null)
    return null;
  String ret = "";
  String c = "";
  int strlen = stuff.length();
  for(int i=0; i<strlen; i++){
    c = stuff.substring(i, i+1);
    if(c.equals("<"))
      ret += "&#060;";
    else if(c.equals(">"))
      ret += "&#062;";
    else if(c.equals("&"))
      ret += "&#038;";
    else if(c.equals("\""))
      ret += "&#034;";
    else if(c.equals("\t"))
      ret += "&#009;";
    else if(c.equals("!"))
      ret += "&#033;";
    else if(c.equals("#"))
      ret += "&#035;";
    else if(c.equals("$"))
      ret += "&#036;";
    else if(c.equals("%"))
      ret += "&#037;";
    else if(c.equals("'"))
      ret += "&#039;";
    else if(c.equals("("))
      ret += "&#040;";
    else if(c.equals(")"))
      ret += "&#041;";
    else if(c.equals("*"))
      ret += "&#042;";
    else if(c.equals("+"))
      ret += "&#043;";
    else if(c.equals(","))
      ret += "&#044;";
    else if(c.equals("-"))
      ret += "&#045;";
    else if(c.equals("."))
      ret += "&#046;";
    else if(c.equals("/"))
      ret += "&#047;";
    else if(c.equals(":"))
      ret += "&#058;";
    else if(c.equals(";"))
      ret += "&#059;";
    else if(c.equals("="))
      ret += "&#061;";
    else if(c.equals("?"))
      ret += "&#063;";
    else if(c.equals("@"))
      ret += "&#064;";
    else if(c.equals("["))
      ret += "&#091;";
    else if(c.equals("\\"))
      ret += "&#092;";
    else if(c.equals("]"))
      ret += "&#093;";
    else if(c.equals("^"))
      ret += "&#094;";
    else if(c.equals("_"))
      ret += "&#095;";
    else if(c.equals("`"))
      ret += "&#096;";
    else if(c.equals("{"))
      ret += "&#123;";
    else if(c.equals("|"))
      ret += "&#124;";
    else if(c.equals("}"))
      ret += "&#125;";
    else if(c.equals("~"))
      ret += "&#126;";
    else if(c.equals(" "))
      ret += "&#160;";
    else if(c.equals(" "))
      ret += "&#032;";
    else
      ret += c;
  }
  return ret;
}
