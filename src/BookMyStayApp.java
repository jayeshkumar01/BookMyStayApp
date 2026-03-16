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

    String roomType;

    if(roomID.startsWith("S"))
        roomType = "Standard";
    else if(roomID.startsWith("D"))
        roomType = "Deluxe";
    else
        roomType = "Suite";

    inventory.put(roomType, inventory.get(roomType) + 1);

    System.out.println("Booking cancelled successfully");
}System.out.println("5 Cancel Booking");case 5:

    System.out.println("Enter Guest Name:");
    String cancelName = sc.nextLine();

    cancelBooking(cancelName, reservations, inventory);

    break;