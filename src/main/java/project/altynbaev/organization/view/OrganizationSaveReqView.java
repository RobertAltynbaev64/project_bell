package project.altynbaev.organization.view;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationSaveReqView {
    @NotEmpty(message = "name cannot be null")
    @Size(min = 2, max = 50)
    @ApiModelProperty(value = "Название организации", example = "1")
    public String name;

    @NotEmpty(message = "fullName cannot be null")
    @Size(min = 2, max = 100)
    @ApiModelProperty(value = "Полное название организации", example = "ПАО \"МТС\"")
    public String fullName;

    @NotEmpty(message = "inn cannot be null")
    @Size(min = 10, max = 12)
    @ApiModelProperty(value = "ИНН организации", example = "7740000076")
    public String inn;

    @NotEmpty(message = "kpp cannot be null")
    @Size(min = 9, max = 9)
    @ApiModelProperty(value = "КПП организации", example = "770901001")
    public String kpp;

    @NotEmpty(message = "address cannot be null")
    @Size(min = 2, max = 100)
    @ApiModelProperty(value = "Адрес организации", example = "109147, г. Москва, Марксистская улица, 4")
    public String address;

    @Size(min = 11, max = 18)
    @ApiModelProperty(value = "Телефон организации", example = "8(800)250-08-90")
    public String phone;

    @ApiModelProperty(value = "Активность организации", example = "true")
    public Boolean isActive;
}
