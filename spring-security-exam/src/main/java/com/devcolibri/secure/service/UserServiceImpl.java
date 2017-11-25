package com.devcolibri.secure.service;

import com.devcolibri.secure.data.HibernateUsersDaoImpl;
import com.devcolibri.secure.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String login) {
        User user = new HibernateUsersDaoImpl().findUserByLogin(login);



        return user;
    }

}
