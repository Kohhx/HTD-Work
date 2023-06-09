package com.project.EmployeeApplication.authentication;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HardCodedAuthenticationService {

    private static List<User> users = new ArrayList<>();
    private static int userId = 0;
    public boolean isUserLoggedIn = false;
    public String role = "";

    {
        users.add(new User(++userId, "kohhx", "password", "ADMIN"));
        users.add(new User(++userId, "leon", "password", "USER"));
    }

    public boolean authenticate(String username, String password) {
        Optional<User> userFound = users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
        if (userFound.isPresent()) {
            role = userFound.get().getRole();
        }
        return userFound.isPresent();
    }

    public String getRole() {
        return role;
    }

//    public void setUserLoggedIn() {
//        isUserLoggedIn = true;
//    }
//
//    public void setUserLoggedOut() {
//        isUserLoggedIn = false;
//    }
//
//    public boolean isUserLoggedIn() {
//        return isUserLoggedIn;
//    }

}
