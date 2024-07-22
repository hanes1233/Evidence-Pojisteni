package com.example.evidencepojisteni.models.services;

public enum Countries {

    Czech_Republic("Česká republica"),
    Slovakia("Slovensko"),
    Ukraine("Ukrajina"),
    Russia("Rusko"),
    Vietnam("Vietnam"),
    Switzerland("Švýcarsko"),
    Norway("Norsko"),
    UAE("Spojené Arabské Emiráty"),
    OtherEU("Jiný stát EU"),
    OtherThird("Jiný stát třetí země"),
    NorthAmerica("Severní Amerika"),
    Other("Jiný");


    public String country;

    Countries(String country) {
        this.country = country;
    }


}
