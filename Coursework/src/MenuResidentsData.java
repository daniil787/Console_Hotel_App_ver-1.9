import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuResidentsData{
    private final Scanner scanner = new Scanner(System.in);
    private final Hotel hotel;
    private static final int ADD_RESIDENT = 1;
    private static final int CHANGE_RESIDENT = 2;
    private static final int DELETE_RESIDENT = 3;
    private static final int COUNT_RESIDENTS = 4;
    private static final int DISPLAY_RESIDENTS = 5;
    private static final int LIST_RES_BY_TYPE = 6;
    private static final int LARGEST_TERM = 7;
    private static final int PREVIOUS_MENU = 8;
    private static final int EXIT_PROGRAM = 9;
    private static final String residentsMenuName = "\t Residents menu";
    public MenuResidentsData(Hotel hotel) {
        this.hotel = hotel;
    }
    public void mainMenu() {
        boolean exit = false;
        while (!exit) {

            try {
                displayMainMenu();

                System.out.print("\tChoice an option -> ");
                int choice2 = scanner.nextInt();
                scanner.nextLine();

                switch (choice2) {

                    case ADD_RESIDENT:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        residentAdd();
                        break;

                    case CHANGE_RESIDENT:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        changeOption();
                        break;

                    case DELETE_RESIDENT:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        residentDelete();
                        break;

                    case COUNT_RESIDENTS:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        residentCount();
                        break;

                    case DISPLAY_RESIDENTS:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        residentsDisplay();
                        break;
                    case LIST_RES_BY_TYPE:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        listResByType();
                        break;

                    case LARGEST_TERM:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        largestTerm();
                        break;

                    case PREVIOUS_MENU:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        exit = true;
                        break;

                    case EXIT_PROGRAM:
                        System.out.println("\n\tExiting the program...");

                        Administrator.saveToFile(hotel.getAdministrators());
                        Resident.saveToFile(hotel.getResidents());
                        Room.saveToFile(hotel.getRooms());

                        System.exit(0);

                    default:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        System.out.println("\n\tEnter a valid value");
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                scanner.nextLine();
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\tIncorrect input");
            }
        }
    }
    private static void displayMainMenu() {
        Core.greeting(residentsMenuName);
        System.out.println("\n\t1 -> Add");
        System.out.println("\t2 -> Change");
        System.out.println("\t3 -> Delete");
        System.out.println("\t4 -> Number of residents");
        System.out.println("\t5 -> Display residents");
        System.out.println("\t6 -> List of residents of rooms of the specified type");
        System.out.println("\t7 -> The largest term of residence");
        System.out.println("\t8 -> Previous menu");
        System.out.println("\t9 -> Exit\n");
    }
    private void residentAdd() {
        if(!checkRoomAvailability()) {return;}
        if(!hotel.getFreeRooms().isEmpty()) {

            // Display rooms
            System.out.println("\tAvailable rooms:");
            for (Room room : hotel.getRooms()) {
                if (room.getResidents().size() < room.getCapacity()) {
                    System.out.println("\n\t| Room ID: " + room.getID()
                            + " | Room Number: " + room.getNumber()
                            + " | Type room: " + room.getTypeRoom()
                            + " | Capacity: " + room.getCapacity()
                            + " | Cost: " + room.getCost()
                            + " | Occupancy: " + room.getResidents().size() + " |");
                }
            }

            // Input field for selecting a room to add a resident

                System.out.print("\n\n\tEnter ID of the room to add a resident -> ");
                int selectedRoomID = scanner.nextInt();
                scanner.nextLine();

                Room selectedRoom = hotel.findRoomByID(selectedRoomID);
                if(selectedRoom == null){
                    for (int i = 0; i <= 49; i++){System.out.println();}
                    System.out.println("\tRoom with ID " + selectedRoomID + " not founded"); return;
                }
                int occupanceRoom = selectedRoom.getResidents().size();

                if  (selectedRoom != null && (occupanceRoom != selectedRoom.getCapacity())) {
                    // Check room capacity
                    int roomCapacity = selectedRoom.getCapacity();

                    if (roomCapacity == 1) {
                        // Add a resident directly
                        addSingleResident(selectedRoom);
                    } else {
                        // Input field for the number of residents to add
                        System.out.print("\n\n\tEnter the number of residents to add to this room -> ");
                        int residentsCount = scanner.nextInt();
                        scanner.nextLine();
                        // Check if the number of residents is within the room capacity
                        if (residentsCount <= roomCapacity) {
                            if (residentsCount > (roomCapacity - selectedRoom.getResidents().size())) {
                                for (int i = 0; i <= 49; i++) {
                                    System.out.println();
                                }
                                System.out.println("\tCannot add more residents than free place in the room.");
                                return;
                            }
                            // Add residents
                            for (int i = 1; i <= residentsCount; i++) {
                                System.out.println("\n\n\tFill out the resident's information:");
                                addSingleResident(selectedRoom);
                            }
                        } else {
                            for (int i = 0; i <= 49; i++) {
                                System.out.println();
                            }
                            System.out.println("\n\n\tCannot add more residents than the room capacity.");
                        }
                    }
                } else {
                    for (int i = 0; i <= 49; i++) {
                        System.out.println();
                    }
                    System.out.println("\n\n\tFree room with ID " + selectedRoomID + " not found");
                }
        }
        else {System.out.println("\n\tThere are no free rooms");}
    }
    private void addSingleResident(Room selectedRoom) {
        System.out.print("\n\tEnter full name -> ");
        String fullName = scanner.nextLine();

        System.out.print("\n\tEnter phone number -> ");
        String phoneNumber = scanner.nextLine();

        System.out.print("\n\tEnter address -> ");
        String address = scanner.nextLine();

        System.out.print("\n\tEnter residence term in format: 00.00.0000 - 00.00.0000 -> ");
        int countedTerm = countResidenceTerm();

        Resident newResident = new Resident(fullName, phoneNumber, address, selectedRoom.getID(), countedTerm, hotel);

        newResident.setOccupiedRoom(selectedRoom);
        selectedRoom.getResidents().add(newResident);
        newResident.addEntity(newResident);
        for (int i = 0; i <= 49; i++){System.out.println();}
        System.out.println("\n\tResident added  to room with ID: " + selectedRoom.getID());
    }
    private static void displayChangeMenu(){
        for (int i = 0; i <= 49; i++){System.out.println();}
        System.out.print("\n\n\tMenu for changing data --> ");
        System.out.println("\n\t1 -> Full name");
        System.out.println("\t2 -> Phone number");
        System.out.println("\t3 -> Address");
        System.out.println("\t4 -> Occupied term");
        System.out.println("\t5 -> Previous menu\n");
    }
    private void changeOption(){
        if(!checkRoomAvailability()){return;}
        if(!hotel.getResidents().isEmpty()) {
            for (Resident resident : hotel.getResidents()) {
                // Display residents to select
                System.out.println("\n\t| Resident: " + resident.getFullName()
                        + " | Phone number: " + resident.getPhoneNumber()
                        + " | Address: " + resident.getAddress()
                        + " | ID of occupied room:" + resident.getOccupiedRoomID()
                        + " | Room №:" + resident.getRoomNumber() + " |");
            }
            // Selecting a resident to change his data
            try {
                System.out.print("\n\n\tEnter phone number of resident to change his data -> ");
                String numberToChangeData = scanner.nextLine();
                Resident resident = hotel.findResidentByNumber(numberToChangeData);

                displayChangeMenu();
                resident.changeEntity();
            }
            catch (NullPointerException e){notFounded();}
            }
        else {System.out.println("\tThere are not residence");};
    }
    private static void notFounded(){
        for (int i = 0; i <= 49; i++) {
            System.out.println();
        }
        System.out.println("\tResident not found");
    }
    private void residentDelete(){
        if(!checkRoomAvailability()) {return;}
        if(!hotel.getResidents().isEmpty()) {
            // Display residents to select
                for (Resident resident : hotel.getResidents()) {
                    System.out.println("\n\t| Resident: " + resident.getFullName()
                            + " | Phone number: " + resident.getPhoneNumber()
                            + " | Address: " + resident.getAddress()
                            + " | ID of occupied room: " + resident.getOccupiedRoomID()
                            + " | Room №: " + resident.getRoomNumber() + " |");
                }
            // Selecting a resident to delete
            System.out.print("\n\n\tEnter phone number of resident to delete -> ");
            try {
                String residentToDelete = scanner.nextLine();
                Resident deleteResident = hotel.findResidentByNumber(residentToDelete);
                deleteResident.getOccupiedRoom().getResidents().remove(deleteResident);
                deleteResident.deleteEntity(deleteResident);
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.print("\n\n\tResident deleted ");
            }
            catch (NullPointerException e){
                notFounded();
            }
        }
        else{System.out.println("\n\tThere are no residents");}
    }

    private void residentCount()
    {
        if(!checkRoomAvailability()){return;}
        int countResidents = hotel.countResidents();
        System.out.println("\n\tCount of residents --> " + countResidents);
    }
    private void residentsDisplay()
    {
        if(!checkRoomAvailability()){return;}

        else if(hotel.getResidents().isEmpty()) {
            System.out.println("\n\n\tThere are no residents");
            return;
        }
            System.out.println("\n\n\t\tAll residents:");
            hotel.displayResidents();
    }
    void listResByType() {
        if(!checkRoomAvailability()){return;}
        if(!hotel.getResidents().isEmpty()) {
            // Selecting type of room
            System.out.print("\n\n\tEnter type of room: ");
            String roomType = scanner.nextLine();

            boolean foundRooms = false;
            // Display residents by occupied room type
        if(!foundRooms) {
                System.out.println("\n\tResidents occupied " + roomType + ":");
                for (Resident resident : hotel.getResidents()) {
                    if (resident.getOccupiedTypeRoom().equalsIgnoreCase(roomType)) {
                        foundRooms = true;
                        System.out.print("\n\t\t| Full name: " + resident.getFullName()
                                + " | Phone number: " + resident.getPhoneNumber()
                                + " | Address: " + resident.getAddress()
                                + " | ID of occupied room: " + resident.getOccupiedRoomID() + " |\n");
                    }
                }
            }
            else {
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\n\tNo rooms of type " + roomType + " found");
            }
        }
        else{System.out.println("\n\tThere are no residents");}
    }
    private boolean checkRoomAvailability() {
        if (hotel.getRooms().isEmpty()) {
            System.out.println("\n\tThere are no rooms. Add rooms firstly");
            return false;
        }
        return true;
    }
    // \/ Begin of count of residence term
    private int countResidenceTerm() {
        while (true){
            String residenceTerm = scanner.nextLine();
            String[] twoDates = residenceTerm.trim().split("-");

            try {
                String startDateStr = twoDates[0].trim();
                String endDateStr = twoDates[1].trim();

                String[] startComponents = startDateStr.trim().split("\\.");
                String[] endComponents = endDateStr.trim().split("\\.");

                int startDay = Integer.parseInt(startComponents[0]);
                int endDay = Integer.parseInt(endComponents[0]);

                int startMonth = Integer.parseInt(startComponents[1]);
                int endMonth = Integer.parseInt(endComponents[1]);

                int startYear = Integer.parseInt(startComponents[2]);
                int endYear = Integer.parseInt(endComponents[2]);

                int residence_Term = 0;

                if (startYear == endYear) {
                    // Both dates are in the same year
                    if (startMonth == endMonth) {
                        // Both dates are in the same month
                        residence_Term = endDay - startDay;
                    } else {
                        // Different months
                        residence_Term += getDaysInMonth(startMonth, startYear) - startDay; // Days until the end of the first month

                        for (int month = startMonth + 1; month < endMonth; month++) {
                            residence_Term += getDaysInMonth(month, startYear); // Days in the middle of the month
                        }

                        residence_Term += endDay; // Days in the last month
                    }
                } else {
                    // Different years
                    residence_Term += getDaysInMonth(startMonth, startYear) - startDay; // Days until the end of the first month

                    for (int month = startMonth + 1; month <= 12; month++) {
                        residence_Term += getDaysInMonth(month, startYear); // Days in the year of the starting month
                    }
                    for (int year = startYear + 1; year < endYear; year++) {
                        if (isLeapYear(year)) {
                            residence_Term += 366; // Leap year
                        } else {
                            residence_Term += 365;
                        }
                    }
                    for (int month = 1; month < endMonth; month++) {
                        residence_Term += getDaysInMonth(month, endYear); // Days in the year of the ending month
                    }
                    residence_Term += endDay; // Days in the last month
                }
                return residence_Term;
            } catch (ArrayIndexOutOfBoundsException e) {
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\n\tIncorrect. Should be in \"00.00.0000 - 00.00.0000\" format");
                System.out.print("\n\tTry again: ");
            }
        }
    }
    static int getDaysInMonth(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28; // February
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }
    static boolean isLeapYear(int year) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true; // Leap year
        } else {
            return false; // Not leap year
        }
    }
    // /\ End of count of residence term
    private void  largestTerm() {
        if(!checkRoomAvailability()){return;}
            int compareUnit = 0;
            for (Resident resident : hotel.getResidents()) {
                if (resident.getResidenceTerm() > compareUnit) {
                    compareUnit = resident.getResidenceTerm();
                }
            }
            System.out.println("\n\n\tLargest residence term -> " + compareUnit + " days");
    }
}
