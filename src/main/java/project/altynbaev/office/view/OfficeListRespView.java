package project.altynbaev.office.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeListRespView {
    @ApiModelProperty(value = "Id офиса", example = "1")
    public Long id;

    @ApiModelProperty(value = "Название офиса", example = "Салон-магазин МТС в ТРЦ Июнь")
    public String name;

    @ApiModelProperty(value = "Активность офиса", example = "true")
    public Boolean isActive;

    public OfficeListRespView(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
}
