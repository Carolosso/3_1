import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class StudentParseError extends Exception {}

public class Service1 {
  
  public void addStudent(Student student) throws IOException {
    var f = new FileWriter("db.txt", true);
    var b = new BufferedWriter(f);
    b.append(student.ToString());
    b.newLine();
    b.close();
  }

  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>();
    var f = new FileReader("db.txt");
    var reader = new BufferedReader(f);
    String line = "";
    while (true) {
      line = reader.readLine();
      if(line == null)
        break;
      try{
         ret.add(Student.Parse(line));
      }catch(StudentParseError e){ 
        System.out.println("Błędne dane studenta!");
      }
    }
    reader.close();
    return ret;
  }

  public Student findStudentByName(String imie) throws IOException { 
    var studenci = this.getStudents();
    for(Student aktualny: studenci){
      if(aktualny.GetName().equals(imie)) return aktualny;
    }
    return null;
  }
}