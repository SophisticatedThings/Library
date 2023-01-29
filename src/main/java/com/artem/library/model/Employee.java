package com.artem.library.model;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee extends BaseEntity {

    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Role  role;
    @Column(name="salary")
    private Double salary;
    @Column(name= "phone_number")
    private String  phoneNumber;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;



}
