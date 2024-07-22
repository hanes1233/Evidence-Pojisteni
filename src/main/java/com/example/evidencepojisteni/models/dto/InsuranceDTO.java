package com.example.evidencepojisteni.models.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class InsuranceDTO {

    /**
     * Class representing Insurance object
     */
    private Long id;
    private String type;
    private int amount;
    private String subject;
    private Date dateOfStart;
    private Date dateOfEnd;
    private Long userId;
}
