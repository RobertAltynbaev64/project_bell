package project.altynbaev.user.view;

import io.swagger.annotations.ApiModelProperty;

public class UserListRespView {

    @ApiModelProperty(value = "Id пользователя", example = "1")
    public Long id;

    @ApiModelProperty(value = "Имя пользователя", example = "Сергей")
    public String firstName;

    @ApiModelProperty(value = "Фамилия пользователя", example = "Смирнов")
    public String secondName;

    @ApiModelProperty(value = "Отчество пользователя", example = "Андреевич")
    public String middleName;

    @ApiModelProperty(value = "Должность пользователя", example = "Менеджер")
    public String position;

    public UserListRespView(Long id, String firstName, String secondName, String middleName, String position) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
    }
}
