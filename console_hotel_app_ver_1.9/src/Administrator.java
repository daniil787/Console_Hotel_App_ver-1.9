import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
public class Administrator extends Person implements EntityManagement<Administrator>, Serializable {
    private String shift;
    private static final String NAME = "Oasis";
    final private Hotel hotel;
    private static final int CHANGE_FULL_NAME = 1;
    private static final int CHANGE_PHONE_NUMBER = 2;
    private static final int CHANGE_ADDRESS = 3;
    private static final int CHANGE_SHIFT = 4;
    private static final int PREVIOUS_MENU = 5;
    private static final String FILE_PATH_ADMINS = "C:\\Users\\user\\Documents\\Hotel\\administrators.dat";
    public Administrator(String fullName, String phoneNumber, String address, String shift, Hotel hotel) {
        super(fullName, phoneNumber, address);
        this.shift = shift;
        this.hotel = hotel;
    }

    //Getters
    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }
    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }

    //Setters
    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }
    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }
    @Override
    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    // Implemented methods of the interface
    @Override
    public void addEntity(Administrator administrator) {
        hotel.getAdministrators().add(administrator);
        Administrator.saveToFile(hotel.getAdministrators());
    }
    @Override
    public void changeEntity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tChoice an option -> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case CHANGE_FULL_NAME:
                System.out.print("\n\tEnter new full name -> ");
                String name = scanner.nextLine();

                setFullName(name);
                System.out.println("\n\tFull name changed");
                break;

            case CHANGE_PHONE_NUMBER:
                System.out.print("\n\tEnter new phone number -> ");
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

            case CHANGE_SHIFT:
                System.out.print("\n\tEnter new shift -> ");
                String shift = scanner.nextLine();

                setShift(shift);
                System.out.println("\n\tShift changed");
                break;

            case PREVIOUS_MENU:
                for(int i = 0; i < 49; i++) {
                    System.out.println();
                }
                break;
            default:
                for (int i = 0; i <= 49; i++){System.out.println();}
                System.out.println("\n\tEntered valid value");
        }
        Administrator.saveToFile(hotel.getAdministrators());
    }
    @Override
    public void deleteEntity(Administrator administrator) {
        hotel.getAdministrators().remove(administrator);
        Administrator.saveToFile(hotel.getAdministrators());
    }

    // Methods for work with files
    public static List<Administrator> loadFromFile() {
        List<Administrator> administrators = new ArrayList<>();

        File file = new File(FILE_PATH_ADMINS);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH_ADMINS))) {
                Object obj = ois.readObject();
                if (obj instanceof List) {
                    administrators = (List<Administrator>) obj;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return administrators;
    }
    public static void saveToFile(List<Administrator> administrators) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH_ADMINS))) {
            oos.writeObject(administrators);
        } catch (IOException e) {
            e.printStackTrace();
        }//HAsh code
    }
}
