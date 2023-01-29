package com.artem.library.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Client extends BaseEntity {
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String Surname;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private Status status;
    @OneToMany()
    @JoinColumn(name="client_id")
    private List<Book> books = new LinkedList<>();

}
