
import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));
    }

    private static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.category);
            }
        }
    }

    private static void bookRoom(Scanner scanner) {
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && !room.isBooked) {
                room.isBooked = true;
                System.out.println("Room " + roomNumber + " has been successfully booked!");
                return;
            }
        }
        System.out.println("Room not available or does not exist.");
    }
}

//Output
/* 
Hotel Reservation System
1. View Available Rooms
2. Book a Room
3. Exit
Choose an option: 1

Available Rooms:
Room 101 - Single
Room 102 - Double
Room 103 - Suite

Hotel Reservation System
1. View Available Rooms
2. Book a Room
3. Exit
Choose an option: 2
Enter room number to book: 102
Room 102 has been successfully booked!

Hotel Reservation System
1. View Available Rooms
2. Book a Room
3. Exit
Choose an option: 3
Thank you for using the Hotel Reservation System. */
