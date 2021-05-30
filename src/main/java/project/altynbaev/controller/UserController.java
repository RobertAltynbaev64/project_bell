package project.altynbaev.controller;

import project.altynbaev.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.altynbaev.dto.user.*;
import project.altynbaev.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("list")
    public List<UserFilterOutDto> filter(@RequestBody @Valid UserFilterInDto userFilterInDTO) {
        return userService.filter(userFilterInDTO);
    }

    @GetMapping("{id}")
    public UserGetDto getUser(@PathVariable int id) {
        if (userService.findById(id) != null) {
            return userService.findById(id);
        } else throw new NotFoundException("Не найден пользователь с id = " + id);
    }

    @PostMapping("save")
    public void saveUser(@RequestBody @Valid UserSaveDto userSaveDTO) {
        userService.save(userSaveDTO);
    }

    @PostMapping("update")
    public void updateUser(@RequestBody @Valid UserUpdateDto userUpdateDTO) {
        userService.update(userUpdateDTO);
    }
}
