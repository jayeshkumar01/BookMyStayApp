import java.util.HashMap;
import java.util.Stack;

static HashMap<String, Boolean> paymentStatus = new HashMap<>();
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
}System.out.println("7 Make Payment");case 7:

System.out.println("Enter Guest Name:");
String payName = sc.nextLine();

processPayment(payName, reservations);

break;