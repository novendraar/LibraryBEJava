package com.librarybe.librarybe.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Books extends BaseEntity {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    // @Column(name = "publisher_name", nullable = false, unique = true, length =
    // 100)
    // private String name;

    // @Column(name = "address", nullable = false)
    // private String address;

    // @Column(name = "email", unique = true)
    // private String email;

    // @Column(columnDefinition = "TEXT")
    // private String description;

    // @Column(name = "price", precision = 10, scale = 2)
    // private BigDecimal price;
    public Books(String title, String synopsis, LocalDate publishedAt, Authors authors, Category category,
            Publisher publisher) {
        this.title = title;
        this.synopsis = synopsis;
        this.stock = 0;
        this.publishedAt = publishedAt;
        this.author = authors;
        this.category = category;
        this.publisher = publisher;
    }

    public Books(String title, String synopsis, Integer stock, LocalDate publishedAt, Authors authors,
            Category category,
            Publisher publisher) {
        this.title = title;
        this.synopsis = synopsis;
        this.stock = stock;
        this.publishedAt = publishedAt;
        this.author = authors;
        this.category = category;
        this.publisher = publisher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String synopsis;

    @Column(length = 10)
    private Integer stock;

    private LocalDate publishedAt;

    // Join table
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Authors author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;
}
