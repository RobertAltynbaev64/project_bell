package project.altynbaev.dto.organization;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationUpdateAndGetDto {

    @NotNull
    private int id;

    @Size(max = 40)
    @NotBlank
    private String name;

    @Size(max = 100)
    @NotBlank
    private String fullName;

    @Size(max = 10)
    @NotNull
    private Long inn;

    @Size(max = 9)
    @NotNull
    private Long kpp;

    @Size(max = 100)
    @NotBlank
    private String address;

    private Long phone;

    private Boolean isActive;

    public OrganizationUpdateAndGetDto(int id, String name, String fullName, Long inn, Long kpp, String address, Long phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OrganizationUpdateAndGetDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
