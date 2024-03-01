package model;

public class Rating {
    private int ratingId;
    private Product product;
    private User user;
    private int rating;

    public Rating(int ratingId, Product product, User user, int rating) {
        this.ratingId = ratingId;
        this.product = product;
        this.user = user;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", product=" + product +
                ", user=" + user +
                ", rating=" + rating +
                '}';
    }
}
