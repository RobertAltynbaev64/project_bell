package project.altynbaev.dao;

import project.altynbaev.model.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    void save(User user);

    List<User> filter(User user, int docCode);

}
