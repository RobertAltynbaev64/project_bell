package project.altynbaev.office.model;

import project.altynbaev.user.model.User;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность офис
 */
@Entity(name = "Office")
public class Office {
    /**
     * Id офиса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Id организации
     */
    @Column(name = "organization_id", nullable = false)
    private Long orgId;

    /**
     * Название офиса
     */
    @Column(name = "name", length = 40)
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address", length = 100)
    private String address;

    /**
     * Телефон офиса
     */
    @Column(name = "phone", length = 18)
    private String phone;

    /**
     * Активность офиса
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "office_id")
    private List<User> users;

    public Office() {
    }

    public Office(Long orgId, String name, String address, String phone, Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
