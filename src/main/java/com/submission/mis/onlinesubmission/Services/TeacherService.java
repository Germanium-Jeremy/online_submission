package com.submission.mis.onlinesubmission.Services;

import com.submission.mis.onlinesubmission.Models.Teachers;
import com.submission.mis.onlinesubmission.Utility.HybernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TeacherService {
    protected static SessionFactory sessionFactory = HybernateUtil.getSessionFactory();
    protected static Session session;
    protected static TeacherService teacherService;

    public static TeacherService getInstance() {
        if (teacherService == null) {
            teacherService = new TeacherService();
        }
        return teacherService;
    }

    private TeacherService() {}

    public void addTeacher(Teachers teacher) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(teacher);
        session.getTransaction().commit();
        session.close();
    }

    public List<Teachers> getTeachers() {
        session = sessionFactory.openSession();
        List<Teachers> teachers = session.createQuery("from Teachers").list();
        teachers.forEach(teacher -> {
            teacher.setPassword(null);
        });
        session.close();
        return teachers;
    }

    public Teachers getTeacher(int id) {
        session = sessionFactory.openSession();
        Teachers teacher = (Teachers) session.get(Teachers.class, id);
        session.close();
        return teacher;
    }

    public Teachers getTeacherByEmail(String email) {
        session = sessionFactory.openSession();
        Teachers teacher = null;
        try {
            session.beginTransaction();
            teacher = (Teachers) session.createQuery("from Teachers where email = :email").setParameter("email", email).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return teacher;
    }
}
