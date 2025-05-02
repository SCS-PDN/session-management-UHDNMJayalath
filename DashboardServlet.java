import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

// Make sure Course.java is available in your project
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    public static class Course {
        private String courseId;
        private String courseName;
        private String instructor;

        public Course(String courseId, String courseName, String instructor) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.instructor = instructor;
        }

        public String getCourseId() {
            return courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getInstructor() {
            return instructor;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // 2. Create a list of courses (hardcoded)
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSC2101", "Data Structures", "Dr. Silva"));
        courses.add(new Course("CSC2102", "Database Systems", "Dr. Perera"));
        courses.add(new Course("CSC2103", "Web Development", "Ms. Fernando"));

        // 3. Store courses in request attribute
        request.setAttribute("courses", courses);

        // 4. Forward to dashboard.jsp
        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);
    }
}
