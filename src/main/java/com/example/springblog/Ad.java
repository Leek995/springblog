package com.example.springblog;
import javax.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    public Ad(){};

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Ad(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
