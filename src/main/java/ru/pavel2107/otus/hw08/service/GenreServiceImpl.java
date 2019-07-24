package ru.pavel2107.otus.hw08.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.otus.hw08.domain.Book;
import ru.pavel2107.otus.hw08.domain.Genre;
import ru.pavel2107.otus.hw08.repository.mongoDB.BookRepository;
import ru.pavel2107.otus.hw08.repository.mongoDB.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {


    private GenreRepository repository;
    private BookRepository  bookRepository;


    @Autowired
    public GenreServiceImpl(GenreRepository repository, BookRepository bookRepository){

        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    @HystrixCommand( fallbackMethod = "defaultSave")
    public Genre save(Genre genre) {
        return repository.save( genre);
    }

    @HystrixCommand
    private Genre defaultSave (Genre genre) {
        return new Genre();
    }

    @Override
    @HystrixCommand( fallbackMethod = "defaultDelete")
    public void delete(String id) throws Exception{
        Genre g = find( id);
        if( g != null) {
            if( bookRepository.existsByGenreId( id)) {
                throw new Exception( "There are books for genre #" + id);
            }
            else{
                repository.delete(g);
            }
        }
    }

    @HystrixCommand
    private void defaultDelete(String id) throws Exception{

    }

    @Override
    @HystrixCommand( fallbackMethod = "defaultFind")
    public Genre find(String id) {
        return repository.findById( id).orElse( null);
    }

    @HystrixCommand
    public Genre defaultFind(String id) {
        return null;
    }

    @Override
    @HystrixCommand( fallbackMethod = "defaultFindAll")
    public List<Genre> findAll() {
        return (List<Genre>)repository.findAll();
    }

    @HystrixCommand
    private List<Genre> defaultFindAll(){
        return new ArrayList<Genre>();
    }



}
