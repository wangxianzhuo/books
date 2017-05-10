package com.github.xianzhuo.library.model;

import com.github.xianzhuo.library.common.AbstractModel;
import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by shangjie on 2017/5/10.
 */
public class Library extends AbstractModel {
    @Expose
    private String parentId;
    @Expose
    private Integer weight;
    @Expose
    private Long capacity;
    @Expose
    private Long size;

    public Library() {
    }

    public Library(String parentId, Integer weight, Long capacity, Long size) {
        this.parentId = parentId;
        this.weight = weight;
        this.capacity = capacity;
        this.size = size;
        generateId();
        setCreatedTime(new Date());
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
