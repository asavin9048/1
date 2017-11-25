package com.devcolibri.secure.data;

import com.devcolibri.secure.entity.User;

import java.util.ArrayList;

public interface UsersDao {
    User findUserByLogin(String login);
}
