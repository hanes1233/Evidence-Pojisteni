package com.example.evidencepojisteni.models.services;

public enum InsuranceType {
    CAR("Povinné ručení"),
    LIFE("Pojištění životů"),
    PET("Pojištění domácích mazličků"),
    PROPERTY("Pojištění nemovitosti"),
    TRAVEL("Cestovní pojištění");

    public String type;

     InsuranceType(String type) {
        this.type = type;
    }
}
