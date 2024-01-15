package com.alame.lab4.service;

import com.alame.lab4.model.DefaultPostResponse;
import com.alame.lab4.model.User;

public interface UserService {
    DefaultPostResponse register(User user);

    DefaultPostResponse authorize(User user);
}
