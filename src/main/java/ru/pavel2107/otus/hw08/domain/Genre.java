package ru.pavel2107.otus.hw08.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "genres")
@ToString
public  class Genre {
    @Setter @Getter @Id private String id;
    @Setter @Getter private String name;
}
