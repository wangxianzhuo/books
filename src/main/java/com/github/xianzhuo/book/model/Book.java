package com.github.xianzhuo.book.model;

import com.github.xianzhuo.book.common.AbstractModel;
import com.google.gson.annotations.Expose;


/**
 * Created by shangjie on 2017/5/2.
 */
public class Book extends AbstractModel{
    @Expose
    private String id;
    @Expose
    private String isbn;
    @Expose
    private String name;
    @Expose
    private String publisher;
    @Expose
    private String authors;

    public Book() {
    }

    public Book(String id, String isbn, String name, String publisher, String authors) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
        this.authors = authors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
