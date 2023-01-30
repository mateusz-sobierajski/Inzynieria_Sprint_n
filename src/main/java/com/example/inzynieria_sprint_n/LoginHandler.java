package com.example.inzynieria_sprint_n;

import com.example.inzynieria_sprint_n.password.PasswordUtils;

import java.util.List;

public class LoginHandler {

    private final CSVHandler csvHandler;

    public LoginHandler(CSVHandler csvHandler) {
        this.csvHandler = csvHandler;
    }


    public boolean handleLogin(String username, String password) {
        List<Worker> workers = csvHandler.loadWorkersFromFile("src/main/java/com/example/inzynieria_sprint_n/password/workers.csv");
        for (Worker worker : workers) {
            if (worker.getUsername().equals(username) && PasswordUtils.verifyPassword(password, worker.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
