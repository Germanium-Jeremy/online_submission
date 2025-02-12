package com.submission.mis.onlinesubmission.Services;

import com.submission.mis.onlinesubmission.Models.Student;
import com.submission.mis.onlinesubmission.Models.Teachers;
import com.submission.mis.onlinesubmission.Utility.HybernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AuthenticationService {
    protected static SessionFactory sessionFactory = HybernateUtil.getSessionFactory();
    protected static Session session;
    protected static AuthenticationService authenticationService;

    public static AuthenticationService getInstance() {
        if (authenticationService == null) {
            authenticationService = new AuthenticationService();
        }
        return authenticationService;
    }

    private AuthenticationService() {}

    public String login(String email, String password) {
        session = sessionFactory.openSession();
        String result = null;

        try {
            session.beginTransaction();

            Teachers teachers = session.createQuery("FROM Teachers WHERE email = :email", Teachers.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (teachers != null) {
                if (teachers.getPassword().equals(password)) {
                    result = "Login successful as teacher";
                } else {
                    result = "Invalid password";
                }
            } else {
                Student student = session.createQuery("FROM Student WHERE email = :email", Student.class)
                    .setParameter("email", email)
                    .uniqueResult();
                if (student != null) {
                    if (student.getPassword().equals(password)) {
                        result = "Login successful as student";
                    } else {
                        result = "Invalid password";
                    }
                } else {
                    result = "Invalid email";
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            result = "An error occurred" + e.getMessage();
        } finally {
            session.close();
        }
        return result;
    }
}
