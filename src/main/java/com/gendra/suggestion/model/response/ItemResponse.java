package com.gendra.suggestion.model.response;

public class ItemResponse<T> {

    private T item;

    private Integer status;

    public ItemResponse() {
    }

    public ItemResponse(T item) {
        super();
        this.item = item;
    }

    public ItemResponse(T item, Integer Status) {
        super();
        this.item = item;
        this.status = Status;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
