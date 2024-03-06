package Controller.User;

import Beans.ErrorBean;
import database.UserDAO;
import model.User;
import util.Email;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "RegisterForm", value = "/RegisterForm")
public class RegisterForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*  try {
            String username = request.getParameter("usernamere");
            String name = request.getParameter("namere");
            String email = request.getParameter("emailre");
            String password = request.getParameter("passwordre");
            String enterThePassword = request.getParameter("enterThePasswordre");
            UserDAO cd = new UserDAO();
            ErrorBean eb = new ErrorBean();
            String baoLoi1 = "";
            String baoLoi2 = "";
            String baoloi3 = "";
            System.out.println(username);
            String url = "";
        *//*request.setAttribute("username", username);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("enterThePassword", enterThePassword);*//*
            if (cd.selectByUsername(username)) {
                eb.setError("ten dang nhap da ton tai, vui long chon ten dang nhap khac");
                *//* request.setAttribute("name", "");*//*
                request.setAttribute("errorBean", eb);
                baoLoi1 += eb.getError();

                url = "/WEB-INF/book/register.jsp";
                String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
                request.setAttribute("encodedError", encodedError);

                return;

            } else if (cd.selectByEmail(email)) {
                eb.setError("ten email da ton tai, vui long chon email khac");
                *//*request.setAttribute("name", "");*//*
                request.setAttribute("errorBean", eb);
                baoLoi2 += eb.getError();
                url = "/WEB-INF/book/register.jsp";
                String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
                request.setAttribute("encodedError", encodedError);

                return;

            } else if (password != null && !password.equals(enterThePassword)) {
                eb.setError(" mat khau nhap lai khong dung");
                *//*request.setAttribute("password", "");*//*
                request.setAttribute("errorBean", eb);
                baoloi3 += eb.getError();
                url = "/WEB-INF/book/register.jsp";
                String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
                request.setAttribute("encodedError", encodedError);

                return;
            } else {
                User customer = new User((cd.creatId() + 1), username, password, 2, name, null, null, null, email, null);
                System.out.println("id cua customer: " + customer.getUserId());
                cd.insert(customer);
                Email.sendEmail(email, "Chuc mung ban da tro thanh khach hang than thiet cua cua hang chung toi!", "Thong bao dang ky tai khoan thanh cong");
                url = "/WEB-INF/book/logintwo.jsp";
            }
            if (url != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }*/

        
        /*else {
            url = "/WEB-INF/book/register.jsp";
            String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
            request.setAttribute("encodedError", encodedError);
            request.getRequestDispatcher(url).forward(request, response);
            return;

        }*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("usernamere");
            String name = request.getParameter("namere");
            String email = request.getParameter("emailre");
            String password = request.getParameter("passwordre");
            String enterThePassword = request.getParameter("enterThePasswordre");
            UserDAO cd = new UserDAO();
            ErrorBean eb = new ErrorBean();
            String baoLoi1 = "";
            String baoLoi2 = "";
            String baoloi3 = "";
            System.out.println(username);
            String url = "";
        /*request.setAttribute("username", username);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("enterThePassword", enterThePassword);*/
            if (cd.selectByUsername(username)) {
                eb.setError("ten dang nhap da ton tai, vui long chon ten dang nhap khac");
                /* request.setAttribute("name", "");*/
                request.setAttribute("errorBean1", eb);
                baoLoi1 += eb.getError();

                url = "/WEB-INF/book/register.jsp";
                String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
                request.setAttribute("encodedError", encodedError);
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);

                return;

            } else if (cd.selectByEmail(email)) {
                eb.setError("ten email da ton tai, vui long chon email khac");
                /*request.setAttribute("name", "");*/
                request.setAttribute("errorBean2", eb);
                baoLoi2 += eb.getError();
                url = "/WEB-INF/book/register.jsp";
                String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
                request.setAttribute("encodedError", encodedError);
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);

                return;

            } else if (password != null && !password.equals(enterThePassword)) {
                eb.setError(" mat khau nhap lai khong dung");
                /*request.setAttribute("password", "");*/
                request.setAttribute("errorBean3", eb);
                baoloi3 += eb.getError();
                url = "/WEB-INF/book/register.jsp";
                String encodedError = URLEncoder.encode(eb.getError(), "UTF-8");
                request.setAttribute("encodedError", encodedError);
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);

                return;
            } else {
                User customer = new User((cd.creatId() + 1), username, password, 2, name, null, null, null, email, null);
                System.out.println("id cua customer: " + customer.getUserId());
                cd.insert(customer);
                Email.sendEmail(email, "Chuc mung ban da tro thanh khach hang than thiet cua cua hang chung toi!", "Thong bao dang ky tai khoan thanh cong");
                url = "/WEB-INF/book/logintwo.jsp";
            }
            if (url != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}