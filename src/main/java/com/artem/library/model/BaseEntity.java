package com.artem.library.model;


import lombok.*;


import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
