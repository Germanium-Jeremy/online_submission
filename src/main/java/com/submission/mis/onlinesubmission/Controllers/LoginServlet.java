package com.submission.mis.onlinesubmission.Controllers;

import com.submission.mis.onlinesubmission.Models.Student;
import com.submission.mis.onlinesubmission.Models.Teachers;
import com.submission.mis.onlinesubmission.Models.Users;
import com.submission.mis.onlinesubmission.Services.AuthenticationService;
import com.submission.mis.onlinesubmission.Services.StudentService;
import com.submission.mis.onlinesubmission.Services.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private AuthenticationService authenticationService = AuthenticationService.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String loginResult = authenticationService.login(email, password);

        if ("Login successful as student".equals(loginResult)) {
            StudentService studentService = StudentService.getInstance();
            Student student = studentService.getStudentByEmail(email);
            request.getSession().setAttribute("student", student);
            response.sendRedirect(request.getContextPath() + "/studentDashboard");
        } else if ("Login successful as teacher".equals(loginResult)) {
            TeacherService teacherService = TeacherService.getInstance();
            Teachers teacher = teacherService.getTeacherByEmail(email);
            request.getSession().setAttribute("teacher", teacher);
            response.sendRedirect(request.getContextPath() + "/mainDashboard");
        } else {
            request.setAttribute("error", loginResult);
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("formName", "Login Form");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }
}
