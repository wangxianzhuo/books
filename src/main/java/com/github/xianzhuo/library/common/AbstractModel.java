package com.github.xianzhuo.library.common;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.UUID;

/**
 * Created by shangjie on 2017/5/10.
 */
public abstract class AbstractModel {
    @Expose
    private String id;
    @Expose
    private Date createdTime;
    @Expose
    private Date updatedTime;

    public AbstractModel() {
    }

    public AbstractModel(String id, Date createdTime, Date updatedTime) {
        this.id = id;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void generateId() {
        if (id == null || id.trim().isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }
}
