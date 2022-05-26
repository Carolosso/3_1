import java.util.Scanner;
import java.io.IOException;

class WrongStudentName extends Exception { }

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
  
  public static void main(String[] args) {
    try {
      Service1 s = new Service1();
      int wybor=0;
      while(wybor!=9){
      System.out.println("-----------MENU------------");
      System.out.println("1. Dopisz studenta.");
      System.out.println("2. Pokaz studentow.");
      System.out.println("3. Znajdz studenta.");
      System.out.println("9. Wyjscie.");
      wybor=scan.nextInt();
      scan.nextLine();
          switch(wybor){
            case 1: {
              wyczysc();
              System.out.println("Wybrano opcje 1. ");
              String imie = ReadName();
              System.out.println("Podaj wiek: ");
              int wiek=scan.nextInt(); scan.nextLine();
              System.out.println("Podaj date urodzenia: ");
              String dataur=scan.nextLine();
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
        scan.close();
    }  catch (WrongStudentName e) {
        System.out.print("Błędne imie!");
    }  
      catch (IOException e) {
        System.out.println("ERROR!");
    }
  }
}