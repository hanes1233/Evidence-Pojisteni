package com.example.evidencepojisteni.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="LASTNAME")
    private String lastName;
    @Column(name="EMAIL")
    private String email;
    @Column(name="DATEOFBIRTH")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Column(name="ISADMIN")
    private boolean isAdmin;
    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;

    @Autowired
    public User(String name,
                String lastName,
                String email,
                Date dateOfBirth,
                boolean isAdmin,
                String username,
                String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
    }
    public User(String name,
                String lastName,
                String email,
                Date dateOfBirth,
                boolean isAdmin,
                String username) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.username = username;
    }


    @Autowired
    public User(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
