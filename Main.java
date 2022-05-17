import java.util.Scanner;
import java.io.IOException;

class Main {
  
  public static void wyczysc(){
    //wyczyszczenie ekranu
    System.out.print("\033[H\033[2J");
    System.out.flush();
    //-----------------
  }
  
  public static void main(String[] args) {
    try {
      Service1 s = new Service1();
      Scanner scan = new Scanner(System.in);
      int wybor=0;
      while(wybor!=9){
      System.out.println("-----------MENU------------");
      System.out.println("1. Dopisz studenta.");
      System.out.println("2. Pokaz studentow.");
      System.out.println("9. Wyjscie.");
      wybor=scan.nextInt();
      scan.nextLine();
          switch(wybor){
            case 1: {
              wyczysc();
              System.out.println("Wybrano opcje 1. ");
              System.out.println("Podaj imie: ");
              String imie=scan.nextLine();
              System.out.println("Podaj wiek: ");
              int wiek=scan.nextInt(); scan.nextLine();
              s.addStudent(new Student(imie, wiek));
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
          }
      }
        scan.close();
    } catch (IOException e) {

    }
  }
}