package project.altynbaev.dto.organization;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OrganizationFilterInDto {

    @Size(max = 40)
    @NotBlank
    private String name;

    private Long inn;

    private Boolean isActive;

    public OrganizationFilterInDto(String name, Long inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public OrganizationFilterInDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
