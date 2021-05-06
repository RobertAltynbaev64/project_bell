package project.altynbaev.organization.view;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationListReqView {
    @NotEmpty(message = "name cannot be null")
    @Size(min = 2, max = 50)
    @ApiModelProperty(value = "Название организации", example = "МТС")
    public String name;

    @Size(min = 10, max = 12)
    @ApiModelProperty(value = "ИНН организации", example = "7740000076")
    public String inn;

    @ApiModelProperty(value = "Активность организации", example = "true")
    public Boolean isActive;
}
