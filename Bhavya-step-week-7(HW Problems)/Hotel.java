class HotelBooking {
    
    // Standard booking
    void bookRoom(String roomType, int nights) {
        int pricePerNight = getPrice(roomType);
        int total = pricePerNight * nights;
        System.out.println("Standard Booking:");
        System.out.println("Room: " + roomType + " | Nights: " + nights);
        System.out.println("Total Price: $" + total + "\n");
    }
    
    // Seasonal booking
    void bookRoom(String roomType, int nights, double seasonalMultiplier) {
        int pricePerNight = getPrice(roomType);
        double total = (pricePerNight * nights) * seasonalMultiplier;
        System.out.println("Seasonal Booking:");
        System.out.println("Room: " + roomType + " | Nights: " + nights);
        System.out.println("Seasonal Multiplier: " + seasonalMultiplier);
        System.out.println("Total Price: $" + total + "\n");
    }
    
    // Corporate booking
    void bookRoom(String roomType, int nights, double discount, boolean mealPackage) {
        int pricePerNight = getPrice(roomType);
        double total = (pricePerNight * nights) - discount;
        if(mealPackage) total += 50; // flat meal cost
        System.out.println("Corporate Booking:");
        System.out.println("Room: " + roomType + " | Nights: " + nights);
        System.out.println("Discount: $" + discount + " | Meal Package: " + mealPackage);
        System.out.println("Total Price: $" + total + "\n");
    }
    
    // Wedding package
    void bookRoom(String roomType, int nights, int guestCount, int decorationFee, int cateringFee) {
        int pricePerNight = getPrice(roomType);
        int total = (pricePerNight * nights) + decorationFee + cateringFee + (guestCount * 20);
        System.out.println("Wedding Package Booking:");
        System.out.println("Room: " + roomType + " | Nights: " + nights + " | Guests: " + guestCount);
        System.out.println("Decoration Fee: $" + decorationFee + " | Catering Fee: $" + cateringFee);
        System.out.println("Total Price: $" + total + "\n");
    }
    
    // Helper method to assign room prices
    private int getPrice(String roomType) {
        switch(roomType.toLowerCase()) {
            case "deluxe": return 200;
            case "suite": return 400;
            default: return 100; // standard room
        }
    }
}

public class Hotel {
    public static void main(String[] args) {
        HotelBooking booking = new HotelBooking();
        
        booking.bookRoom("Standard", 3);
        booking.bookRoom("Deluxe", 4, 1.5);
        booking.bookRoom("Suite", 2, 100, true);
        booking.bookRoom("Standard", 2, 50, 200, 500);
    }
}