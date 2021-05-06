package project.altynbaev.user.view;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserListReqView {

    @NotNull(message = "name cannot be null")
    @Min(1)
    @ApiModelProperty(value = "Id офиса", example = "1")
    public Long officeId;

    @Size(min = 2, max = 15)
    @ApiModelProperty(value = "Имя пользователя", example = "Сергей")
    public String firstName;

    @Size(min = 2, max = 15)
    @ApiModelProperty(value = "Фамилия пользователя", example = "Смирнов")
    public String secondName;

    @Size(min = 2, max = 15)
    @ApiModelProperty(value = "Отчество пользователя", example = "Андреевич")
    public String middleName;

    @Size(min = 2, max = 20)
    @ApiModelProperty(value = "Должность пользователя", example = "Менеджер")
    public String position;

    @Min(1)
    @ApiModelProperty(value = "Код документа", example = "3")
    public Integer docCode;

    @Min(1)
    @ApiModelProperty(value = "Код страны гражданства", example = "643")
    public Integer citizenshipCode;
}
