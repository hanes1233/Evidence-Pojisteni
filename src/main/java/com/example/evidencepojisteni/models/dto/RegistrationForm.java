package com.example.evidencepojisteni.models.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class RegistrationForm {

    /**
     * Class representing two DTO objects inside - UserDTO and AddressDTO
     * It's made to comfortably work with object in forms (register new user together with his address,
     * updateAndSave users info).
     */

    @Autowired
    public UserDTO userDTO;
    @Autowired
    public AddressDTO addressDTO;
}
