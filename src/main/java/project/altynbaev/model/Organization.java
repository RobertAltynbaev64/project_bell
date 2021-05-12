package project.altynbaev.model;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private Integer version = 0;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "inn", length = 10, nullable = false)
    private Long inn;

    @Column(name = "kpp", length = 9, nullable = false)
    private Long kpp;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "phone", length = 11)
    private Long phone;

    @Column(name = "is_active")
    private Boolean isActive;

    public Organization(int id, String name, String fullName, Long inn, Long kpp, String address, Long phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Organization() {
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
