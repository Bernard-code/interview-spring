package com.bernard.interview.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "answer", nullable = false, length = 4000)
    private String answer;

    @Column(name = "position", nullable = true)
    private int position;

    @Column(name = "category", nullable = true)
    private int category;
}
