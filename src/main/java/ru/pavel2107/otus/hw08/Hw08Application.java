package ru.pavel2107.otus.hw08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;
import ru.pavel2107.otus.hw08.domain.Author;
import ru.pavel2107.otus.hw08.repository.mongoDB.AuthorRepository;

import java.util.List;


@SpringBootApplication
@EnableHystrix
public class Hw08Application {
    public static void main(String[] args)  throws Exception {
        ApplicationContext context = SpringApplication.run(Hw08Application.class, args);
    }
}
