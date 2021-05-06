package project.altynbaev.office.view;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfficeListReqView {
    @NotNull(message = "organization_id cannot be null")
    @Min(1)
    @ApiModelProperty(value = "Id организации", example = "1")
    public Long orgId;

    @Size(min = 2, max = 50)
    @ApiModelProperty(value = "Название офиса", example = "Салон-магазин МТС в ТРЦ Июнь")
    public String name;

    @Size(min = 10, max = 12)
    @ApiModelProperty(value = "Телефон офиса", example = "89178745639")
    public String phone;

    @ApiModelProperty(value = "Активность офиса", example = "true")
    public Boolean isActive;
}
