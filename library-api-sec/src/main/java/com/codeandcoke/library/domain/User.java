package com.codeandcoke.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private String nif;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column
    private String address;
    @Column
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    @Column
    private String province;
    @Column
    private String country;
    @Column
    private String image;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Column(name = "active")
    private boolean active = true;
    @Transient
    private int age;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
