// Q3_HotelReservationSystem

class Room {
    String roomNumber;
    String roomType;
    double pricePerNight;
    boolean isAvailable;
    int maxOccupancy;

    public Room(String rn, String rt, double price, int maxOcc) {
        this.roomNumber = rn;
        this.roomType = rt;
        this.pricePerNight = price;
        this.maxOccupancy = maxOcc;
        this.isAvailable = true;  // initially all rooms available
    }
}

class Guest {
    String guestId;
    String guestName;
    String phoneNumber;
    String email;
    String[] bookingHistory;
    int bookingCount;

    public Guest(String id, String name, String phone, String email) {
        this.guestId = id;
        this.guestName = name;
        this.phoneNumber = phone;
        this.email = email;
        this.bookingHistory = new String[10]; // store up to 10 bookings
        this.bookingCount = 0;
    }

    public void addBookingHistory(String bookingId) {
        if (bookingCount < bookingHistory.length) {
            bookingHistory[bookingCount] = bookingId;
            bookingCount++;
        }
    }
}

class Booking {
    String bookingId;
    Guest guest;
    Room room;
    String checkInDate;
    String checkOutDate;
    double totalAmount;

    public Booking(String bid, Guest g, Room r, String in, String out) {
        this.bookingId = bid;
        this.guest = g;
        this.room = r;
        this.checkInDate = in;
        this.checkOutDate = out;
        this.totalAmount = r.pricePerNight; // assume 1 night for simplicity
    }
}

class Hotel {
    static int totalBookings = 0;
    static double hotelRevenue = 0.0;
    static String hotelName = "Campus Hotel";

    Room[] rooms;
    Booking[] bookings;
    int bookingCount;

    public Hotel(int roomCapacity, int bookingCapacity) {
        rooms = new Room[roomCapacity];
        bookings = new Booking[bookingCapacity];
        bookingCount = 0;
    }

    public void addRoom(Room r, int index) {
        if (index >= 0 && index < rooms.length) {
            rooms[index] = r;
        }
    }

    public boolean checkAvailability(String roomType) {
        for (Room r : rooms) {
            if (r != null && r.roomType.equalsIgnoreCase(roomType) && r.isAvailable) {
                return true;
            }
        }
        return false;
    }

    public Booking makeReservation(Guest g, String roomType, String in, String out) {
        for (Room r : rooms) {
            if (r != null && r.roomType.equalsIgnoreCase(roomType) && r.isAvailable) {
                String bid = "B" + (++totalBookings);
                Booking b = new Booking(bid, g, r, in, out);
                r.isAvailable = false; // mark room unavailable
                hotelRevenue += b.totalAmount;
                bookings[bookingCount] = b;
                bookingCount++;
                g.addBookingHistory(bid);
                return b;
            }
        }
        return null; // no available room
    }

    public boolean cancelReservation(String bookingId) {
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i] != null && bookings[i].bookingId.equals(bookingId)) {
                bookings[i].room.isAvailable = true; // free up room
                hotelRevenue -= bookings[i].totalAmount;
                bookings[i] = null;
                return true;
            }
        }
        return false;
    }

    public double calculateBill(String bookingId) {
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i] != null && bookings[i].bookingId.equals(bookingId)) {
                return bookings[i].totalAmount;
            }
        }
        return 0.0;
    }

    public double getOccupancyRate() {
        int total = 0, occupied = 0;
        for (Room r : rooms) {
            if (r != null) {
                total++;
                if (!r.isAvailable) occupied++;
            }
        }
        if (total == 0) return 0.0;
        return (occupied * 100.0) / total;
    }

    public double getTotalRevenue() {
        return hotelRevenue;
    }
}

public class Q3_HotelReservationSystem {
    public static void main(String[] args) {
        // Create hotel with capacity for 5 rooms and 10 bookings
        Hotel hotel = new Hotel(5, 10);

        // Add rooms
        hotel.addRoom(new Room("101", "Single", 1500, 1), 0);
        hotel.addRoom(new Room("102", "Double", 2500, 2), 1);
        hotel.addRoom(new Room("201", "Suite", 5000, 4), 2);

        // Create guest
        Guest g1 = new Guest("G1", "Emma", "9999999999", "emma@example.com");

        // Make reservation
        Booking b1 = hotel.makeReservation(g1, "Double", "2025-09-10", "2025-09-11");
        if (b1 != null) {
            System.out.println("Booking successful: " + b1.bookingId + " | Amount: " + b1.totalAmount);
        } else {
            System.out.println("No room available.");
        }

        // Show hotel stats
        System.out.println("Occupancy rate: " + hotel.getOccupancyRate() + "%");
        System.out.println("Hotel revenue: " + hotel.getTotalRevenue());
    }
}