package model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private int cartId;
    private User user;
    private List<CartItem> cartItems;

    public Cart(int cartId, User user) {
        this.cartId = cartId;
        this.user = user;
    }

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", cartItems=" + cartItems +
                '}';
    }
    //tinh tong so luong san pham
    public int calculateTotalQuantity() {
        int totalQuantity = 0;
        if (cartItems != null) {
            for (CartItem item : cartItems) {
                totalQuantity += item.getQuantity();
            }
        }
        return totalQuantity;
    }
    public void removeItem(int productid) {
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            if (cartItem.getProduct().getProductId()==productid) {
                iterator.remove();
                return; // Đã xóa sản phẩm, không cần kiểm tra các phần tử khác
            }
        }

    }
    public double calculateTotal() {
        double total = 0;

        if (cartItems != null) {
            for (CartItem item : cartItems) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }

        return total;
    }

    public void addToCart(CartItem cartItem) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId()==cartItem.getProduct().getProductId()) {
                // Nếu đã tồn tại, tăng số lượng lên
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        // Nếu chưa tồn tại, thêm mới vào giỏ hàng
        cartItems.add(cartItem);
    }
    public void updateQuantity(int productId, int newquantity) {
        for (CartItem cart_item : cartItems) {
            if(cart_item.getProduct().getProductId()==productId) {
                cart_item.setQuantity(newquantity);
                return;
            }
        }
    }
    public CartItem findCartItemId(int productid) {
        for (CartItem cart_item : cartItems) {
            if(cart_item.getProduct().getProductId()==productid) {
                return cart_item;
            }
        }
        return null;
    }
    public void increaseQuantity(int productId) {
        CartItem item = findCartItemId(productId);
        if(item!=null) {
            //tang so luong cua cartiem len 1
            item.setQuantity(item.getQuantity()+1);
        }
    }
    public void clearCart() {
        cartItems.clear();
    }
}
