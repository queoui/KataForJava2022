package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.whitlock.Student.MissingCommandLineArguments;
import edu.pdx.cs410J.whitlock.Student.UnrecognizedGenderException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static edu.pdx.cs410J.whitlock.Student.createStudentFrom;
import static edu.pdx.cs410J.whitlock.Student.validateGender;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    return new Student(name, new ArrayList<>(), 0.0, Gender.OTHER);
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
    MissingCommandLineArguments ex =
      assertThrows(MissingCommandLineArguments.class, Student::createStudentFrom);
    assertThat(ex.getMessage(), equalTo("Missing command line arguments"));
  }

  @Test
  void onlyOneArgumentReturnsMissingGender() {
    MissingCommandLineArguments ex =
      assertThrows(MissingCommandLineArguments.class, () -> createStudentFrom("Name"));
    assertThat(ex.getMessage(), equalTo("Missing gender"));
  }

  @Test
  void onlyNameAndGenderReturnsMissingGpa() {
    MissingCommandLineArguments ex =
      assertThrows(MissingCommandLineArguments.class, () -> createStudentFrom("Name", "Gender"));
    assertThat(ex.getMessage(), equalTo("Missing GPA"));
  }

  @Test
  void studentEnrolledInZeroClassesIsValid() throws UnrecognizedGenderException, MissingCommandLineArguments {
    assertThat(createStudentFrom("Dave", "male", "3.64"), notNullValue());
  }
  
  @Test
  void unrecognizedGenderThrowsUnrecognizedGenderException() {
    String bogus = "bogus";
    UnrecognizedGenderException ex =
      assertThrows(UnrecognizedGenderException.class, () -> validateGender(bogus));
    assertThat(ex.getUnrecognizedGender(), equalTo(bogus));
  }

  @Test
  void otherIsValidGender() throws UnrecognizedGenderException {
    assertThat(validateGender("other"), equalTo(Gender.OTHER));
  }

  @Test
  void OTHERIsValidGender() throws UnrecognizedGenderException {
    assertThat(validateGender("OTHER"), equalTo(Gender.OTHER));
  }

  @Test
  void femaleIsValidGender() throws UnrecognizedGenderException {
    assertThat(validateGender("female"), equalTo(Gender.FEMALE));
  }

  @Test
  void FEMALEIsValidGender() throws UnrecognizedGenderException {
    assertThat(validateGender("FEMALE"), equalTo(Gender.FEMALE));
  }

  @Test
  void maleIsValidGender() throws UnrecognizedGenderException {
    assertThat(validateGender("male"), equalTo(Gender.MALE));
  }

  @Test
  void MALEIsValidGender() throws UnrecognizedGenderException {
    assertThat(validateGender("MALE"), equalTo(Gender.MALE));
  }

  @Test
  void canCreateStudentWithExpectedName() throws UnrecognizedGenderException, MissingCommandLineArguments {
    String name = "Dave";
    Student student = createStudentFrom(name, "male", "3.64");
    assertThat(student.getName(), equalTo(name));
  }

  @Test
  void canCreateStudentWithExpectedGender() throws UnrecognizedGenderException, MissingCommandLineArguments {
    Student student = createStudentFrom("Dave", "male", "3.64");
    assertThat(student.getGender(), equalTo(Gender.MALE));
  }

  @Test
  void toStringStartsWithStudentName() {
    String name = "Name";
    Student student = createStudentNamed(name);
    assertThat(student.toString(), startsWith(name));
  }

  @Test
  void canCreateStudentWithExpectedGpa() throws UnrecognizedGenderException, MissingCommandLineArguments {
    Student student = createStudentFrom("Name", "other", "3.45");
    assertThat(student.getGpa(), equalTo(3.45));
  }

  @Test
  void toStringContainsGPA() throws MissingCommandLineArguments, UnrecognizedGenderException {
    String gpa = "3.64";
    Student student = createStudentFrom("Name", "male", gpa);
    assertThat(student.toString(), containsString("has a GPA of " + gpa));
  }
}
