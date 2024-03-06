package Controller.User;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Shopgrid", value = "/Shopgrid")
public class Shopgrid extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User auth = (User) request.getAttribute("userC");
//        HttpSession session = request.getSession(true);
//        if (session != null) {
//            if (auth != null && auth.getUsername() != null) {
//                request.getRequestDispatcher("/WEB-INF/book/shop-grid.jsp").forward(request, response);
//                // User authenticated, forward to index.jsp
//                return; // Stop further processing
//            }
//        }
// User not authenticated, redirect to the login page
        String url = "/WEB-INF/book/shop-grid.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}