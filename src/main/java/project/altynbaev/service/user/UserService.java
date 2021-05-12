package project.altynbaev.service.user;

import project.altynbaev.dto.user.*;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    UserGetDto findById(int id);

    void save(@Valid UserSaveDto userSaveDTO);

    void update(@Valid UserUpdateDto userUpdateDTO);

    List<UserFilterOutDto> filter(@Valid UserFilterInDto userFilterInDTO);

}
