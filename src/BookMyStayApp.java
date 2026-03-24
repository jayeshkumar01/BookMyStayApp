import java.util.*;

public class BookMyStayApp {

    // Data Structures
    static HashMap<String,String> reservations = new HashMap<>();
    static HashMap<String,Boolean> paymentStatus = new HashMap<>();
    static HashMap<String,Boolean> checkinStatus = new HashMap<>();
    static ArrayList<String> bookingHistory = new ArrayList<>();
    static Stack<String> cancelledRooms = new Stack<>();

    // UC1: View Rooms
    static void viewRooms(){
        System.out.println("\nAvailable Rooms:");
        System.out.println("1. Single Room - ₹2000");
        System.out.println("2. Double Room - ₹3500");
        System.out.println("3. Suite Room - ₹5000");
    }

    // UC2: Book Room
    static void bookRoom(String name){

        if(reservations.containsKey(name)){
            System.out.println("Already booked!");
            return;
        }

        reservations.put(name,"Room");
        bookingHistory.add(name);

        System.out.println("Room booked for " + name);
    }

    // UC3: Add Services
    static void addService(String name){

        if(!reservations.containsKey(name)){
            System.out.println("No reservation found");
            return;
        }

        System.out.println("Service added for " + name);
    }

    // UC4: Booking History
    static void viewHistory(){

        System.out.println("\nBooking History:");
        for(String g : bookingHistory){
            System.out.println(g);
        }
    }

    // UC5: Validation
    static void validateBooking(String name){

        if(name == null || name.isEmpty()){
            System.out.println("Invalid name");
            return;
        }

        System.out.println("Valid booking input");
    }

    // UC6: Cancel Booking
    static void cancelBooking(String name){

        if(!reservations.containsKey(name)){
            System.out.println("No reservation found");
            return;
        }

        reservations.remove(name);
        cancelledRooms.push(name);

        System.out.println("Booking cancelled for " + name);
    }

    // UC7: Payment
    static void processPayment(String name){

        if(!reservations.containsKey(name)){
            System.out.println("Reservation not found");
            return;
        }

        if(paymentStatus.containsKey(name)){
            System.out.println("Payment already done");
            return;
        }

        paymentStatus.put(name,true);
        System.out.println("Payment successful");
    }

    // UC8: Check-in
    static void checkInGuest(String name){

        if(!paymentStatus.containsKey(name)){
            System.out.println("Payment required");
            return;
        }

        checkinStatus.put(name,true);
        System.out.println("Check-in successful");
    }

    // UC9: Report
    static void generateReport(){

        System.out.println("\n--- Booking Report ---");

        System.out.println("Total Reservations: " + reservations.size());
        System.out.println("Total Payments: " + paymentStatus.size());
        System.out.println("Total Check-ins: " + checkinStatus.size());

        System.out.println("\nBooking History:");
        for(String g : bookingHistory){
            System.out.println(g);
        }
    }

    // UC10: System Summary
    static void systemSummary(){

        System.out.println("\n--- System Summary ---");

        System.out.println("Reservations: " + reservations.size());
        System.out.println("Payments: " + paymentStatus.size());
        System.out.println("Check-ins: " + checkinStatus.size());
        System.out.println("Cancelled: " + cancelledRooms.size());
    }

    // MAIN
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int choice;

        do{

            System.out.println("\n--- BookMyStay Menu ---");
            System.out.println("1 View Rooms");
            System.out.println("2 Book Room");
            System.out.println("3 Add Services");
            System.out.println("4 View Booking History");
            System.out.println("5 Validate Booking");
            System.out.println("6 Cancel Booking");
            System.out.println("7 Make Payment");
            System.out.println("8 Guest Check-in");
            System.out.println("9 Generate Report");
            System.out.println("10 System Summary");
            System.out.println("11 Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    viewRooms();
                    break;

                case 2:
                    System.out.println("Enter Name:");
                    bookRoom(sc.nextLine());
                    break;

                case 3:
                    System.out.println("Enter Name:");
                    addService(sc.nextLine());
                    break;

                case 4:
                    viewHistory();
                    break;

                case 5:
                    System.out.println("Enter Name:");
                    validateBooking(sc.nextLine());
                    break;

                case 6:
                    System.out.println("Enter Name:");
                    cancelBooking(sc.nextLine());
                    break;

                case 7:
                    System.out.println("Enter Name:");
                    processPayment(sc.nextLine());
                    break;

                case 8:
                    System.out.println("Enter Name:");
                    checkInGuest(sc.nextLine());
                    break;

                case 9:
                    generateReport();
                    break;

                case 10:
                    systemSummary();
                    break;

                case 11:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        }while(choice != 11);

        sc.close();
    }
}

