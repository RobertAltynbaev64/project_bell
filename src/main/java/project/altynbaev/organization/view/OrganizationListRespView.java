package project.altynbaev.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationListRespView {
    @ApiModelProperty(value = "Id организации", example = "1")
    public Long id;

    @ApiModelProperty(value = "Название организации", example = "МТС")
    public String name;

    @ApiModelProperty(value = "Активность организации", example = "true")
    public Boolean isActive;

    public OrganizationListRespView() {
    }

    public OrganizationListRespView(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
}
