package com.submission.mis.onlinesubmission.Controllers;

import java.io.*;
import java.util.List;

import com.submission.mis.onlinesubmission.Models.Submission;
import com.submission.mis.onlinesubmission.Services.CourseAssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletServlet", value = "/Servlet-servlet")
public class ViewSubmitted extends HttpServlet {
    CourseAssignmentService courseAssignmentService = CourseAssignmentService.getInstance();

    public void init() {
        String message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long assignmentId = Long.parseLong(request.getParameter("assignmentId"));

        // Fetch submissions for the specific assignment
        List<Submission> submissions = courseAssignmentService.getSubmissionsByAssignmentId(assignmentId);
        for (Submission submission : submissions) {
            System.out.println(submission.getFilePath() + " " + submission.getUsers().getUsername());
        }

        // Set the submissions as a request attribute
        request.setAttribute("submissions", submissions);
        request.setAttribute("assignmentId", assignmentId);

        // Forward to the JSP page that displays submissions
        request.getRequestDispatcher("WEB-INF/viewSubmissions.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void destroy() {
    }
}