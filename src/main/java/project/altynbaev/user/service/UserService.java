package project.altynbaev.user.service;

import project.altynbaev.user.view.*;

import java.util.List;

public interface UserService {

    /**
     * Получить список организаций по фильтру
     * @param userListReqView
     * @return
     */
    public List<UserListRespView> getUserByFilter(UserListReqView userListReqView);

    /**
     * Получить организацию по id
     * @param id
     * @return
     */
    public UserByIdRespView getUserById(Long id);

    /**
     * Добавить организацию
     * @param userSaveReqView
     */
    public void saveUser(UserSaveReqView userSaveReqView);

    /**
     * Обновить организацию
     * @param userUpdateReqView
     */
    public void updateUser(UserUpdateReqView userUpdateReqView);
}
