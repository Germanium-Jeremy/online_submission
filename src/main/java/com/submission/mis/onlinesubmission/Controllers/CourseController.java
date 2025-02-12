package com.submission.mis.onlinesubmission.Controllers;

import java.io.*;

import com.submission.mis.onlinesubmission.Models.Courses;
import com.submission.mis.onlinesubmission.Models.Teachers;
import com.submission.mis.onlinesubmission.Services.CourseAssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletServlet", value = "/Servlet-servlet")
public class CourseController extends HttpServlet {
    CourseAssignmentService courseAssignmentService = CourseAssignmentService.getInstance();

    public void init() {
        String message = "Hello World! from course controller";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("formName", "Add course");
        request.getRequestDispatcher("/WEB-INF/createCourse.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("course");
        if (courseName == null || courseName.trim().isEmpty()) {
            request.setAttribute("error", "Please enter a course name");
            request.getRequestDispatcher("/WEB-INF/createCourse.jsp").forward(request, response);
        }

        Courses course = new Courses();
        course.setName(courseName);
        courseAssignmentService.addCourse(course);
        request.setAttribute("course", course);
        response.sendRedirect(request.getContextPath() + "/mainDashboard");
    }

    public void destroy() {
    }
}