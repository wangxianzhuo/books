package com.github.xianzhuo.library.model;

import com.github.xianzhuo.library.common.AbstractModel;
import com.google.gson.annotations.Expose;

/**
 * Created by shangjie on 2017/5/10.
 */
public class BookFact extends AbstractModel{
    @Expose
    private String bookId;
    @Expose
    private String name;
    @Expose
    private String value;

    public BookFact() {
    }

    public BookFact(String bookId, String name, String value) {
        this.bookId = bookId;
        this.name = name;
        this.value = value;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
