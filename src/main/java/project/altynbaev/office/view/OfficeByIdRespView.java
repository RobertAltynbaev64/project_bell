package project.altynbaev.office.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeByIdRespView {
    @ApiModelProperty(value = "Id офиса", example = "1")
    public Long id;

    @ApiModelProperty(value = "Название офиса", example = "Салон-магазин МТС в ТРЦ Июнь")
    public String name;

    @ApiModelProperty(value = "Адрес офиса", example = "Уфа, ул. Комсомольская, 112")
    public String address;

    @ApiModelProperty(value = "Телефон офиса", example = "89178745639")
    public String phone;

    @ApiModelProperty(value = "Активность офиса", example = "true")
    public Boolean isActive;

    public OfficeByIdRespView(Long id, String name, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
