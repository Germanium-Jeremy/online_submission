package com.submission.mis.onlinesubmission.Models;

import jakarta.persistence.*;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Student student;

    public Submission() {}

    public Submission(long id, Assignment assignment, String filePath, Student student) {
        this.id = id;
        this.assignment = assignment;
        this.filePath = filePath;
        this.student = student;
    }

    public Submission(Assignment assignment, String filePath, Student users) {
        this.assignment = assignment;
        this.filePath = filePath;
        this.student = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Student getUsers() {
        return student;
    }

    public void setUsers(Student users) {
        this.student = users;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
