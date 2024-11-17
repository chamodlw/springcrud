package com.example.springcrud.model;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class book {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
}
