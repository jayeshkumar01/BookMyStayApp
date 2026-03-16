import java.util.HashMap;
import java.util.Stack;

static HashMap<String, Boolean> paymentStatus = new HashMap<>();
static HashMap<String, Boolean> checkinStatus = new HashMap<>();

// UC7 Payment Processing
static void processPayment(String guestName, HashMap<String,String> reservations){

    if(!reservations.containsKey(guestName)){
        System.out.println("Reservation not found for " + guestName);
        return;
    }

    if(paymentStatus.containsKey(guestName)){
        System.out.println("Payment already completed for " + guestName);
        return;
    }

    paymentStatus.put(guestName, true);
    System.out.println("Payment successful for " + guestName);
}

// UC8 Guest Check-in
static void checkInGuest(String guestName){

    if(!paymentStatus.containsKey(guestName)){
        System.out.println("Payment required before check-in");
        return;
    }

    checkinStatus.put(guestName,true);
    System.out.println("Check-in successful for " + guestName);
}case 7:

System.out.println("Enter Guest Name:");
String payName = sc.nextLine();
processPayment(payName, reservations);
break;

case 8:

System.out.println("Enter Guest Name:");
String checkName = sc.nextLine();
checkInGuest(checkName);
break;System.out.println("7 Make Payment");
System.out.println("8 Guest Check-in");
