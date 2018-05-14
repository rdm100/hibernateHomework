import db.DBAuthor;
import db.DBBook;
import models.Author;
import models.Book;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        Book book1 = new Book("Book1", "Hello");
        Book book2 = new Book("Book2", "Hello2");
        DBBook.save(book1);
        DBBook.save(book2);
        book2.setTitle("hey");
        DBBook.update(book2);
        DBBook.delete(book1);

        List<Book> bookList = DBBook.getBooks();

        Author author1 = new Author("Dave", "Jones");
        Author author2 = new Author("Mike", "Davis");
        DBAuthor.save(author1);
        DBAuthor.save(author2);
        author2.setLastName("hey");
        DBAuthor.update(author2);
        DBAuthor.delete(author1);

        List<Author> authorList = DBAuthor.getAuthors();


    }


}
