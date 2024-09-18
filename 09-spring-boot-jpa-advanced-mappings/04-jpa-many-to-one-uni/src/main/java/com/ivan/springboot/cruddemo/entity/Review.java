package com.ivan.springboot.cruddemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    public Review(String comment) {
        this.comment = comment;
    }
}
