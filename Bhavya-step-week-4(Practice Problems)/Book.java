public class Book {
    String title;
    String author;
    double price;
    Book() {
        title = "Unknown Title";
        author = "Unknown Author";
        price = 0.0;
    }
    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
        System.out.println();
    }
    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("1984", "George Orwell", 299.99);
        System.out.println("=== Book Details ===");
        book1.display();
        book2.display();
    }
}