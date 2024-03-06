package Controller.User;

import Beans.ErrorBean;
import database.UserDAO;
import model.User;
import util.PasswordEncryption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginForm", value = "/LoginForm")
public class LoginForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = PasswordEncryption.toSHA1(password);
        boolean check_error = false;
        HttpSession session = request.getSession();

        session.setAttribute("username", username);

        //kiem tra password
        if (password == null || password.trim().length() == 0) {
            check_error = true;
            request.setAttribute("e_password", "chua nhap mat khau");
        }

        UserDAO test = new UserDAO();
        User user = test.selectByUsernamePassword(username, password);
        System.out.println("nguoi dung: " + username);

        String url = "";


        if (user != null) {


            if (user.getRole() == 1) {
                session.setAttribute("admin", user);
                url = "/WEB-INF/book/index.jsp";
                response.sendRedirect(request.getContextPath() + url);

            } else {

                if (user != null) {
                    session.setMaxInactiveInterval(30 * 60);
                    session.setAttribute("customer", user);
                    url = "/WEB-INF/book/index.jsp";


                }
            }


        } else {
            request.setAttribute("Error", "ten dang nhap hoac mat khau chua chinh xac!");
            ErrorBean eb = new ErrorBean();
            eb.setError((String) request.getAttribute("Error"));
            request.setAttribute("errorBean", eb);

            url = request.getContextPath() + "/WEB-INF/book/logintwo.jsp";
            String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
            request.setAttribute("encodedError", encodedError);
            return;
        }
        if (url != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);

        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("usernamelo");
        String password = request.getParameter("passwordlo");
        ErrorBean eb = new ErrorBean();
        String baoLoi1 = "";
        // Encrypt password (assuming proper encryption implemented elsewhere)
        password = PasswordEncryption.toSHA1(password);

        HttpSession session = request.getSession();

        // Check for login attempt
        if (username != null && password != null) {
            UserDAO userDAO = new UserDAO(); // Assuming UserDAO is instantiated correctly
            User user = userDAO.selectByUsernamePassword(username, password);

            if (user != null) {
                session.setAttribute("username", username);
                System.out.println(username);

                if (user.getRole() == 1) {
                    session.setAttribute("admin", user);
                    // Use sendRedirect for successful admin login
                    response.sendRedirect(request.getContextPath() + "/WEB-INF/book/index.jsp");
                } else {
                    session.setMaxInactiveInterval(30 * 60);
                    session.setAttribute("userC", user);
                    // Use forward for successful customer login
                    String url = "/WEB-INF/book/index.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
            } else {
                // Handle incorrect login credentials
                request.setAttribute("Error", "Tên đăng nhập hoặc mật khẩu chưa chính xác!");
                eb.setError((String) request.getAttribute("Error"));
                request.setAttribute("errorBean", eb);
                String url = request.getContextPath() + "/WEB-INF/book/logintwo.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
//        } else {
//            // User hasn't attempted login, indicate non-logged-in state
//            request.setAttribute("notLoggedIn", true);
//            // Forward to index.jsp to display information for non-logged-in users
//            String url = "/WEB-INF/book/index.jsp";
//            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//            dispatcher.forward(request, response);
//        }
    }
}