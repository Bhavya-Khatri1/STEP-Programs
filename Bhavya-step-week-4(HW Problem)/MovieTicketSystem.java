class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    // 1. Default constructor
    MovieTicket() {
        this.movieName = "Unknown";
        this.theatreName = "Not Assigned";
        this.seatNumber = 0;
        this.price = 0.0;
    }

    // 2. Constructor with movie name
    MovieTicket(String movieName) {
        this.movieName = movieName;
        this.theatreName = "Not Assigned";
        this.seatNumber = 0;
        this.price = 200.0;
    }

    // 3. Constructor with movie name and seat number
    MovieTicket(String movieName, int seatNumber) {
        this.movieName = movieName;
        this.theatreName = "PVR";
        this.seatNumber = seatNumber;
        this.price = 250.0;
    }

    // 4. Full constructor
    MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    void printTicket() {
        System.out.println("🎟 Ticket Details:");
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: ₹" + price);
        System.out.println("---------------------");
    }
}

public class MovieTicketSystem {
    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Inception");
        MovieTicket t3 = new MovieTicket("Interstellar", 45);
        MovieTicket t4 = new MovieTicket("Dune", "IMAX", 12, 500.0);

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}