package Controller.User;

import Beans.ErrorBean;
import database.UserDAO;
import model.User;
import util.PasswordEncryption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println(username);
        String password = request.getParameter("password");
        System.out.println("pw"+password);
        String newPassword = request.getParameter("newPassword");

        password = PasswordEncryption.toSHA1(password);
        newPassword = PasswordEncryption.toSHA1(newPassword);
        UserDAO customerDAO = new UserDAO();
        User customer = customerDAO.selectByUsernamePassword(username, password);
        System.out.println(customer);
        String url="";
        if(customer!=null) {
            customer.setPassword(newPassword);
            customerDAO.update(customer);
            url = "/pizza-gh-pages/pizza-gh-pages/index.jsp";
            response.sendRedirect(request.getContextPath() + url);
        }else {
            request.setAttribute("Error", "mat khau chua chinh xac!");
            ErrorBean eb = new ErrorBean();
            eb.setError((String)request.getAttribute("Error"));
            request.setAttribute("errorBean", eb);
            url =  request.getContextPath() + "/WEB-INF/book/changePassword.jsp";
            response.sendRedirect(url + "?error=" + URLEncoder.encode(eb.getError(), "UTF-8"));
            return;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}