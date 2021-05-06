package project.altynbaev.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.altynbaev.exception.BadRequestException;
import project.altynbaev.user.service.UserService;
import project.altynbaev.user.view.*;
import project.altynbaev.view.ResultView;

import javax.validation.Valid;
import java.util.List;

@Api(value = "UserController")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Получить пользователей офиса по фильтру", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserListRespView.class)
    })
    @PostMapping("/list")
    public List<UserListRespView> getList(@Valid @RequestBody UserListReqView userListReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            return userService.getUserByFilter(userListReqView);
        }
    }

    @ApiOperation(value = "Получить пользователя офиса по id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserByIdRespView.class)
    })
    @GetMapping("/{id:[\\d]+}")
    public UserByIdRespView getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "Добавить пользователя", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResultView.class)
    })
    @PostMapping("/save")
    public void save(@Valid @RequestBody UserSaveReqView userSaveReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            userService.saveUser(userSaveReqView);
        }
    }

    @ApiOperation(value = "Обновить пользователя", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResultView.class)
    })
    @PostMapping("/update")
    public void update(@Valid @RequestBody UserUpdateReqView userUpdateReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            userService.updateUser(userUpdateReqView);
        }
    }
}
