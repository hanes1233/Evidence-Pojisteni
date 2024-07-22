package com.example.evidencepojisteni.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "insurred")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="TYPE")
    private String type;
    @Column(name="AMOUNT")
    private int amount;
    @Column(name="SUBJECT")
    private String subject;
    @Column(name="BEGGINING")
    private Date dateOfStart;
    @Column(name="ENDDATE")
    private Date dateOfEnd;
    @Column(name="USER_ID")
    private Long userId;


    public Insurance(String type, int amount, String subject, Date dateOfStart, Date dateOfEnd, Long userId) {
        this.type = type;
        this.amount = amount;
        this.subject = subject;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.userId = userId;
    }
}
