package edu.pdx.cs410J.whitlock;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static edu.pdx.cs410J.whitlock.Student.validateArguments;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  void studentNamedPatIsNamedPat() {
    // GIVEN that there is a student named "Pat"
    String name = "Pat";
    Student pat = createStudentNamed(name);

    // WHEN Pat's name is requested
    // THEN Pat's name is "Pat"
    assertThat(pat.getName(), equalTo(name));
  }

  private Student createStudentNamed(String name) {
    return new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
  }

  @Test
  void allStudentsSayThisClassIsTooMuchWork() {
    // GIVEN that there is a student
    var student = createStudentNamed("Student");

    // WHEN Pat is asked to say something
    // THEN Pat says "This class is too much work"
    assertThat(student.says(), equalTo("This class is too much work"));
  }

  @Test
  void zeroArgumentsReturnsMissingCommandLineArguments() {
    assertThat(validateArguments(), equalTo("Missing command line arguments"));
  }

  @Test
  void onlyOneArgumentReturnsMissingGender() {
    assertThat(validateArguments("Name"), equalTo("Missing gender"));
  }

  @Test
  void onlyNameAndGenderReturnsMissingGpa() {
    assertThat(validateArguments("Name", "Gender"), equalTo("Missing GPA"));
  }

  @Test
  void studentEnrolledInZeroClassesIsValid() {
    assertThat(validateArguments("Dave", "male", "3.64"), nullValue());
  }

}
