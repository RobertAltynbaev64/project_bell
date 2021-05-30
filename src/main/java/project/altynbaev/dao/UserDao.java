package project.altynbaev.dao;

import project.altynbaev.model.Organization;
import project.altynbaev.model.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    void save(User user);

    void update(User user, int id);

    List<User> filter(project.altynbaev.dto.user.UserFilterInDto user);
}
