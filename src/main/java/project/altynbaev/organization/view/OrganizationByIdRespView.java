package project.altynbaev.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationByIdRespView {
    @ApiModelProperty(value = "Id организации", example = "1")
    public Long id;

    @ApiModelProperty(value = "Название организации", example = "1")
    public String name;

    @ApiModelProperty(value = "Полное название организации", example = "ПАО \"МТС\"")
    public String fullName;

    @ApiModelProperty(value = "ИНН организации", example = "7740000076")
    public String inn;

    @ApiModelProperty(value = "КПП организации", example = "770901001")
    public String kpp;

    @ApiModelProperty(value = "Адрес организации", example = "109147, г. Москва, Марксистская улица, 4")
    public String address;

    @ApiModelProperty(value = "Телефон организации", example = "8(800)250-08-90")
    public String phone;

    @ApiModelProperty(value = "Активность организации", example = "true")
    public Boolean isActive;

    public OrganizationByIdRespView() {
    }

    public OrganizationByIdRespView(Long id, String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
