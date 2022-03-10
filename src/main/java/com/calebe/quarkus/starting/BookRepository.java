package com.calebe.quarkus.starting;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    // All got from application.properties
    @ConfigProperty(name = "books.genreC", defaultValue = "Comedy")
    String genreC;

    @ConfigProperty(name="books.genreH", defaultValue = "Horror")
    String genreH;

    @ConfigProperty(name="books.genreD", defaultValue = "Drama")
    String genreD;

    @ConfigProperty(name="books.genreA", defaultValue = "Adventure")
    String genreA;

    //Methods
    public List<Book> getAllBooks() { //Just returns a list of Books
        return List.of(
                new Book(1, "An Interesting Book", "Joseph Trickle", 2020, genreC),
                new Book(2, "Wonderful Book", "Maria Petrovska", 2001, genreH),
                new Book(3, "Wonders of the Antique World", "Oliver Field", 2005, genreD),
                new Book(4, "What it Matters", "Jean Fontaine", 1990, genreA)
        );
    }

    public Optional<Book> getBook(int id) { //The id is passed by the BookResource class as PathParam
        return getAllBooks().stream().filter(book -> {
            return book.getId() == id;
        }).findFirst();

    }
}