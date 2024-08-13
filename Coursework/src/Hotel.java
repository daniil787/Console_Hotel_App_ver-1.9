import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Hotel implements  Serializable{
     private List<Administrator> administrators;
         private List<Resident> residents;
        private List<Room> rooms;

        public Hotel() {
            this.administrators = Administrator.loadFromFile();
            this.residents = Resident.loadFromFile();
            this.rooms = Room.loadFromFile();
        }
    public List<Administrator> getAdministrators(){
        return administrators;
    }
    public List<Resident> getResidents(){
        return residents;
    }
    public List<Room> getRooms(){return rooms;}
    public List<Room> getFreeRooms() {
        List<Room> freeRooms = new ArrayList<>();

        if (!rooms.isEmpty()) {
            for (Room room : rooms) {
                if (isRoomFree(room)) {
                    freeRooms.add(room);
                }
            }
        }
        return freeRooms;
    }
    public boolean isRoomFree(Room room) {
        for (Resident resident : residents) {
            if (resident.getOccupiedRoomID() == room.getID() && (room.getResidents().size() == room.getCapacity())) {
                return false;
            }
        }
        return true;
    }
    public void displayAdministrators()
    {
        for (Administrator admin : administrators)
        {
            System.out.println("\n\t\t\t| Administrator: " + admin.getFullName()
                    + " | Phone number: " + admin.getPhoneNumber()
                    + " | Address: " + admin.getAddress()
                    + " | Shift: " + admin.getShift() + " |");
        }
    }
    public void displayResidents()
    {
        for (Resident resident : residents)
        {
            System.out.println("\n\t\t\t| Resident: " + resident.getFullName()
                    + " | Phone number: " + resident.getPhoneNumber()
                    + " | Address: " + resident.getAddress()
                    + " | Occupied room ID: " + resident.getOccupiedRoomID()
                    + " | Occupied room number: " + resident.getRoomNumber()
                    + " | Residence term: " + resident.getResidenceTerm() + " |");
        }
    }
    public void displayRooms()
    {
        for (Room room : rooms)
        {
            System.out.println("\n\t\t\t| ID: " + room.getID()
                    + " | №: " +  room.getNumber()
                    + " | Type: " + room.getTypeRoom()
                    + " | Capacity: " +  room.getCapacity()
                    + " | Cost: " + room.getCost() + " |");
        }
    }
    public Administrator findAdministratorByNumber(String phoneNumber)
    {
        for(Administrator administrator : administrators)
        {
            if(administrator.getPhoneNumber().equals(phoneNumber)) {
                return administrator;
            }
        }
        return null;
    }
    public Resident findResidentByNumber(String phoneNumber)
    {
        for(Resident resident : residents)
        {
            if(resident.getPhoneNumber().equals(phoneNumber)) {
                return resident;
            }
        }
        return null;
    }
    public Room findRoomByID(int ID)
    {
        for(Room room : rooms)
        {
            if(room.getID() == ID) {
                return room;
            }
        }
        return null;
    }
    public int countAdministrators() {
        return administrators.size();
    }
    public int countResidents() {
        return residents.size();
    }
    public int countRooms() {return rooms.size();}
}
