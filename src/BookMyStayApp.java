static Stack<String> cancelledRooms = new Stack<>();

static void cancelBooking(String guestName,
                          HashMap<String,String> reservations,
                          HashMap<String,Integer> inventory) {

    if(!reservations.containsKey(guestName)){
        System.out.println("Reservation not found");
        return;
    }

    String roomID = reservations.get(guestName);

    cancelledRooms.push(roomID);

    reservations.remove(guestName);

    System.out.println("Booking cancelled successfully");
}