import java.text.ParseException;
import java.io.IOException;

public class Student {

  private String Name;
  private int Age;
  private String BirthDay;

  public Student(String name, int age, String birthDay) {
    Name = name;
    Age = age;
    BirthDay = birthDay;
  }

  public String GetName() {return Name;}
  public int GetAge() {return Age;}
  public String GetBirthDay() {return BirthDay;}

  public String ToString() {
    return Name + " " + Integer.toString(Age)+ " " + BirthDay;
  }
    
  public static Student Parse(String str) throws IOException, StudentParseError {
    String[] data = str.split(" ");
    if(data.length != 3)throw new StudentParseError();
    return new Student(data[0], Integer.parseInt(data[1]), data[2]);
    }

}