package com.ironhack.ivanbank.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String roles;

    private Boolean isAccountNonLocked;

    @CreationTimestamp
    private Instant creationDate;

    @UpdateTimestamp
    private Instant lastUpdateDate;

    @OneToOne
    private AccountHolder owner;

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        isAccountNonLocked = true;
    }

    public User(String username, String password, String roles, AccountHolder owner) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.owner = owner;
        isAccountNonLocked = true;
    }

    public User(){
        isAccountNonLocked = true;
    }
}
