package com.calebe.quarkus.starting;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/api/books")//Path that will return everything when an http request is called
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject //It will instantiate the book repository thanks to @ApplicationScoped
    BookRepository bookRepository; //From repository

    @Inject
    Logger logger;

    @GET
    public List<Book> getAllBooks() { //No path set, so it will return this list as Json when /api/books be called
        logger.info("Returns all books");
        return bookRepository.getAllBooks();// Invoked from BookRepository class
    }

    @GET
    @Path("/count")// So when /api/books/count was requested, it will show the amount of books
    @Produces(MediaType.TEXT_PLAIN)//This overrides the default produces
    public int countAllBooks() {
        logger.info("Returns the number of books");
        return bookRepository.getAllBooks().size();
    }

    @GET
    @Path("{id}")
    public Optional<Book> getBook(@PathParam("id") int id) { //Return optional for handling possible null numberscu
        logger.info("Returns a single book with id: " + id);
        return bookRepository.getBook(id);
    }
}