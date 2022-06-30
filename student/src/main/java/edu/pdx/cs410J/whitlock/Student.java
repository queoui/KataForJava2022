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
    try {
      createStudentFrom(args);

    } catch (UnrecognizedGenderException | MissingCommandLineArguments e) {
      System.err.println(e.getMessage());
    }

  }

  @VisibleForTesting
  static Student createStudentFrom(String... args) throws UnrecognizedGenderException, MissingCommandLineArguments {
    if (args.length == 0) {
      throw new MissingCommandLineArguments("Missing command line arguments");

    } else if (args.length == 1) {
      throw new MissingCommandLineArguments("Missing gender");

    } else if (args.length == 2) {
      throw new MissingCommandLineArguments("Missing GPA");

    } else {
      String name = args[0];
      validateGender(args[1]);
      return new Student(name, new ArrayList<>(), 1.23, "other");
    }
  }

  @VisibleForTesting
  static Gender validateGender(String gender) throws UnrecognizedGenderException {
    if (gender.equalsIgnoreCase("other")) {
      return Gender.OTHER;

    } else if (gender.equalsIgnoreCase("female")) {
      return Gender.FEMALE;

    } else if (gender.equalsIgnoreCase("male")) {
      return Gender.MALE;
    }

    throw new UnrecognizedGenderException(gender);
  }

  @VisibleForTesting
  static class UnrecognizedGenderException extends Exception {
    private final String unrecognized;

    public UnrecognizedGenderException(String unrecognized) {
      this.unrecognized = unrecognized;
    }

    public String getUnrecognizedGender() {
      return this.unrecognized;
    }

    @Override
    public String getMessage() {
      return "Unrecognized gender: " + this.getUnrecognizedGender();
    }
  }

  @VisibleForTesting
  static class MissingCommandLineArguments extends Exception {
    public MissingCommandLineArguments(String message) {
      super(message);
    }
  }
}