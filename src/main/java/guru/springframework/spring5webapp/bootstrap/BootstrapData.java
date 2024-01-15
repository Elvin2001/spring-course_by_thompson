package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher firstPublisher = new Publisher("Goldwig Publishing", LocalDate.of(2005, 11, 23));
        publisherRepository.save(firstPublisher);

        Author firstAuthor = new Author("Mikhail", "Bulgakov");
        Book firstBook = new Book("Master and Margarita", "133399292");

        firstAuthor.getBooks().add(firstBook);
        firstBook.getAuthors().add(firstAuthor);
        firstBook.setPublisher(firstPublisher);
        firstPublisher.getBooks().add(firstBook);

        authorRepository.save(firstAuthor);
        bookRepository.save(firstBook);


        Publisher secondPublisher = new Publisher("Henry Publication House", LocalDate.of(2002, 2, 11));
        publisherRepository.save(secondPublisher);
        Author secondAuthor = new Author("Aleksandr", "Pushkin");
        Book secondBook = new Book("Evgeniy Onegin", "34922231");

        secondAuthor.getBooks().add(secondBook);
        secondBook.getAuthors().add(secondAuthor);
        firstBook.setPublisher(secondPublisher);
        secondPublisher.getBooks().add(secondBook);

        bookRepository.save(secondBook);
        authorRepository.save(secondAuthor);


        System.out.println("Numbers of books = " + bookRepository.count());
        System.out.println("Numbers of second author books = " + secondAuthor.getBooks().size());
        System.out.println("Numbers of first publisher books = " + firstPublisher.getBooks().size());
    }
}
