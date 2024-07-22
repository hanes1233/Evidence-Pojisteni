package com.example.evidencepojisteni.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table (name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="USER_ID")
    private Long usersId;
    @Column(name="STREET")
    private String street;
    @Column(name="STREETNUM")
    private int streetNum;
    @Column(name="CITY")
    private String city;
    @Column(name="PSC")
    private int psc;
    @Column(name="COUNTRY")
    private String country;

    public Address(Long usersId, String street, int streetNum , String city, int psc, String country) {
        this.usersId = usersId;
        this.street = street;
        this.streetNum = streetNum;
        this.city = city;
        this.psc = psc;
        this.country = country;
    }

    public Address(String street, int streetNum, String city,int psc, String country) {
        this.street = street;
        this.streetNum = streetNum;
        this.city = city;
        this.psc = psc;
        this.country = country;
    }
}
