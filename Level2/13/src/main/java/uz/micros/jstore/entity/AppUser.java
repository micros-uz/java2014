package uz.micros.jstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class AppUser {
    public static final int ROLE_ADMIN = 0x01;
    public static final int ROLE_USER = 0x02;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    public AppUser() {
        type = ROLE_USER;
    }

    public AppUser(String firstName, String lastName, String userName, String password, String email, Integer type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}