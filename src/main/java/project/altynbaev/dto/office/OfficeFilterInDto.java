package project.altynbaev.dto.office;

import javax.validation.constraints.NotNull;

public class OfficeFilterInDto {

    @NotNull
    private int orgId;

    private String name;

    private Long phone;

    private Boolean isActive;

    public OfficeFilterInDto(int orgId, String name, Long phone, Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OfficeFilterInDto() {
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
