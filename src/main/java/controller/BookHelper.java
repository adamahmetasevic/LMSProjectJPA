package controller;



import java.util.List;
import javax.persistence.*;
import model.Book;

public class BookHelper {

    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("library");

    public void insertBook(Book book) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();
    }

    public List<Book> getAllBooks() {
        EntityManager em = emfactory.createEntityManager();
        List<Book> allBooks = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        em.close();
        return allBooks;
    }

    public void deleteBook(Book book) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        Book foundBook = em.find(Book.class, book.getId());
        em.remove(foundBook);
        em.getTransaction().commit();
        em.close();
    }

    public List<Book> searchForBookByTitle(String title) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Book> typedQuery = em.createQuery("SELECT b FROM Book b WHERE b.title = :selectedTitle", Book.class);
        typedQuery.setParameter("selectedTitle", title);
        List<Book> foundBooks = typedQuery.getResultList();
        em.close();
        return foundBooks;
    }

    public Book findBookById(int id) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        Book foundBook = em.find(Book.class, id);
        em.close();
        return foundBook;
    }

    public void updateBook(Book book) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
        em.close();
    }

    public void cleanUp() {
        emfactory.close();
    }
}
