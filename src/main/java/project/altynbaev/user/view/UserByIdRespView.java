package project.altynbaev.user.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserByIdRespView {
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

    @ApiModelProperty(value = "Телефон пользователя", example = "89174568987")
    public String phone;

    @ApiModelProperty(value = "Название документа", example = "Паспорт гражданина Российской Федерации")
    public String docName;

    @ApiModelProperty(value = "Номер документа", example = "459874")
    public String docNumber;

    @JsonFormat(pattern="dd.MM.yyyy")
    @ApiModelProperty(value = "Дата выдачи документа", example = "05.10.1998")
    public Date docDate;

    @ApiModelProperty(value = "Название страны гражданства", example = "Россия")
    public String citizenshipName;

    @ApiModelProperty(value = "Код страны гражданства", example = "643")
    public Integer citizenshipCode;

    @ApiModelProperty(value = "Определение пользователя", example = "true")
    public Boolean isIdentified;

    public UserByIdRespView() {
    }

    public UserByIdRespView(Long id, String firstName, String secondName, String middleName, String position, String phone, String docName, String docNumber, Date docDate, String citizenshipName, Integer citizenshipCode, Boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipName = citizenshipName;
        this.citizenshipCode = citizenshipCode;
        this.isIdentified = isIdentified;
    }
}
