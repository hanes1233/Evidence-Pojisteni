package com.example.evidencepojisteni.models.dto;

import lombok.Data;

@Data
public class AddressDTO {

    /**
     * Class representing address object
     */

    private Long usersId;
    private String street;
    private int streetNum;
    private String city;
    private int psc;
    private String country;
}
