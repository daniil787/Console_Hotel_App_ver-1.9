import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MenuRoomsData{
    Scanner scanner = new Scanner(System.in);
    private final Hotel hotel;
    private static final int ADD_ROOM = 1;
    private static final int  CHANGE_ROOM = 2;
    private static final int DEL_ROOM = 3;
    private static final int COUNT_ROOMS = 4;
    private static final int DISPLAY_ROOMS = 5;
    private static final int LIST_FREE_ROOMS = 6;
    private static final int LIST_BUSY_ROOMS= 7;
    private static final int FREE_TYPES = 8;
    private static final int BUSY_TYPES = 9;
    private static final int GREAT_TYPE_DEMAND = 10;
    private static final int PREVIOUS_MENU = 11;
    private static final int EXIT = 12;
    private static final String roomsMenuName = "\t  Rooms menu";
    public MenuRoomsData(Hotel hotel)
    {
        this.hotel = hotel;
    }
    public void mainMenu()
    {
        boolean exit = false;

        while(!exit) {

            try{
                displayMainMenu();

                System.out.print("\tChoice an option -> ");
                int choice3 = scanner.nextInt();
                scanner.nextLine();

                switch (choice3) {

                    case ADD_ROOM:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        roomAdd();
                        break;

                    case CHANGE_ROOM:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        changeRoom();
                        break;

                    case DEL_ROOM:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        roomDelete();
                        break;

                    case COUNT_ROOMS:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        roomsCount();
                        break;

                    case DISPLAY_ROOMS:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        roomsDisplay();
                        break;

                    case LIST_FREE_ROOMS:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        roomsFree();
                        break;

                    case LIST_BUSY_ROOMS:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        roomsBusy();
                        break;

                    case FREE_TYPES:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        freeTypes();
                        break;

                    case BUSY_TYPES:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        busyTypes();
                        break;

                    case GREAT_TYPE_DEMAND:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        greatTypeRoomDemand();
                        break;

                    case PREVIOUS_MENU:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        exit = true;
                        break;

                    case EXIT:
                        System.out.println("\n\n\tExiting the program...");

                        Administrator.saveToFile(hotel.getAdministrators());
                        Resident.saveToFile(hotel.getResidents());
                        Room.saveToFile(hotel.getRooms());

                        System.exit(0);
                    default:
                        for (int i = 0; i <= 49; i++){System.out.println();}
                        System.out.println("\n\n\tIncorrect value");
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
    private static void displayMainMenu(){
        Core.greeting(roomsMenuName);
        System.out.println("\t1 -> Add");
        System.out.println("\t2 -> Change");
        System.out.println("\t3 -> Delete");
        System.out.println("\t4 -> Count of rooms");
        System.out.println("\t5 -> Display rooms");
        System.out.println("\t6 -> Free rooms");
        System.out.println("\t7 -> Busy rooms");
        System.out.println("\t8 -> The number of free room by types");
        System.out.println("\t9 -> The number of busy room by types");
        System.out.println("\t10 -> The type of room that has the greatest demand");
        System.out.println("\t11 -> Previous menu");
        System.out.println("\t12 -> Exit\n");
    }
    private void displayChangeMenu()
    {
        System.out.println("\n\n\tMenu for changing data --> ");
        System.out.println("\t1 -> ID");
        System.out.println("\t2 -> Number");
        System.out.println("\t3 -> Room type");
        System.out.println("\t4 -> Capacity");
        System.out.println("\t5 -> Cost");
        System.out.println("\t6 -> Previous menu\n");
    }
    public void roomAdd()
    {   System.out.print("\n\tEnter ID of room --> ");

        int roomID = scanner.nextInt();
        scanner.nextLine();

        for(Room room : hotel.getRooms())
        {
            if(roomID == room.getID())
            {
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\tA room with this ID already exists");
                return;
            }
        }
        System.out.print("\n\tEnter number of room --> ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        for(Room room : hotel.getRooms())
        {
            if(roomNumber == room.getNumber())
            {
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\tA room with this number already exists");
                return;
            }
        }
        System.out.print("\n\tEnter type of room --> ");
        String roomType = scanner.nextLine();
        roomType = roomType.toLowerCase();

        System.out.print("\n\tEnter capacity of room --> ");
        int roomCapacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\n\tEnter cost of room --> ");
        float roomCost = scanner.nextFloat();
        scanner.nextLine();

        Room newRoom = new Room(roomID, roomNumber, roomCapacity, roomType, roomCost, hotel);
        newRoom.addEntity(newRoom);
        for (int i = 0; i <= 49; i++){System.out.println();}
        System.out.println("\n\n\tRoom added");
    }
    public void changeRoom() {
        if (!hotel.getRooms().isEmpty()) {
            // Display rooms to select
            for (Room room : hotel.getRooms()) {
                System.out.println("\n\t| Room ID: " + room.getID()
                        + " | Room Number: " + room.getNumber()
                        + " | Capacity: " + room.getCapacity() + " |");
            }
            // Selecting a room to change his data

            System.out.print("\n\n\tEnter an ID of the room to change its data -> ");
            try{
            int choiceRoom = scanner.nextInt();
            scanner.nextLine();

            Room room = hotel.findRoomByID(choiceRoom);


            // Check if the room is free
            if (room.getResidents().isEmpty()) {
                displayChangeMenu();
                room.changeEntity();
            } else {
                for (int i = 0; i <= 49; i++) {
                    System.out.println();
                }
                System.out.println("\n\n\tSelected room is not free. Cannot change data.");
            }
        }
            catch(NullPointerException e){
                notFounded();
            }
        } else {
            System.out.println("\n\n\tThere are no rooms");
        }
    }
 private static void notFounded(){
     for (int i = 0; i <= 49; i++) {
         System.out.println();
     }
     System.out.println("\tRoom not found");
 }
    private void roomDelete()
    {   if(!hotel.getRooms().isEmpty()) {
        for (Room room : hotel.getRooms()) {
            // Display rooms to select
            System.out.println("\n\t| Room ID: " + room.getID()
                    + " | Room Number: " + room.getNumber()
                    + " | Capacity: " + room.getCapacity() + " |");
        }
        // Selection a room to delete

        System.out.print("\n\n\tEnter ID of room to delete --> ");
        try {
        int delRoom = scanner.nextInt();
        scanner.nextLine();
        Room choiceRoom = hotel.findRoomByID(delRoom);

        if (!choiceRoom.getResidents().isEmpty()) {
            for (int i = 0; i <= 49; i++) {
                System.out.println();
            }
            System.out.println("\n\n\tSelected room is not free. Cannot delete room");
        } else {
            choiceRoom.deleteEntity(choiceRoom);
            for (int i = 0; i <= 49; i++) {
                System.out.println();
            }
            System.out.println("\n\n\tRoom with ID " + delRoom + " has been deleted");
        }

        }
            catch (NullPointerException e) {
                notFounded();
            }
    }
        else {
            System.out.println("\n\n\tThe are no rooms");
        }
    }
    private void roomsCount()
    {
        if (!hotel.getRooms().isEmpty()) {
            int countRooms = hotel.countRooms();
            System.out.println("\n\n\tCount of rooms --> " + countRooms);
        }
        else{
            System.out.println("\n\n\tThere are no rooms");
        }
    }
    private void roomsDisplay()
    {
        if (!hotel.getRooms().isEmpty())
        {
            System.out.println("\n\n\tAll rooms:");
            hotel.displayRooms();
        }
        else{
            System.out.println("\n\n\tThere are no rooms");
        }
    }
    private void roomsBusy() {
        if(!hotel.getRooms().isEmpty()) {
            if(!hotel.getResidents().isEmpty()) {
                // Display busy rooms and residents
                System.out.println("\n\n\tBusy rooms:");
                for (Room room : hotel.getRooms()) {
                    if(!room.getResidents().isEmpty()){
                        System.out.println("\n\n\t| Room ID -> | " + room.getID() + " | Number room -> | " + room.getNumber() + " |");
                        for (Resident resident : room.getResidents()) {
                            System.out.println("\n\t\t| Resident -> | " + resident.getFullName() +
                                                          " | Phone number -> | " + resident.getPhoneNumber());
                        }
                    }
                }
            }
            else {
                System.out.println("\n\n\tThe are no busy rooms");
            }
        }
        else {
            System.out.println("\n\n\tThe are no rooms");
        }
    }
    private void roomsFree() {
        if(!hotel.getRooms().isEmpty()) {
            List<Room> freeRooms = hotel.getFreeRooms();

            if (!freeRooms.isEmpty()) {
                // Display free rooms
                System.out.println("\tFree Rooms:");
                for (Room room : freeRooms) {
                    if(room.getResidents().isEmpty()) {
                        displayRoomInfo(room);
                    }
                }
            }
            else {
                System.out.println("\n\n\tThere are no free rooms");
            }
        }
        else {System.out.println("\n\tThere are no rooms");}
    }
    private void displayRoomInfo(Room room) {
        System.out.println("\n\t| Room ID: " + room.getID()
                + " | Room Number: " + room.getNumber()
                + " | Capacity: " + room.getCapacity()
                + " | Type room: " + room.getTypeRoom()
                + " | Cost: " + room.getCost() + " |");
    }
    private void busyTypes() {
        if(!hotel.getRooms().isEmpty()) {
            if(!hotel.getResidents().isEmpty()) {
                // Count of occupied rooms by type
                Map<String, Integer> busyRoomTypeCount = new HashMap<>();
                for (Resident resident : hotel.getResidents()) {
                    String roomType = resident.getOccupiedTypeRoom();
                    busyRoomTypeCount.put(roomType, busyRoomTypeCount.getOrDefault(roomType, 0) + 1);
                }
                //Display count of occupied rooms by type
                System.out.println("\n\n\tBusy room types:");
                for (Map.Entry<String, Integer> entry : busyRoomTypeCount.entrySet()) {
                    String roomType = entry.getKey();
                    int count = entry.getValue();
                    System.out.println("\n\t" + roomType.toUpperCase() + ": " + count + (count == 1? " room": " rooms"));
                }
            }
            else {
                System.out.println("\n\n\tThere are no busy rooms");
            }
        }
        else {
            System.out.println("\n\n\tThere are no rooms");
        }
    }
    private void freeTypes() {
        if(!hotel.getRooms().isEmpty())
        {
        Map<String, Integer> freeRoomTypeCount = getFreeRoomTypesCount();
        if (!freeRoomTypeCount.isEmpty()) {
            // Display count of busy rooms
            System.out.println("\n\n\tFree Room Types:");

            for (Map.Entry<String, Integer> entry : freeRoomTypeCount.entrySet()) {
                String roomType = entry.getKey();
                int count = entry.getValue();
                System.out.println("\n\t" + roomType.toUpperCase() + ": " + count + (count == 1? " room": " rooms"));
            }
        } else {
            System.out.println("\n\n\tThere are no free rooms");
        }
    }
        else{System.out.println("\n\n\tThere are no rooms");}
    }
    private Map<String, Integer> getFreeRoomTypesCount() {

        Map<String, Integer> freeRoomTypeCount = new HashMap<>();
        // Count free rooms by type
        if (!hotel.getRooms().isEmpty()) {
            for (Room room : hotel.getRooms()) {
                if (hotel.isRoomFree(room)) {
                    String roomType = room.getTypeRoom();
                    freeRoomTypeCount.put(roomType, freeRoomTypeCount.getOrDefault(roomType, 0) + 1);
                }
            }
        } else {
            for (int i = 0; i <= 49; i++){System.out.println();}
            System.out.println("\n\n\tThere are no rooms");
        }

        return freeRoomTypeCount;
    }
    private void greatTypeRoomDemand() {
        if (!hotel.getRooms().isEmpty()) {
            Map<String, Integer> roomTypeCount = new HashMap<>();

            if (!hotel.getResidents().isEmpty()) {
                // Count rooms by type
                for (Resident resident : hotel.getResidents()) {
                    String roomType = resident.getOccupiedTypeRoom();
                    roomTypeCount.put(roomType, roomTypeCount.getOrDefault(roomType, 0) + 1);
                }
                if (!roomTypeCount.isEmpty()) {
                    // Find type with most residents
                    String maxRoomType = null;
                    int maxCount = 0;

                    for (Map.Entry<String, Integer> entry : roomTypeCount.entrySet()) {
                        if (entry.getValue() > maxCount) {
                            maxCount = entry.getValue();
                            maxRoomType = entry.getKey();
                        }
                    }
                    System.out.println("\n\n\tGreat type room demand is " + maxRoomType);
                } else {
                    System.out.println("\n\n\tThere are no occupied rooms");
                }
            } else {
                System.out.println("\n\n\tThere are no residents");
            }
        } else {
            System.out.println("\n\n\tThere are no rooms");
        }
    }
}
