class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    // Default constructor
    Book() {
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.isAvailable = true;
    }

    // Constructor with title and author
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isbn = "Not Assigned";
        this.isAvailable = true;
    }

    // Full constructor
    Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is not available.");
        }
    }

    void returnBook() {
        isAvailable = true;
        System.out.println(title + " has been returned.");
    }

    void displayBookInfo() {
        System.out.println("📖 Book Details:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("---------------------");
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("1984", "George Orwell");
        Book b3 = new Book("The Hobbit", "J.R.R. Tolkien", "123456789", true);

        b2.borrowBook();
        b2.displayBookInfo();

        b2.returnBook();
        b2.displayBookInfo();

        b3.displayBookInfo();
    }
}