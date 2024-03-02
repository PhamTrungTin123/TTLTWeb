package Controller.User;
import model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveItemCart", value = "/RemoveItemCart")
public class RemoveItemCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy mã sản phẩm từ request
        String productId = request.getParameter("productId");
        int product_id = Integer.parseInt(productId);

        // Lấy giỏ hàng từ session
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            // Xóa sản phẩm khỏi giỏ hàng
            cart.removeItem(product_id);
        }

        // Cập nhật session
        request.getSession().setAttribute("cart", cart);

        // Chuyển hướng lại đến trang giỏ hàng hoặc trang gốc
        response.sendRedirect(request.getHeader("referer"));
    }
}