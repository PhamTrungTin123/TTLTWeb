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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginForm", value = "/LoginForm")
public class LoginForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = PasswordEncryption.toSHA1(password);
        boolean check_error=false;

        //kiem tra password
        if(password == null || password.trim().length()==0){
            check_error = true;
            request.setAttribute("e_password", "chua nhap mat khau");
        }

        UserDAO test =  new UserDAO();
        User user = test.selectByUsernamePassword(username, password);
        System.out.println("nguoi dung: "+ username);

        String url="";


        if(user!=null) {


            if(user.getRole()==1) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", user);
                url = "/WEB-INF/book/index.jsp";
                response.sendRedirect(request.getContextPath() + url);

            }else {

                if(user!=null) {
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(30*60);
                    session.setAttribute("customer", user);
                    url = "/WEB-INF/book/index.jsp";
                    response.sendRedirect(request.getContextPath() + url);


                }
            }


        }else {
            request.setAttribute("Error", "ten dang nhap hoac mat khau chua chinh xac!");
            ErrorBean eb = new ErrorBean();
            eb.setError((String)request.getAttribute("Error"));
            request.setAttribute("errorBean", eb);

            url =  request.getContextPath() + "/WEB-INF/book/logintwo.jsp";
            response.sendRedirect(url + "?error=" + URLEncoder.encode(eb.getError(), "UTF-8"));
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}