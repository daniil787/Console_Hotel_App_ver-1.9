import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Resident extends Person implements EntityManagement<Resident>, Serializable{
    private int occupiedRoomID;
    private int residenceTerm;
    final private Hotel hotel;
    private Room occupiedRoom;
    private static final int CHANGE_FULL_NAME = 1;
    private static final int CHANGE_PHONE_NUMBER = 2;
    private static final int CHANGE_ADDRESS = 3;
    private static final int CHANGE_OCCUPIED_TERM = 4;
    private static final int PREVIOUS_MENU = 5;
    private static final String FILE_PATH_RESIDENTS = "C:\\Users\\user\\Documents\\Hotel\\residents.dat";
    public Resident(String fullName, String phoneNumber, String  address, int occupiedRoomID, int residenceTerm, Hotel hotel)
    {
        super(fullName, phoneNumber, address);
        this.occupiedRoomID = occupiedRoomID;
        this.hotel = hotel;
        this.residenceTerm = residenceTerm;
    }

    // Getters
    public int getOccupiedRoomID() {
        return occupiedRoom != null ?  occupiedRoom.getID():  -1;
    }
    @Override
    public String getFullName() {
        return super.getFullName();
    }
    @Override
    public String getAddress() {
        return super.getAddress();
    }
    @Override
    public String getPhoneNumber()
    {
        return super.getPhoneNumber();
    }
    public int getResidenceTerm()
    {
        return residenceTerm;
    }
    public String getOccupiedTypeRoom(){
        return occupiedRoom != null ? occupiedRoom.getTypeRoom() : "Room not assigned";
    }
    public int getRoomNumber() {
        return occupiedRoom != null ? occupiedRoom.getNumber() : -1;
    }
    public Room getOccupiedRoom(){
        return occupiedRoom;
    }

    // Setters
    public void setFullName(String fullName)
    {
        super.setFullName(fullName);
    }
    @Override
    public void setPhoneNumber(String phoneNumber)
    {
        super.setPhoneNumber(phoneNumber);
    }
    @Override
    public void setAddress(String address)
    {
        super.setAddress(address);
    }
    public void setOccupiedRoomID(int occupiedRoomID)
    {
        this.occupiedRoomID =  occupiedRoomID;
    }
    public void setResidenceTerm(int residenceTerm){
        this.residenceTerm = residenceTerm;
    }
    public void setOccupiedRoom(Room room){
        this.occupiedRoom = room;
    }

    //Implemented methods of the interface
    @Override
    public void addEntity(Resident resident){
        hotel.getResidents().add(resident);
        Resident.saveToFile(hotel.getResidents());
        Room.saveToFile(hotel.getRooms());
    }
    @Override
    public void changeEntity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tEnter option -> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case CHANGE_FULL_NAME:

                System.out.print("\n\tEnter full name -> ");
                String fullName = scanner.nextLine();

                setFullName(fullName);
                System.out.print("\n\tFull name changed");
                break;

            case CHANGE_PHONE_NUMBER:

                System.out.print("\n\tEnter phone number -> ");
                String phoneNumber = scanner.nextLine();

                setPhoneNumber(phoneNumber);
                System.out.println("\n\tPhone number changed");
                break;

            case CHANGE_ADDRESS:

                System.out.print("\n\tEnter new address -> ");
                String address = scanner.nextLine();

                setAddress(address);
                System.out.println("\n\tAddress changed");
                break;

            case CHANGE_OCCUPIED_TERM:

                System.out.print("\n\tEnter new occupied term -> ");
                int occupiedTerm = scanner.nextInt();

                setResidenceTerm(occupiedTerm);
                System.out.println("\n\tOccupied term changed");
                break;

            case PREVIOUS_MENU:
                for(int i = 0; i < 49; i++) {
                    System.out.println();
                }
                break;

            default:
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\tChoice existing option number");

        }
        Room.saveToFile(hotel.getRooms());
        Resident.saveToFile(hotel.getResidents());
    }

    @Override
    public void deleteEntity(Resident resident){
        resident.getOccupiedRoom().getResidents().remove(resident);
        hotel.getResidents().remove(resident);
        Resident.saveToFile(hotel.getResidents());
        Room.saveToFile(hotel.getRooms());
    }

    // Methods for work with files
    public static List<Resident> loadFromFile() {

        List<Resident> residents = new ArrayList<>();

        File file = new File(FILE_PATH_RESIDENTS);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH_RESIDENTS))) {
                Object obj = ois.readObject();
                if (obj instanceof List) {
                    residents = (List<Resident>) obj;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return residents;
    }
    public static void saveToFile(List<Resident> residents) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH_RESIDENTS))) {
            oos.writeObject(residents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
