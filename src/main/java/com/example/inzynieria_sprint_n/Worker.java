package com.example.inzynieria_sprint_n;

public class Worker {
    private String username;
    private String password;

    public Worker(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Worker: " + this.getUsername() + ", password: " + getPassword();
    }
}
