package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.User;

public interface UserService {

    User registerUser(User user);

    User getUserByEmail(String email);
    
    User loginUser(String email, String password);
}