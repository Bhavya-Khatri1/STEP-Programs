import java.util.*;
class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;
    public Book(String title, String author, String isbn, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void displayBook() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("-------------------");
    }
}
class Library {
    private String libraryName;
    private Book[] books;
    private int totalBooks;
    public Library(String libraryName, int size) {
        this.libraryName = libraryName;
        books = new Book[size];
        totalBooks = 0;
    }
    public void addBook(Book book) {
        if (totalBooks < books.length) {
            books[totalBooks] = book;
            totalBooks++;
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Library is full!");
        }
    }
    public void searchBook(String keyword) {
        boolean found = false;
        for (int i = 0; i < totalBooks; i++) {
            if (books[i].getTitle().equalsIgnoreCase(keyword) || books[i].getAuthor().equalsIgnoreCase(keyword)) {
                books[i].displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found for: " + keyword);
        }
    }
    public void displayAllBooks() {
        if (totalBooks == 0) {
            System.out.println("No books in the library.");
        } else {
            for (int i = 0; i < totalBooks; i++) {
                books[i].displayBook();
            }
        }
    }
    public void calculateTotalValue() {
        double totalValue = 0;
        for (int i = 0; i < totalBooks; i++) {
            totalValue += books[i].getPrice() * books[i].getQuantity();
        }
        System.out.println("Total value of all books: " + totalValue);
    }
}
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library("City Library", 100);
        int choice;
        do {
            System.out.println("\n=== LIBRARY MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Calculate Total Value");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    Book b = new Book(title, author, isbn, price, qty);
                    library.addBook(b);
                    break;

                case 2:
                    System.out.print("Enter Title or Author to search: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;

                case 3:
                    library.displayAllBooks();
                    break;

                case 4:
                    library.calculateTotalValue();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }
}