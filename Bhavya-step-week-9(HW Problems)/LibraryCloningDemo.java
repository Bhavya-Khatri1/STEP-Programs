import java.util.*;

class Book implements Cloneable {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

class Library implements Cloneable {
    List<Book> books;

    Library(List<Book> books) {
        this.books = books;
    }

    // Shallow clone
    protected Object cloneShallow() throws CloneNotSupportedException {
        return super.clone(); // copies reference of list
    }

    // Deep clone
    protected Object cloneDeep() throws CloneNotSupportedException {
        List<Book> clonedBooks = new ArrayList<>();
        for (Book b : books) {
            clonedBooks.add((Book) b.clone()); // clone each book individually
        }
        return new Library(clonedBooks);
    }

    @Override
    public String toString() {
        return books.toString();
    }
}

public class LibraryCloningDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("1984", "George Orwell"));
        bookList.add(new Book("To Kill a Mockingbird", "Harper Lee"));

        Library lib1 = new Library(bookList);

        // Shallow clone
        Library shallowLib = (Library) lib1.cloneShallow();

        // Deep clone
        Library deepLib = (Library) lib1.cloneDeep();

        System.out.println("Original Library: " + lib1);
        System.out.println("Shallow Clone: " + shallowLib);
        System.out.println("Deep Clone: " + deepLib);

        // Modify a book in cloned library
        shallowLib.books.get(0).title = "Animal Farm";

        System.out.println("\nAfter modifying shallow clone:");
        System.out.println("Original Library: " + lib1); // affected
        System.out.println("Shallow Clone: " + shallowLib); // affected
        System.out.println("Deep Clone: " + deepLib); // unaffected
    }
}