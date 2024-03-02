package Controller.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogOutForm", value = "/LogOutForm")
public class LogOutForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Xóa đối tượng "customer" khỏi phiên làm việc
            session.setAttribute("customer", null);
            session.setAttribute("admin", null);
            session.removeAttribute("customer");

            // Hủy phiên làm việc
            session.invalidate();
        }

        String url =  request.getContextPath() + "/WEB-INF/book/logintwo.jsp";
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}