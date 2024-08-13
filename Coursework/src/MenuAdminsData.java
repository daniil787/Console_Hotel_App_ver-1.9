import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdminsData{
    private final Scanner scanner = new Scanner(System.in);
    private final Hotel hotel;
    private static final int ADD_OPTION = 1;
    private static final int CHANGE_OPTION = 2;
    private static final int DELETE_OPTION = 3;
    private static final int COUNT_OPTION = 4;
    private static final int DISPLAY_OPTION = 5;
    private static final int PREVIOUS_OPTION = 6;
    private static final int EXIT_OPTION = 7;
    private static final String adminsMenuName = " Administrators menu";
    public MenuAdminsData(Hotel hotel) {
        this.hotel = hotel;
    }
    public void mainMenu()
    {
               boolean exit = false;
        while (!exit) {
            try {
                displayMenu();
                System.out.print("\n\tChoice an option -> ");

                int choice1 = scanner.nextInt();
                scanner.nextLine();

                switch (choice1) {

                    case ADD_OPTION:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        adminAdd();
                        break;
                    case CHANGE_OPTION:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        changeDataOption();
                        break;
                    case DELETE_OPTION:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        adminDelete();
                        break;
                    case COUNT_OPTION:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        adminsCount();
                        break;
                    case DISPLAY_OPTION:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        adminsDisplay();
                        break;
                    case PREVIOUS_OPTION:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        exit = true;
                        break;
                    case EXIT_OPTION:
                        System.out.println("\n\n\tExiting the program...");

                        Administrator.saveToFile(hotel.getAdministrators());
                        Resident.saveToFile(hotel.getResidents());
                        Room.saveToFile(hotel.getRooms());

                        System.exit(0);
                    default:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        System.out.println("\n\n\tEnter a valid value");
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                for (int i = 0; i <= 49; i++){System.out.println();}
                scanner.nextLine();
                System.out.println("\n\n\tIncorrect input");
            }
        }
    }
    public static void displayMenu()
    {
        Core.greeting(adminsMenuName);
        System.out.println("\t1 -> Add");
        System.out.println("\t2 -> Change");
        System.out.println("\t3 -> Delete");
        System.out.println("\t4 -> Number of administrators");
        System.out.println("\t5 -> Display administrators");
        System.out.println("\t6 -> Previous menu");
        System.out.println("\t7 -> Exit");
    }
    public void adminAdd()
    {
        System.out.print("\n\n\tEnter full name -> ");
        String fullName = scanner.nextLine();

        System.out.print("\n\tEnter phone number -> ");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("\n\tEnter address -> ");
        String adminAddress = scanner.nextLine();

        System.out.print("\n\tEnter shift -> ");
        String shift = scanner.nextLine();

        Administrator newAdmin = new Administrator(fullName, phoneNumber, adminAddress, shift, hotel);
        newAdmin.addEntity(newAdmin);
        for (int i = 0; i <= 49; i++){System.out.println();}
        System.out.println("\n\tAdministrator added");
    }
    public void changeDataOption()
    {
        if(!hotel.getAdministrators().isEmpty()) {
            // Display administrators to select
            for(Administrator administrator : hotel.getAdministrators()){System.out.println("\n\t"
                    + "| Administrator: " + administrator.getFullName()
                    + " | Phone number: " + administrator.getPhoneNumber()
                    + " | Address: " + administrator.getAddress()
                    + " | Shift:" + administrator.getAddress() + " |");}
            // Selecting an administrator to change his data
            System.out.print("\n\tEnter the phone number of administrator to change his data -> ");
            try {
                String choiceAdministratorByPhone = scanner.nextLine().trim();

                Administrator administrator = hotel.findAdministratorByNumber(choiceAdministratorByPhone);

                while (true) {
                    // Menu change data
                    try {
                        displayChangeMenu();
                        administrator.changeEntity();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\n\n\tIncorrect input");
                        scanner.nextLine();
                    }
                }
            }
            catch (NullPointerException e){
                notFounded();
            }
        }
        else {
            System.out.println("\n\tThe are no administrators");
        }
    }
    public static void displayChangeMenu()
    {
        for (int i = 0; i <= 49; i++){System.out.println();}
        System.out.print("\n\n\t\t\tMenu for changing data:");
        System.out.println("\n\t1 -> Full name");
        System.out.println("\t2 -> Phone number");
        System.out.println("\t3 -> Address");
        System.out.println("\t4 -> Shift");
        System.out.println("\t5 -> Previous menu");
    }
    private static void notFounded(){
        for (int i = 0; i <= 49; i++) {
            System.out.println();
        }
        System.out.println("\tAdministrator not found");
    }
    public void adminDelete()
    {
        if(!hotel.getAdministrators().isEmpty()) {
            // Display administrators to select
            for (Administrator administrator : hotel.getAdministrators()) {
                System.out.println("\n\t"
                        + "| Administrator: " + administrator.getFullName()
                        + " | Phone number: " + administrator.getPhoneNumber()
                        + " | Address: " + administrator.getAddress()
                        + " | Shift: " + administrator.getAddress() + " |");
            }

            // Selecting an administrator to delete
            try {
            System.out.print("\n\n\tEnter phone number of administrator to delete -> ");
            String administratorToDelete = scanner.nextLine().trim();

            Administrator delAdministrator = hotel.findAdministratorByNumber(administratorToDelete);

            delAdministrator.deleteEntity(delAdministrator);
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\n\tAdministrator deleted");
            }
            catch (NullPointerException e){
                notFounded();
            }

        }
        else{
            System.out.println("\n\n\tThe are no administrators");
        }
    }
    public void adminsCount()
    {
        int countAdmins = hotel.countAdministrators();
        System.out.println("\n\n\tCount of administrators -> " + countAdmins);
    }
    public void adminsDisplay()
    {
        if (!hotel.getAdministrators().isEmpty())
        {
            System.out.println("\n\n\tAll administrators -> ");
            hotel.displayAdministrators();
        }
        else{
            System.out.println("\n\n\tThere are no administrators");
        }
    }

}
