package com.submission.mis.onlinesubmission.Controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.submission.mis.onlinesubmission.Models.Student;
import com.submission.mis.onlinesubmission.Services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class ServletController extends HttpServlet {
    StudentService studentService = StudentService.getInstance();
    private String message;

    public void init() {
        message = "Student Registration";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("formName", "Student Registration Form");
        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("WEB-INF/form.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String password = request.getParameter("password");

        Student student = new Student(firstName, lastName, dateOfBirth, email, password);
        studentService.addStudent(student);
        doGet(request, response);
    }

    public void destroy() {
    }
}