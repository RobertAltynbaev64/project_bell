package project.altynbaev.user.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserUpdateReqView {

    @NotNull(message = "id cannot be null")
    @Min(1)
    @ApiModelProperty(value = "Id пользователя", example = "1")
    public Long id;

    @Min(1)
    @ApiModelProperty(value = "Id офиса", example = "1")
    public Long officeId;

    @NotEmpty(message = "firstName cannot be null")
    @Size(min = 2, max = 15)
    @ApiModelProperty(value = "Имя пользователя", example = "Сергей")
    public String firstName;

    @Size(min = 2, max = 15)
    @ApiModelProperty(value = "Фамилия пользователя", example = "Смирнов")
    public String secondName;

    @Size(min = 2, max = 15)
    @ApiModelProperty(value = "Отчество пользователя", example = "Андреевич")
    public String middleName;

    @NotEmpty(message = "position cannot be null")
    @Size(min = 2, max = 20)
    @ApiModelProperty(value = "Должность пользователя", example = "Менеджер")
    public String position;

    @Size(min = 2, max = 18)
    @ApiModelProperty(value = "Телефон пользователя", example = "89179874125")
    public String phone;

    @Size(min = 2, max = 100)
    @ApiModelProperty(value = "Название документа", example = "Паспорт гражданина Российской Федерации")
    public String docName;

    @Size(min = 2, max = 20)
    @ApiModelProperty(value = "Номер документа", example = "459874")
    public String docNumber;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @ApiModelProperty(value = "Дата выдачи документа", example = "05.10.1998")
    public Date docDate;

    @Min(1)
    @ApiModelProperty(value = "Код страны гражданства", example = "643")
    public Integer citizenshipCode;

    @ApiModelProperty(value = "Определение пользователя", example = "true")
    public Boolean isIdentified;
}
