package project.altynbaev.user.dao;

import project.altynbaev.user.model.User;
import project.altynbaev.user.view.*;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {
    /**
     * Получить список организаций по фильтру
     *
     * @param userListReqView
     * @return
     */
    public List<UserListRespView> getListByFilter(UserListReqView userListReqView);

    /**
     * Получить организацию по id
     *
     * @param id
     * @return
     */
    public UserByIdRespView getUserById(Long id);

    /**
     * Добавить организацию
     *
     * @param user
     */
    public void save(User user);

    /**
     * Обновить организацию
     *
     * @param userUpdateReqView
     */
    public void update(UserUpdateReqView userUpdateReqView);
}
