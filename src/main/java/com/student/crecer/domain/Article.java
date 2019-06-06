package com.student.crecer.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field cannot empty")
    @Length(max = 2048, message = "Too long")
    private String text;
    @Length(max = 255, message = "Too long")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User avtor;

    private String filename;

    public Article() {
    }

    public Article(String text, String tag, User user) {
        this.avtor = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAvtorName() {
        return avtor != null ? avtor.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAvtor() {
        return avtor;
    }

    public void setAvtor(User avtor) {
        this.avtor = avtor;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
