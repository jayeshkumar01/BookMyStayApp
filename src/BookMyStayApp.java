HashMap<String,Integer> inventory = new HashMap<>();

inventory.put("Standard",2);
inventory.put("Deluxe",1);
inventory.put("Suite",0);

Scanner sc = new Scanner(System.in);

System.out.println("Enter Name:");
String name = sc.nextLine();

System.out.println("Enter Room Type:");
String roomType = sc.nextLine();

try {

    validateBooking(roomType, inventory);

    System.out.println("Booking request accepted");

}
catch(InvalidBookingException e){

    System.out.println("Error: " + e.getMessage());

}