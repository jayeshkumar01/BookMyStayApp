import java.util.*;

public class BookMyStayApp {

    static HashMap<Integer, Boolean> rooms = new HashMap<>();
    static Queue<String> bookingQueue = new LinkedList<>();
    static HashMap<String, Integer> bookings = new HashMap<>();
    static ArrayList<String> bookingHistory = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // initialize rooms
        for(int i=1;i<=5;i++){
            rooms.put(i,false);
        }

        while(true){

            System.out.println("\n===== BOOK MY STAY =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Request Booking");
            System.out.println("3. Process Booking");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Search Booking");
            System.out.println("6. View Booking History");
            System.out.println("7. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    viewRooms();
                    break;

                case 2:
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    bookingQueue.add(name);
                    System.out.println("Booking request added to queue.");
                    break;

                case 3:
                    processBooking();
                    break;

                case 4:
                    System.out.print("Enter Customer Name: ");
                    String cancel = sc.nextLine();
                    cancelBooking(cancel);
                    break;

                case 5:
                    System.out.print("Enter Customer Name: ");
                    String search = sc.nextLine();
                    searchBooking(search);
                    break;

                case 6:
                    viewHistory();
                    break;

                case 7:
                    System.out.println("Thank you for using BookMyStay!");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    static void viewRooms(){
        System.out.println("\nRoom Status");
        for(int room : rooms.keySet()){
            if(rooms.get(room))
                System.out.println("Room "+room+" : Booked");
            else
                System.out.println("Room "+room+" : Available");
        }
    }

    static void processBooking(){

        if(bookingQueue.isEmpty()){
            System.out.println("No booking requests.");
            return;
        }

        String customer = bookingQueue.poll();

        for(int room : rooms.keySet()){

            if(!rooms.get(room)){
                rooms.put(room,true);
                bookings.put(customer,room);

                bookingHistory.add(customer + " booked room " + room);

                System.out.println("Room "+room+" booked for "+customer);
                return;
            }
        }

        System.out.println("No rooms available.");
    }

    static void cancelBooking(String name){

        if(bookings.containsKey(name)){

            int room = bookings.get(name);

            rooms.put(room,false);
            bookings.remove(name);

            bookingHistory.add(name + " cancelled room " + room);

            System.out.println("Booking cancelled.");
        }
        else{
            System.out.println("Booking not found.");
        }
    }

    static void searchBooking(String name){

        if(bookings.containsKey(name))
            System.out.println(name + " has booked room " + bookings.get(name));
        else
            System.out.println("No booking found for "+name);
    }

    static void viewHistory(){

        if(bookingHistory.isEmpty()){
            System.out.println("No history available.");
            return;
        }

        System.out.println("\nBooking History");

        for(String record : bookingHistory){
            System.out.println(record);
        }
    }
}