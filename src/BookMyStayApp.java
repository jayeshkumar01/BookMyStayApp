import java.util.*;

public class BookMyStayApp {

    static HashMap<String,String> reservations = new HashMap<>();
    static HashMap<String,Boolean> paymentStatus = new HashMap<>();
    static HashMap<String,Boolean> checkinStatus = new HashMap<>();
    static ArrayList<String> bookingHistory = new ArrayList<>();
    static Stack<String> cancelledRooms = new Stack<>();

    static void bookRoom(String name){
        reservations.put(name,"Room");
        bookingHistory.add(name);
        System.out.println("Room booked for " + name);
    }

    static void cancelBooking(String name){
        if(!reservations.containsKey(name)){
            System.out.println("Reservation not found");
            return;
        }
        reservations.remove(name);
        cancelledRooms.push(name);
        System.out.println("Booking cancelled for " + name);
    }

    static void processPayment(String name){
        if(!reservations.containsKey(name)){
            System.out.println("Reservation not found");
            return;
        }
        paymentStatus.put(name,true);
        System.out.println("Payment successful");
    }

    static void checkInGuest(String name){
        if(!paymentStatus.containsKey(name)){
            System.out.println("Payment required");
            return;
        }
        checkinStatus.put(name,true);
        System.out.println("Check-in successful");
    }

    // ✅ UC9
    static void generateReport(){

        System.out.println("\n--- Booking Report ---");

        System.out.println("Total Reservations: " + reservations.size());
        System.out.println("Total Payments: " + paymentStatus.size());
        System.out.println("Total Check-ins: " + checkinStatus.size());

        System.out.println("\nBooking History:");
        for(String guest : bookingHistory){
            System.out.println(guest);
        }
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int choice;

        do{

            System.out.println("\n--- MENU ---");
            System.out.println("1 Book Room");
            System.out.println("6 Cancel Booking");
            System.out.println("7 Payment");
            System.out.println("8 Check-in");
            System.out.println("9 Generate Report");
            System.out.println("10 Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    System.out.println("Enter Name:");
                    bookRoom(sc.nextLine());
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
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid");
            }

        }while(choice!=10);

        sc.close();
    }
}

