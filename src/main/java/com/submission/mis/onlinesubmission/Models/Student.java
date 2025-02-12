package com.submission.mis.onlinesubmission.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String username;
    private String email;
    private int age;
    private String password;
    private LocalDate birthDate;
    private String role;

    public Student() {}

    public Student(String username, int age, String email, LocalDate birthDate, int id, String password, String role) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.birthDate = birthDate;
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public Student(String username, String email, int age, LocalDate birthDate, String password, String role) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.birthDate = birthDate;
        this.password = password;
        this.role = role;
    }

    public Student(String username, String email, LocalDate birthDate, String password, String role) {
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.role = role;
        this.age = LocalDate.now().getYear() - birthDate.getYear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
}
