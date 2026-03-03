package hotel.reservations.system;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Room{
    int RoomNumber;
    String Category;
    double price;
    boolean isAvailable;
    
    Room(int RoomNumber , String Category , double price)
    {
       this.RoomNumber = RoomNumber;
       this.Category = Category;
       this.price = price;
       this.isAvailable = true;   
    }
    void displayRoom() {
        System.out.println("Room no: "+RoomNumber + 
                " | Category: " + Category + 
                " | Price: $" + price + 
                " | Available: " + (isAvailable ? "Yes" : "No"));
    }
}

   class Booking {
       String customerName;
       int RoomNumber;
       String Category;
       double price;
       
       Booking(String customerName , int RoomNumber, String Category, double price)
       {
          this.customerName = customerName;
          this.RoomNumber = RoomNumber;
          this.Category = Category;
          this.price = price;        
       }
       void saveToFile() {
           try {
               FileWriter fw = new FileWriter("Bookings.txt", true);
               fw.write(customerName + "," + RoomNumber + "," + Category + "," + price + "\n");
               fw.close();
           }
           catch (IOException e) {
               System.out.println("Error saving booking.");
           }
       }
   }
public class HotelReservationsSystem {
    
     static ArrayList<Room> rooms = new ArrayList<>();
     static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
       // Add Rooms....
       rooms.add(new Room(101,"Standard",100));
       rooms.add(new Room(102,"Standard",150));
       rooms.add(new Room(201,"Suite",300));
       rooms.add(new Room(202,"Deluxe",200));
       rooms.add(new Room(301,"VIP",350));
       
       int choice;
       
       do  {
          System.out.println("\n ===== HOTEL RESERVATION SYSTEM =====");
          System.out.println("1. View Available Room.");
          System.out.println("2. Book Room.");
          System.out.println("3. Cancel Booking.");
          System.out.println("4. View Booking details.");
          System.out.println("5. Exit.");
          System.out.println("Enter your choice:  ");
          choice = sc.nextInt();

        switch(choice)  {
            case 1:  
                viewAvailableRooms();
                break;
            case 2:
                bookRooms();
                break;
            case 3:
                cancelBooking();
                break;
            case 4:
                viewBooking();
                break;
            case 5:
                System.out.println("Thank You!");
            default:
                System.out.println("Invalid choice!");
        }
        
       }
       while (choice !=5);
       }
    
    static void viewAvailableRooms()  {
        for (Room r : rooms) {
            if (r.isAvailable) {
                r.displayRoom();
            }
        }
    }
    static void bookRooms()  {
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        
        System.out.println("Enter room number to book: ");
        int roomNo = sc.nextInt();
        
        for(Room r : rooms)  {
            if(r.RoomNumber == roomNo && r.isAvailable) {
                
                System.out.println("Room Price: $" + r.price);
                System.out.println("Proceed to payment? (Yes/No): ");
                sc.nextLine();
                String payment = sc.nextLine();
                
              if(payment.equalsIgnoreCase("Yes")) {
                  r.isAvailable = false;
                  Booking b = new Booking(name, r.RoomNumber, r.Category, r.price);
                  b.saveToFile();
                  System.out.println("Booking Successsful!");               
              }
              else {
                  System.out.println("Payment Cancelled!");
            }
              return;
        }       
    }
        System.out.println("Room not available!");
}
    static void cancelBooking()  {
        System.out.println("Enter room number to cancel: ");
        int roomNo = sc.nextInt();
        for (Room r : rooms) {
            if(r.RoomNumber == roomNo && ! r.isAvailable) {
            r.isAvailable = true;
            
            System.out.println("Booking Cancelled!");
            return;
        }
        }
        System.out.println("No booking found for this room.");
    }
    static void viewBooking() {
        try {
            File file = new File("Booking.txt");
            Scanner fileReader = new Scanner(file);
            
            System.out.println("\n ----- Booking Details -----");
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
                fileReader.close();
            }
           catch (Exception e) {
               System.out.println("No booking found!");
           } 
        }   
    }

