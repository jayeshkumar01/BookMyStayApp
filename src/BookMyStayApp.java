import java.util.*;

public class BookMyStayApp {

    static HashMap<Integer, Boolean> rooms = new HashMap<>();
    static Queue<String> bookingQueue = new LinkedList<>();
    static HashMap<String, Integer> bookings = new HashMap<>();
    static ArrayList<String> bookingHistory = new ArrayList<>();

    // UC4 Stack for Undo Cancel
    static Stack<String> cancelStack = new Stack<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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
            System.out.println("7. Check-In");
            System.out.println("8. Check-Out");
            System.out.println("9. Undo Last Cancellation");
            System.out.println("10. Exit");

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
                    System.out.println("Booking request added.");
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
                    System.out.print("Enter Customer Name: ");
                    String checkin = sc.nextLine();
                    checkIn(checkin);
                    break;

                case 8:
                    System.out.print("Enter Customer Name: ");
                    String checkout = sc.nextLine();
                    checkOut(checkout);
                    break;

                case 9:
                    undoCancel();
                    break;

                case 10:
                    System.out.println("Thank you for using BookMyStay!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void viewRooms(){

        for(int room : rooms.keySet()){

            if(rooms.get(room))
                System.out.println("Room "+room+" : Occupied");
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
                bookingHistory.add(customer+" booked room "+room);

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

            cancelStack.push(name); // push into stack
            bookingHistory.add(name+" cancelled booking");

            System.out.println("Booking cancelled.");
        }
        else{
            System.out.println("Booking not found.");
        }
    }

    static void undoCancel(){

        if(cancelStack.isEmpty()){
            System.out.println("No cancellation to undo.");
            return;
        }

        String customer = cancelStack.pop();
        bookingQueue.add(customer);

        System.out.println("Cancellation undone. Booking request restored for "+customer);
    }

    static void searchBooking(String name){

        if(bookings.containsKey(name))
            System.out.println(name+" has room "+bookings.get(name));
        else
            System.out.println("Booking not found.");
    }

    static void viewHistory(){

        for(String record : bookingHistory){
            System.out.println(record);
        }
    }

    static void checkIn(String name){

        if(bookings.containsKey(name))
            System.out.println(name+" checked into room "+bookings.get(name));
        else
            System.out.println("No booking found.");
    }

    static void checkOut(String name){

        if(bookings.containsKey(name)){

            int room = bookings.get(name);

            rooms.put(room,false);
            bookings.remove(name);

            bookingHistory.add(name+" checked out");

            System.out.println("Checkout successful.");
        }
        else{
            System.out.println("Customer not found.");
        }
    }
}