package com.submission.mis.onlinesubmission.Controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.submission.mis.onlinesubmission.Models.Student;
import com.submission.mis.onlinesubmission.Models.Teachers;
import com.submission.mis.onlinesubmission.Services.StudentService;
import com.submission.mis.onlinesubmission.Services.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class RegisterController extends HttpServlet {
    TeacherService teacherService = TeacherService.getInstance();
    StudentService studentService = StudentService.getInstance();

    public void init() {
        String message = "Hello World! from register";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("formName", "Student Registration Form");
        request.getRequestDispatcher("WEB-INF/registerT.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        if (role.isBlank() || role == null || role.isEmpty()) {
            request.setAttribute("error", "Role is required");
            request.getRequestDispatcher("WEB-INF/registerT.jsp").forward(request, response);
        }

        String username = request.getParameter("username");
        if (username == null || username.isEmpty()) {
            request.setAttribute("error", "Username is required");
            request.getRequestDispatcher("WEB-INF/registerT.jsp").forward(request, response);
        }

        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            request.setAttribute("error", "Password is required");
            request.getRequestDispatcher("WEB-INF/registerT.jsp").forward(request, response);
        }

        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            request.setAttribute("error", "Email is required");
            request.getRequestDispatcher("WEB-INF/registerT.jsp").forward(request, response);
        }

        LocalDate birthday = LocalDate.parse(request.getParameter("birthdate"));
        if (birthday.isAfter(LocalDate.now())) {
            request.setAttribute("error", "The date is not yet reached");
            request.getRequestDispatcher("WEB-INF/registerT.jsp").forward(request, response);
        }

        if (role.equals("Teacher")) {
            Teachers teacher = new Teachers(email, username, password, birthday, role);
            teacherService.addTeacher(teacher);
            request.getSession().setAttribute("teacher", teacher);
            response.sendRedirect(request.getContextPath() + "/mainDashboard");
        } else if (role.equals("Student")) {
            Student student = new Student(username, email, birthday, password, role);
            studentService.addStudent(student);
            request.getSession().setAttribute("student", student);
            response.sendRedirect(request.getContextPath() + "/studentDashboard");
        } else {
            request.setAttribute("error", "Unable to create account");
            request.getRequestDispatcher("WEB-INF/registerT.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}