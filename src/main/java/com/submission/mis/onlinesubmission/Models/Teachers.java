package com.submission.mis.onlinesubmission.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "teachers")
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDate dateOfBirth;

    public Teachers() {}

    public Teachers(long id, String email, String username, String password, LocalDate dateOfBirth, String role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public Teachers(String email, String username, String password, LocalDate dateOfBirth, String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public Teachers(String email, String username, String password, String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }
}
