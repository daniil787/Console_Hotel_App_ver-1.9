import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Serializable;

public class  Core  implements Serializable{
    private final static String mainMenuName = "\t  Main menu";
    public static void main(String[] args)  {

        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        MenuAdminsData menuAdmins = new MenuAdminsData(hotel);
        MenuResidentsData menuResidents = new MenuResidentsData(hotel);
        MenuRoomsData menuRooms = new MenuRoomsData(hotel);

        Administrator.loadFromFile();
        Resident.loadFromFile();
        Room.loadFromFile();

        for (int i = 0; i <= 49; i++){System.out.println();}
        while (true) {
            Core.greeting(mainMenuName);
            Core.displayMainMenu();

            int mainChoice = 0;
            System.out.print("\n\tChoice an option -> ");

            try {
                mainChoice = scanner.nextInt();
                scanner.nextLine();
            }

            catch(InputMismatchException e){
                scanner.nextLine();
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\tIncorrect input");
            }
                switch (mainChoice) {
                    case 1:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        menuAdmins.mainMenu();
                        break;
                    case 2:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        menuResidents.mainMenu();
                        break;
                    case 3:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        menuRooms.mainMenu();
                        break;
                    case 4:

                        System.out.println("\n\tExiting the program...");
                        Administrator.saveToFile(hotel.getAdministrators());
                        Resident.saveToFile(hotel.getResidents());
                        Room.saveToFile(hotel.getRooms());

                        System.exit(0);

                    default:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        System.out.println("\n\tIncorrect option");
                }
        }
    }
    public static void greeting(String menuName)
    {
        System.out.print("\n\n\t");
        for (int i = 0; i < 6; i++) {
            System.out.print("|-|\t");
        }
        System.out.print("\n\t " + menuName + "\n\t");
        for (int i = 0; i < 6; i++) {
            System.out.print("|-|\t");
        }
        System.out.println("\n");
    }
    private static void displayMainMenu()
    {
        System.out.println("\t1 -> Administrators data");
        System.out.println("\t2 -> Residents data");
        System.out.println("\t3 -> Rooms data");
        System.out.println("\t4 -> Exit");
    }
}
