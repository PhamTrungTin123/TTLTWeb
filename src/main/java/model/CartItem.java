package model;

public class CartItem {
    private int cartItemId;
    private Product product;
    private int quantity;
    private double price;
    private Cart cart;

    public CartItem() {
    }

    public CartItem(int cartItemId, Product product, int quantity, double price, Cart cart) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.cart = cart;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", cart=" + cart +
                '}';
    }
}
