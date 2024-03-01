package model;

import java.util.ArrayList;
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
}
