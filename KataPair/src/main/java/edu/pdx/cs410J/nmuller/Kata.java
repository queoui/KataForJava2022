package edu.pdx.cs410J.nmuller;

/**
 * A class for getting started with a code kata
 *
 * Use IntelliJ's "Refactor | Rename..." command to change the name of this
 * class (and its tests).
 */
public class Kata {
                                                                                    

  public static void main(String[] args) {
    Kata newKata = new Kata();

    for(int i = 0; i < args.length; ++i){
      String var_final = newKata.compute(args[i]);
      System.out.println(var_final);
    }

    System.exit(1);
  }


  public static String compute(String arg){

    String var_final = "";
    int number = Integer.parseInt(arg);
    if (number % 3 == 0 ){
      //System.out.println("Foo");
      var_final += "Foo";
    }
    if (number % 5 == 0){
      //System.out.println("Bar");
      var_final += "Bar";
    }
    if (number % 7 == 0){
      //System.out.println("Qix");
      var_final += "Qix";
    }
    if("".equals(var_final))
      return arg;
  return var_final;
  }

}