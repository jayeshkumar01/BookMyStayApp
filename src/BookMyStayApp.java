// Payment
static void processPayment(String name){
    if(!reservations.containsKey(name)){
        System.out.println("Reservation not found");
        return;
    }
    paymentStatus.put(name,true);
    System.out.println("Payment successful");
}

// Check-in
static void checkInGuest(String name){
    if(!paymentStatus.containsKey(name)){
        System.out.println("Payment required");
        return;
    }
    checkinStatus.put(name,true);
    System.out.println("Check-in successful");
}

// UC9 Report
static void generateReport(){

    System.out.println("\n--- Booking Report ---");

    System.out.println("Total Reservations: " + reservations.size());
    System.out.println("Total Payments: " + paymentStatus.size());
    System.out.println("Total Check-ins: " + checkinStatus.size());
}

