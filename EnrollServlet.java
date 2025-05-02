import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get courseId from URL
        String courseId = request.getParameter("courseId");

        // 2. Get session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // 3. Get enrolled course list from session
        @SuppressWarnings("unchecked")
        List<String> enrolledCourses = (List<String>) session.getAttribute("enrolledCourses");

        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }

        // Avoid duplicate enrollment
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }

        // 4. Store updated list back in session
        session.setAttribute("enrolledCourses", enrolledCourses);

        // 5. Redirect back to dashboard
        response.sendRedirect("DashboardServlet");
    }
}
