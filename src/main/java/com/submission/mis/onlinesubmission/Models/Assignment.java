package com.submission.mis.onlinesubmission.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "teachers_id")
    private Teachers teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    public Teachers getTeachers() {
        return teacher;
    }

    public void setTeachers(Teachers teachers) {
        this.teacher = teachers;
    }

    public Assignment() {}

    public Assignment(long id, String title, LocalDateTime deadLine, Courses courses, Teachers teachers) {
        this.id = id;
        this.title = title;
        this.courses = courses;
        this.teacher = teachers;
    }

    public Assignment(String title, Courses course, Teachers teachers) {
        this.title = title;
        this.courses = course;
        this.teacher = teachers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
}
