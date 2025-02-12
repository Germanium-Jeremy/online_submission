package com.submission.mis.onlinesubmission.Controllers;

import com.submission.mis.onlinesubmission.Models.Assignment;
import com.submission.mis.onlinesubmission.Models.Student;
import com.submission.mis.onlinesubmission.Models.Submission;
import com.submission.mis.onlinesubmission.Services.CourseAssignmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig
public class SubmitWorkServlet extends HttpServlet {
    CourseAssignmentService courseAssignmentService = CourseAssignmentService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        File uploadDir = new File("uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Path filePath = Paths.get(uploadDir.getAbsolutePath() + File.separator + System.currentTimeMillis() + "_" + fileName);
        try {
            Files.copy(filePart.getInputStream(), filePath);
        } catch (IOException e) {
            System.out.println("Error submitting file: " + e.getMessage());

            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File could not be uploaded");
            return;
        }

        Submission submission = new Submission();
        submission.setFilePath(filePath.toString());

        long assignmentId = Long.parseLong(req.getParameter("assignmentId"));
        Assignment assignment = courseAssignmentService.getAssignmentById((int) assignmentId);
        submission.setAssignment(assignment);

        Student student = (Student) req.getSession().getAttribute("student");
        submission.setUsers(student);

        courseAssignmentService.addSubmission(submission);
        resp.sendRedirect("studentDashboard");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String assignmentId = req.getParameter("workId");
        System.out.println(assignmentId);
        if (assignmentId != null) {
            Assignment assignment = courseAssignmentService.getAssignmentById((int) Long.parseLong(assignmentId));
            req.setAttribute("assignment", assignment);
            req.setAttribute("formName", "Submit Assignment");
            req.getRequestDispatcher("WEB-INF/fileSubmission.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("WEB-INF/student.jsp");
        }
    }
}
