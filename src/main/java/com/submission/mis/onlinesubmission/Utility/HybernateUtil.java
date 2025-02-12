package com.submission.mis.onlinesubmission.Utility;

import java.util.Properties;

import com.submission.mis.onlinesubmission.Models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HybernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();

            settings.put(Environment.DRIVER, "org.postgresql.Driver");
//            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/rcamis");
//            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/rcamis");
            settings.put(Environment.USER, "postgres");
//            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "root");
//            settings.put(Environment.PASS, "");

            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            settings.put(Environment.SHOW_SQL, true);
            settings.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Teachers.class);
            configuration.addAnnotatedClass(Courses.class);
            configuration.addAnnotatedClass(Assignment.class);
            configuration.addAnnotatedClass(Submission.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}