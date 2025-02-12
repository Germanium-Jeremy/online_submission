package com.submission.mis.onlinesubmission.Services;

import com.submission.mis.onlinesubmission.Models.Student;
import com.submission.mis.onlinesubmission.Utility.HybernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentService {
    protected static SessionFactory sessionFactory= HybernateUtil.getSessionFactory();
    protected static Session session;
    protected static StudentService studentService;

    public static StudentService getInstance() {
        if (studentService == null) {
            studentService = new StudentService();
            return studentService;
        }
        return studentService;
    }

    private StudentService(){}

    public void addStudent(Student student) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
        session.close();
    }

    public List<Student> getAllStudents() {
        session = sessionFactory.openSession();
        List<Student> students = session.createQuery("from Student").list();
        session.close();
        return students;
    }

    public Student getStudent(int id) {
        session = sessionFactory.openSession();
        Student student = (Student) session.get(Student.class, id);
        session.close();
        return student;
    }

    public Student getStudentByEmail(String email) {
        session = sessionFactory.openSession();
        Student student = null;
        try {
            session.beginTransaction();
            student = (Student) session.createQuery("from Student where email = :email").setParameter("email", email).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return student;
    }
}