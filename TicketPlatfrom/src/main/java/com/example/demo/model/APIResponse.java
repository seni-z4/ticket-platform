package com.example.demo.model;

public class APIResponse<T> {

	private int count;
    private T data;

    public APIResponse(int count, T data) {
        this.count = count;
        this.data = data;
    }

    // Getters and Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
