import java.util.Scanner;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;

class WrongStudentName extends Exception {}
class WrongAge extends Exception {}
class WrongDateOfBirth extends Exception{}
class WrongMenuOption extends Exception { }

class Main {
  
  public static Scanner scan = new Scanner(System.in);

  public static void wyczysc(){
    //wyczyszczenie ekranu
    System.out.print("\033[H\033[2J");
    System.out.flush();
    //-----------------
  }
  
  public static String ReadName() throws WrongStudentName {
      System.out.println("Podaj imie: ");
      String name = scan.nextLine();
      if(name.contains(" "))
      throw new WrongStudentName();
      return name;
  }
  public static int ReadAge() throws WrongAge{
      System.out.println("Podaj wiek: ");
      int wiek = scan.nextInt();scan.nextLine();
      if(wiek<0 || wiek >100 )throw new WrongAge();
      return wiek;
  }
  public static String ReadDateOfBirth() throws WrongDateOfBirth {
    System.out.println("Podaj datę urodzenia DD-MM-YYY");
    String date = scan.nextLine();
    try {
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    format.setLenient(false);
    format.parse(date);
    return date;
    } catch(ParseException e){
    throw new WrongDateOfBirth();
    }
  }
  public static int ReadMenu() throws WrongMenuOption {
      System.out.println("-----------MENU------------");
      System.out.println("1. Dopisz studenta.");
      System.out.println("2. Pokaz studentow.");
      System.out.println("3. Znajdz studenta.");
      System.out.println("9. Wyjscie.");
    try{
      return scan.nextInt();
    }catch(InputMismatchException e){
      throw new WrongMenuOption();
    }
  }
  
  public static void main(String[] args) {
      Service1 s = new Service1();
      int wybor=0;
      while(wybor!=9){
        try{
      wybor=ReadMenu();
      scan.nextLine();
          switch(wybor){
            case 1: {
              wyczysc();
              System.out.println("Wybrano opcje 1. ");
              String imie = ReadName();
              int wiek= ReadAge(); //scan.nextLine();
              String dataur= ReadDateOfBirth();
              s.addStudent(new Student(imie, wiek, dataur));
              wyczysc();
              break;
            }
            case 2: {
              wyczysc();
              System.out.println("Wybrano opcje 2. ");
              //------------wypisywanie
              var studenci = s.getStudents();
              for(Student aktaulny : studenci) {
                System.out.println(aktaulny.ToString());
              }
              //------------------
              scan.nextLine();//by rozwiazac problem z pomijaniem scannera po nextInt
              wyczysc();
              break;
            } 
            case 3: {
            wyczysc();
            System.out.println("Wybrano opcje 3. ");
            //------------szukanie
            System.out.print("Podaj szukane imie: ");
            String szukane=scan.nextLine();
            if(s.findStudentByName(szukane)==null)System.out.println("Nie znaleziono!");
            else System.out.println(s.findStudentByName(szukane).ToString());
            //------------------
            scan.nextLine();
            wyczysc();
            //-----------------
            break;
          }
          }
          }
        catch (WrongStudentName e) {
        wyczysc();
        System.out.print("Błędne imie!");
        scan.nextLine();
        wyczysc();    
    }  
      catch (WrongDateOfBirth e) {
        wyczysc();
        System.out.print("Błędne data urodzenia!");
        scan.nextLine();
        wyczysc();
    }   
      catch (WrongAge e) {
        wyczysc();
        System.out.print("Błędny wiek!");
        scan.nextLine();
        wyczysc();
    }  
      catch (IOException e) {
        wyczysc();
        System.out.println("ERROR!");
        scan.nextLine();
        wyczysc();
    }
      catch(WrongMenuOption e){
        wyczysc();
        System.out.println("Nalezy podac liczbę!");
        scan.nextLine();
        wyczysc();
    }
      }
        scan.close(); 
  }
}