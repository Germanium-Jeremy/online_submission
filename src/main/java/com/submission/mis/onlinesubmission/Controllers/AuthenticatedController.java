package com.submission.mis.onlinesubmission.Controllers;

import java.io.*;

import com.submission.mis.onlinesubmission.Models.Student;
import com.submission.mis.onlinesubmission.Services.CourseAssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class AuthenticatedController extends HttpServlet {
    CourseAssignmentService courseAssignmentService = CourseAssignmentService.getInstance();

    public void init() {
        String message = "Hello World! from studentAuthenticatedController";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Student student = (Student) request.getSession().getAttribute("student");
        if (student == null) {
            response.sendRedirect("WEB-INF/login.jsp");
            return;
        }

        response.setContentType("text/html");
        request.setAttribute("student", student);
        request.setAttribute("assignment", courseAssignmentService.getAllAssignments());
        request.getRequestDispatcher("WEB-INF/student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void destroy() {
    }
}