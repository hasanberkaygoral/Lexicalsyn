


import java.util.Arrays;
import java.util.Scanner;

public class NewClass {
    public static boolean isDelimiter(char ch) {
            if(ch == ' ' || ch == '+' || ch == '-' || ch == '*' || ch == '/' || 
               ch == ',' || ch == ';' || ch == '>' || 
               ch == '<' || ch == '=' || ch == '(' || ch == ')' || ch == '[' || 
               ch == ']' || ch == '{' || ch == '}') {
                    return true;
            }
            return false;
    }
    public static boolean isOperator(char ch) {
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || 
               ch == '>' || ch == '<' || ch == '=') {
                    return true;
            }
            return false;
    }
    public static boolean validIdentifier(String str) {
            if(str.charAt(0) == '0' || str.charAt(0) == '1' ||str.charAt(0) == '2' || 
               str.charAt(0) == '3' || str.charAt(0) == '4' || 
               str.charAt(0) == '5' || str.charAt(0) == '6' || str.charAt(0) == '7' || 
               str.charAt(0) == '8' || str.charAt(0) == '9' 
                    || isDelimiter(str.charAt(0)) == true) {
                    return false;
            }
            return true;
    }
    public static boolean isKeyword(String str) {
            if(str.equals("float") || str.equals("if") || str.equals("else") || str.equals("while") || 
                    str.equals("do") || str.equals("break") || str.equals("continue") || 
               str.equals("int") || str.equals("double") || str.equals("float") || str.equals("return") || 
                    str.equals("char") || str.equals("case") || str.equals("char") || 
               str.equals("sizeof") || str.equals("long") || str.equals("short") || str.equals("typedef") || 
                    str.equals("switch") || str.equals("unsigned") || str.equals("void") || 
               str.equals("static") || str.equals("struct") || str.equals("goto")) {
                    return true;
            }
            return false;//
    }      
    public static boolean isInteger(String str) {
            int len = str.length();
            if(len == 0) {
                    return false;
            }
            for(int i = 0; i < len; i++) {
                    if(str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2' &&str.charAt(i) != '3' && 
                       str.charAt(i) != '4' && str.charAt(i) != '5' && 
                       str.charAt(i) != '6' && str.charAt(i) != '7' && str.charAt(i) != '8' && str.charAt(i) != '9' || 
                       str.charAt(i) == '-' && i > 0) {
                            return false;
                    }
            }
            return true;
    }           
    public static char[] subString(char[] str, int sol, int sağ) {//ALT kelimeyi almak için 
            char[] subStr = new char[sağ - sol + 2];

            for(int i = sol; i <= sağ; i++) {
                    subStr[i - sol] = str[i];
            }
            subStr[sağ - sol + 1] = '\0';
            return subStr;
    }
    static String tree="";
    public static boolean lexicalAnalyzer(String str) {
      int sol = 0, sağ = 0;
      int len = str.length();
      while(sağ < len && sol <= sağ) {
        //System.out.println("r: "+sağ+" l: "+sol+" len: "+len);
          if(isDelimiter(str.charAt(sağ)) == false) {
                  sağ++;
          }
          if(isDelimiter(str.charAt(sağ)) == true && sol == sağ) {
                  if(isOperator(str.charAt(sağ)) == true) {
                          System.out.println(" Bu bir oparatördür");
                          tree+=" OPERATOR";
                  }
                  sağ++;
                  sol = sağ;
          }else if(isDelimiter(str.charAt(sağ))==true&&sol!=sağ || sağ==len&&sol!=sağ) {                     
              String subStr = str.substring(sol,sağ);             
                  if (isKeyword(subStr)) {
                      System.out.println( subStr+" Bu bir keyword dür \n"); 
                      tree+=" KEYWORD";
                  }
                  else if (isInteger(subStr) == true) {
                      System.out.println(" Bu bir integer değerdir  \n"+ subStr); 
                      tree+=" INTEGER";
                  }

                  else if (validIdentifier(subStr) == true && isDelimiter(str.charAt(sağ-1)) == false) {
                      System.out.println(" Bu bir değişkendir\n"+ subStr); 
                      tree+=" IDENTIFIER";
                  }
                  else if (validIdentifier(subStr) == false && isDelimiter(str.charAt(sağ-1)) == false) {
                      System.out.println(" Bu bir değişken değildir \n"+ subStr); 
                      return false;
                  }
           sol = sağ; 
        }       }
      return true;
    }
    
    public static boolean syntaxAnalyzer(String tree){
        Scanner sn = new Scanner(tree);
        String exp = sn.next();
        if(exp.equals("KEYWORD")){
            exp = sn.next();
            if(exp.equals("IDENTIFIER")){
                exp=sn.next();                             
                        if(exp.equals("OPERATOR") ){
                             exp=sn.next();
                            if(exp.equals("INTEGER") || exp.equals("IDENTIFIER") ){
                                return true;
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }             
            }else{
                return false;            
        }           
    }
                
    public static void main(String[] args) {
        String str = "int a 55;";   
        boolean lexicalAnalysis = lexicalAnalyzer(str);
        System.out.println("LEXİCAL Analiz sonucu "+lexicalAnalysis);
        
        if(lexicalAnalysis){
            System.out.println("Tree: "+tree);
            System.out.println("Syntax Analiz sonucu:"+syntaxAnalyzer(tree));
        }
    }               
}
