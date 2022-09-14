package com.example.coollibrary.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book extends BaseEntity {

    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="locked")
    private boolean locked;
    @Column(name="client_id")
    private Long clientId;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return title.equals(book.title)   &&
                author.equals(book.author);

    }
}
