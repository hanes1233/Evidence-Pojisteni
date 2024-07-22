package com.example.evidencepojisteni.models.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class UserDTO {

    /**
     * Class representing User object
     */
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private boolean isAdmin;
    private String username;
    private String password;

}
