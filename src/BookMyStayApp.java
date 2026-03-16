import java.util.HashMap;
import java.util.Stack;

static HashMap<String, Boolean> checkinStatus = new HashMap<>();static void checkInGuest(String guestName){

    if(!paymentStatus.containsKey(guestName)){
        System.out.println("Payment required before check-in");
        return;
    }

    checkinStatus.put(guestName,true);

    System.out.println("Check-in successful for " + guestName);
}System.out.println("8 Guest Check-in");