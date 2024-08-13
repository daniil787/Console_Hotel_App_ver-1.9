import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room implements EntityManagement<Room>, Serializable {
    private int ID;
    private int numberRoom;
    private int capacity;
    private String typeRoom;
    private float cost;
    private final Hotel hotel;
    private final List<Resident> settlers = new ArrayList<>();
    static final String FILE_PATH_ROOMS = "C:\\Users\\user\\Documents\\rooms.dat";
    public Room(int ID, int numberRoom, int capacity, String typeRoom, float cost, Hotel hotel)
    {
        this.ID = ID;
        this.numberRoom = numberRoom;
        this.capacity = capacity;
        this.typeRoom = typeRoom;
        this.cost = cost;
        this.hotel = hotel;
    }

    // Getters
    public int getID()
    {
        return ID;
    }
    public String getTypeRoom()
    {
        return typeRoom;
    }
    public float getCost()
    {
        return cost;
    }
    public int getCapacity()
    {
        return capacity;
    }
    public int getNumber()
    {
        return numberRoom;
    }
    public List<Resident> getResidents(){
        return settlers;
    }

    // Setters
    public void setID(int ID){
        this.ID = ID;
    }
    public void setNumberRoom(int numberRoom){
        this.numberRoom = numberRoom;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public void setTypeRoom(String typeRoom){
        this.typeRoom = typeRoom;
    }
    public void setRoomCost(float roomCost){
        this.cost = roomCost;
    }

    // Implemented methods of the interface
    @Override
    public void addEntity(Room room) {
        hotel.getRooms().add(room);
        Room.saveToFile(hotel.getRooms());
    }
    @Override
    public void changeEntity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tChoice an option -> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                // Change ID of room
                System.out.print("\n\tEnter ID -> ");
                int ID = scanner.nextInt();

                setID(ID);
                System.out.println("\n\tRoom ID changed");
                break;

            case 2:
                // Change number of room
                System.out.print("\n\tEnter room number -> ");
                int roomNumber = scanner.nextInt();

                setNumberRoom(roomNumber);
                System.out.println("\n\tNumber of room changed");
                break;

            case 3:
                // Change room type
                System.out.print("\n\tEnter room type -> ");
                String roomType = scanner.nextLine();
                setTypeRoom(roomType.toLowerCase());
                System.out.println("\n\tRoom type changed");
                break;

            case 4:
                // Change capacity of room
                System.out.print("\n\tEnter room capacity -> ");
                if(scanner.hasNextInt()){
                    int roomCapacity = scanner.nextInt();
                    setCapacity(roomCapacity);
                    System.out.println("\n\tCapacity of room changed");
                }
                else {
                    for (int i = 0; i <= 49; i++){System.out.println();}
                    System.out.println("Incorrect input");
                }
                break;

            case 5:
                // Change cost of room
                System.out.print("\n\tEnter cost of room -> ");

                if (scanner.hasNextFloat()){
                    float roomCost = scanner.nextFloat();
                    setRoomCost(roomCost);
                    System.out.println("\n\tCost of room changed");
                }

                else{
                    for (int i = 0; i <= 49; i++){System.out.println();}
                    System.out.println("\tIncorrect input");
                }
                break;

            case 6:
                for(int i = 0; i < 49; i++) {
                    System.out.println();
                }
                break;

            default:
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\tIncorrect input");
        }
        Room.saveToFile(hotel.getRooms());
    }
    @Override
    public void deleteEntity(Room room){
        hotel.getRooms().remove(room);
        Room.saveToFile(hotel.getRooms());
    }

    // Methods for work with files
    public static List<Room> loadFromFile() {
        List<Room> rooms = new ArrayList<>();

        File file = new File(FILE_PATH_ROOMS);
        if(file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH_ROOMS))) {
                Object obj = ois.readObject();
                if (obj instanceof List) {
                    rooms = (List<Room>) obj;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return rooms;
    }
    public static void saveToFile(List<Room> rooms) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH_ROOMS))) {
            oos.writeObject(rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
