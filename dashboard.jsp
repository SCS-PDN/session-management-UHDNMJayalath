<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Dashboard</title>
</head>
<body>
    <h1>Welcome, ${sessionScope.username}!</h1>
    
    <form action="LogoutServlet" method="GET">
        <input type="submit" value="Logout">
    </form>
    
    <h2>Available Courses</h2>
    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.courseId}</td>
                <td>${course.courseName}</td>
                <td>${course.instructor}</td>
                <td><a href="EnrollServlet?courseId=${course.courseId}">Enroll</a></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Your Enrolled Courses</h2>
    <ul>
        <c:forEach items="${sessionScope.enrolledCourses}" var="course">
            <li>${course.courseName} (${course.courseId})</li>
        </c:forEach>
    </ul>
</body>
</html>
