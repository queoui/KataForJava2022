package edu.pdx.cs410J.whitlock;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
                                                                                    
/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {                                                
                                                                                    
  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male", "female", or "other", case insensitive)
   */                                                                               
  public Student(String name, ArrayList<String> classes, double gpa, String gender) {
    super(name);
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    return "This class is too much work";
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    String errorMessage = validateArguments(args);

    if (errorMessage != null) {
      System.err.println(errorMessage);
    }
  }

  @VisibleForTesting
  static String validateArguments(String... args) {
    if (args.length == 0) {
      return "Missing command line arguments";

    } else if (args.length == 1) {
      return "Missing gender";

    } else if (args.length == 2) {
      return "Missing GPA";

    } else {
      Gender gender = validateGender(args[1]);
      if (gender == null) {
        return "Unrecognized gender";
      }
      return null;
    }
  }

  @VisibleForTesting
  static Gender validateGender(String gender) {
    if (gender.equalsIgnoreCase("other")) {
      return Gender.OTHER;

    } else if (gender.equalsIgnoreCase("female")) {
      return Gender.FEMALE;

    } else if (gender.equalsIgnoreCase("male")) {
      return Gender.MALE;
    }

    return null;
  }
}