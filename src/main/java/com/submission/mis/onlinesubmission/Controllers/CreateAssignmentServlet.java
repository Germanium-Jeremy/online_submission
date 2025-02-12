package com.submission.mis.onlinesubmission.Controllers;

import com.submission.mis.onlinesubmission.Models.Assignment;
import com.submission.mis.onlinesubmission.Models.Courses;
import com.submission.mis.onlinesubmission.Models.Teachers;
import com.submission.mis.onlinesubmission.Services.CourseAssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreateAssignmentServlet extends HttpServlet {
    CourseAssignmentService courseAssignmentService = CourseAssignmentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            request.setAttribute("error", "Please enter a Assignment description");
            request.getRequestDispatcher("/WEB-INF/createAssignment.jsp").forward(request, response);
        }

        String courseId = request.getParameter("courseId");
        if (courseId == null || courseId.trim().isEmpty()) {
            request.setAttribute("error", "Please select a course");
            request.getRequestDispatcher("/WEB-INF/createAssignment.jsp").forward(request, response);
        }

        Courses courses = courseAssignmentService.getCourseById(Integer.parseInt(courseId));
        Teachers teacher = (Teachers) request.getSession().getAttribute("teacher");
        Assignment assignment = new Assignment(title, courses, teacher);
        courseAssignmentService.addAssignment(assignment);
        response.sendRedirect(request.getContextPath() + "/mainDashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("courseList", courseAssignmentService.getAllCourses());
        request.setAttribute("formName", "Create Assignment");
        request.getRequestDispatcher("/WEB-INF/createAssignment.jsp").forward(request, response);
    }
}
