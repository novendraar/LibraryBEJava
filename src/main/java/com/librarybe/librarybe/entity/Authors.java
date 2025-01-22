package com.librarybe.librarybe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Authors extends BaseEntity {

    public Authors(String name) {
        this.name = name;
        this.publishedBooks = 0;
    }

    public Authors(String name, Integer publishedBooks) {
        this.name = name;
        this.publishedBooks = publishedBooks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 20, nullable = false)
    private Integer publishedBooks;

}
