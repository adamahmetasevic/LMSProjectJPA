import java.util.List;
import java.util.Scanner;

import controller.BookHelper;
import model.Book;

public class BookMain {
    static Scanner in = new Scanner(System.in);
    static BookHelper bookHelper = new BookHelper();

    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        boolean goAgain = true;
        System.out.println("--- Welcome to our awesome book library! ---");
        while (goAgain) {
            System.out.println("*  Select an action:");
            System.out.println("*  1 -- Add a book");
            System.out.println("*  2 -- Edit a book");
            System.out.println("*  3 -- Delete a book");
            System.out.println("*  4 -- View all books");
            System.out.println("*  5 -- Exit");
            System.out.print("*  Your selection: ");
            int selection = in.nextInt();
            in.nextLine();

            switch (selection) {
                case 1:
                    addBook();
                    break;
                case 2:
                    editBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    viewAllBooks();
                    break;
                default:
                    System.out.println("Goodbye!");
                    goAgain = false;
                    break;
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter the title: ");
        String title = in.nextLine();
        System.out.print("Enter the author: ");
        String author = in.nextLine();
        Book newBook = new Book(title, author);
        bookHelper.insertBook(newBook);
    }

    private static void editBook() {
        System.out.print("Enter the book ID to edit: ");
        int bookId = in.nextInt();
        in.nextLine(); // Consume newline

        Book book = bookHelper.findBookById(bookId);
        if (book != null) {
            System.out.print("Enter the new title: ");
            String newTitle = in.nextLine();
            System.out.print("Enter the new author: ");
            String newAuthor = in.nextLine();

            book.setTitle(newTitle);
            book.setAuthor(newAuthor);

            bookHelper.updateBook(book);
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void deleteBook() {
        System.out.print("Enter the book ID to delete: ");
        int bookId = in.nextInt();
        in.nextLine(); // Consume newline

        Book book = bookHelper.findBookById(bookId);
        if (book != null) {
            bookHelper.deleteBook(book);
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    private static void viewAllBooks() {
        List<Book> books = bookHelper.getAllBooks();
        for (Book book : books) {
            System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor());
        }
    }
}
