package com.artem.library.model;


import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    @OneToMany(mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Book> books = new LinkedList<>();
    public Client(String name, String surname, String phoneNumber, String email, String password, Status status) {
        this.name = name;
        Surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}
