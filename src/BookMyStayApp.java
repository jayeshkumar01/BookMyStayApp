import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class BookMyStayApp {

    // Core Data
    static HashMap<String,String> reservations = new HashMap<>();
    static HashMap<String,Boolean> paymentStatus = new HashMap<>();
    static HashMap<String,Boolean> checkinStatus = new HashMap<>();
    static ArrayList<String> bookingHistory = new ArrayList<>();
    static Stack<String> cancelledRooms = new Stack<>();

    // UC11 Queue
    static Queue<String> bookingQueue = new LinkedList<>();

    // UC1
    static void viewRooms(){
        System.out.println("\nRooms: Single ₹2000 | Double ₹3500 | Suite ₹5000");
    }

    // UC2
    static void bookRoom(String name){
        if(reservations.containsKey(name)){
            System.out.println("Already booked!");
            return;
        }
        reservations.put(name,"Room");
        bookingHistory.add(name);
        System.out.println("Booked for " + name);
    }

    // UC3
    static void addService(String name){
        if(!reservations.containsKey(name)){
            System.out.println("No reservation");
            return;
        }
        System.out.println("Service added for " + name);
    }

    // UC4
    static void viewHistory(){
        System.out.println("\nHistory:");
        for(String g: bookingHistory){
            System.out.println(g);
        }
    }

    // UC5
    static void validateBooking(String name){
        if(name==null || name.isEmpty()){
            System.out.println("Invalid");
            return;
        }
        System.out.println("Valid input");
    }

    // UC6
    static void cancelBooking(String name){
        if(!reservations.containsKey(name)){
            System.out.println("No booking");
            return;
        }
        reservations.remove(name);
        cancelledRooms.push(name);
        System.out.println("Cancelled " + name);
    }

    // UC7
    static void processPayment(String name){
        if(!reservations.containsKey(name)){
            System.out.println("No reservation");
            return;
        }
        if(paymentStatus.containsKey(name)){
            System.out.println("Already paid");
            return;
        }
        paymentStatus.put(name,true);
        System.out.println("Payment done");
    }

    // UC8
    static void checkInGuest(String name){
        if(!paymentStatus.containsKey(name)){
            System.out.println("Pay first");
            return;
        }
        checkinStatus.put(name,true);
        System.out.println("Checked in");
    }

    // UC9
    static void generateReport(){
        System.out.println("\n--- Report ---");
        System.out.println("Reservations: " + reservations.size());
        System.out.println("Payments: " + paymentStatus.size());
        System.out.println("Check-ins: " + checkinStatus.size());
    }

    // UC10
    static void systemSummary(){
        System.out.println("\n--- Summary ---");
        System.out.println("Reservations: " + reservations.size());
        System.out.println("Cancelled: " + cancelledRooms.size());
    }

    // UC11 (Concurrency)
    static synchronized void processConcurrentBooking(String name){
        if(!reservations.containsKey(name)){
            reservations.put(name,"Room");
            System.out.println(Thread.currentThread().getName()+" booked "+name);
        }
    }

    static void simulateConcurrentBooking(){
        bookingQueue.add("A");
        bookingQueue.add("B");
        bookingQueue.add("C");

        Runnable task = () -> {
            while(true){
                String guest;
                synchronized(bookingQueue){
                    if(bookingQueue.isEmpty()) return;
                    guest = bookingQueue.poll();
                }
                processConcurrentBooking(guest);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(Exception e){}
    }

    // UC12 (Persistence)
    static void saveData(){
        try{
            FileWriter fw = new FileWriter("data.txt");
            for(String name: reservations.keySet()){
                fw.write(name + "\n");
            }
            fw.close();
            System.out.println("Saved");
        }catch(Exception e){
            System.out.println("Error saving");
        }
    }

    static void loadData(){
        try{
            File file = new File("data.txt");
            if(!file.exists()) return;

            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String name = sc.nextLine();
                reservations.put(name,"Room");
            }
            sc.close();
        }catch(Exception e){
            System.out.println("Error loading");
        }
    }

    // MAIN
    public static void main(String[] args){

        loadData(); // UC12 load

        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            System.out.println("\n--- Menu ---");
            System.out.println("1 View Rooms");
            System.out.println("2 Book Room");
            System.out.println("3 Add Services");
            System.out.println("4 History");
            System.out.println("5 Validate");
            System.out.println("6 Cancel");
            System.out.println("7 Payment");
            System.out.println("8 Check-in");
            System.out.println("9 Report");
            System.out.println("10 Summary");
            System.out.println("11 Concurrent Booking");
            System.out.println("12 Save Data");
            System.out.println("13 Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1: viewRooms(); break;
                case 2: bookRoom(sc.nextLine()); break;
                case 3: addService(sc.nextLine()); break;
                case 4: viewHistory(); break;
                case 5: validateBooking(sc.nextLine()); break;
                case 6: cancelBooking(sc.nextLine()); break;
                case 7: processPayment(sc.nextLine()); break;
                case 8: checkInGuest(sc.nextLine()); break;
                case 9: generateReport(); break;
                case 10: systemSummary(); break;
                case 11: simulateConcurrentBooking(); break;
                case 12: saveData(); break;
                case 13: System.out.println("Exit"); break;
                default: System.out.println("Invalid");
            }

        }while(choice!=13);

        sc.close();
    }
}

