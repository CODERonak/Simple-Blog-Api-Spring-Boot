package com.code.SimpleBlogAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column; // Import the Column annotation
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")  // Explicitly specify the column name for title
    String title;

    @Column(name = "content")  // Explicitly specify the column name for content
    String content;

    @Column(name = "created_by")  // Explicitly map the createdBy field to the 'createdBy' column
    String createdBy;
}
