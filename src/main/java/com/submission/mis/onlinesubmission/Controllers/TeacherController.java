package com.submission.mis.onlinesubmission.Controllers;

import java.io.*;

import com.submission.mis.onlinesubmission.Models.Courses;
import com.submission.mis.onlinesubmission.Models.Teachers;
import com.submission.mis.onlinesubmission.Services.CourseAssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class TeacherController extends HttpServlet {
    CourseAssignmentService courseAssignmentService = CourseAssignmentService.getInstance();

    public void init() {
        String message = "Hello World! from teacher controller";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Teachers teacher = (Teachers) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            response.sendRedirect("WEB-INF/login.jsp");
            return;
        }

        response.setContentType("text/html");
        request.setAttribute("teacher", teacher);
        request.setAttribute("assignments", courseAssignmentService.getAllAssignments());
        request.getRequestDispatcher("WEB-INF/teacher.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("course");
        if (courseName == null) {
            request.setAttribute("error", "Please enter a course name");
            request.getRequestDispatcher("WEB-INF/createCourse.jsp").forward(request, response);
        }

        Courses course = new Courses();
        course.setName(courseName);
        courseAssignmentService.addCourse(course);
        request.setAttribute("course", course);
        response.sendRedirect(request.getContextPath() + "WEB-INF/teacher.jsp");

    }

    public void destroy() {
    }
}