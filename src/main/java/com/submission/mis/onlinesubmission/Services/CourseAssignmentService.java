package com.submission.mis.onlinesubmission.Services;

import com.submission.mis.onlinesubmission.Models.Assignment;
import com.submission.mis.onlinesubmission.Models.Courses;
import com.submission.mis.onlinesubmission.Models.Submission;
import com.submission.mis.onlinesubmission.Utility.HybernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CourseAssignmentService {
    protected static SessionFactory sessionFactory = HybernateUtil.getSessionFactory();
    protected static Session session;
    protected static CourseAssignmentService courseAssignmentService;

    public static CourseAssignmentService getInstance() {
        if (courseAssignmentService == null) {
            courseAssignmentService = new CourseAssignmentService();
        }
        return courseAssignmentService;
    }

    public CourseAssignmentService() {}

    public void addCourse(Courses course) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(course);
        session.getTransaction().commit();
        session.close();
    }

    public List<Courses> getAllCourses() {
        session = sessionFactory.openSession();
        List<Courses> coursesList = session.createQuery("from Courses").list();
        session.close();
        return coursesList;
    }

    public Courses getCourseById(int id) {
        session = sessionFactory.openSession();
        Courses course = session.get(Courses.class, id);
        session.close();
        return course;
    }


    public void addAssignment(Assignment assignment) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(assignment);
        session.getTransaction().commit();
        session.close();
    }

    public List<Assignment> getAllAssignments() {
        session = sessionFactory.openSession();
        List<Assignment> assignmentList = session.createQuery("from Assignment").list();
        session.close();
        return assignmentList;
    }

    public Assignment getAssignmentById(int id) {
        session = sessionFactory.openSession();
        Assignment assignment = session.get(Assignment.class, id);
        session.close();
        return assignment;
    }

    public void addSubmission(Submission submission) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(submission);
        session.getTransaction().commit();
        session.close();
    }

    public List<Submission> getAllSubmissions() {
        session = sessionFactory.openSession();
        List<Submission> submissionList = session.createQuery("from Submission").list();
        session.close();
        return submissionList;
    }

    public Submission getSubmissionById(int id) {
        session = sessionFactory.openSession();
        Submission submission = session.get(Submission.class, id);
        session.close();
        return submission;
    }

    public List<Submission> getSubmissionsByAssignmentId(long assignmentId) {
        List<Submission> submissions = null;

        try {
            session = sessionFactory.openSession();
            String hql = "from Submission s where s.assignment.id = :assignmentId";
            Query query = session.createQuery(hql, Submission.class);

            query.setParameter("assignmentId", assignmentId);
            submissions = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return submissions;
    }
}
