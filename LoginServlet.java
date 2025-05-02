import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // Hardcoded user list
    private static final Map<String, String> users = new HashMap<>();

    @Override
    public void init() throws ServletException {
        users.put("student1", "pass1");
        users.put("student2", "pass2");
        users.put("admin", "admin123");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate credentials
        if (users.containsKey(username) && users.get(username).equals(password)) {

            // 1. Create session and store username
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 2. Create cookie
            Cookie userCookie = new Cookie("user", username);
            userCookie.setMaxAge(60 * 60); // 1 hour
            response.addCookie(userCookie);

            // 3. Redirect to DashboardServlet
            response.sendRedirect("DashboardServlet");

        } else {
            // Invalid login → redirect back to login.html
            response.sendRedirect("login.html");
        }
    }
}
