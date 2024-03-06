package model;


import java.sql.Date;

public class Comment {
    private int commentid;
    private Product product;
    private User user;
    private String detailComment;
    private Date dateComment;

    public Comment(int commentid, Product product, User user, String detailComment, Date dateComment) {
        this.commentid = commentid;
        this.product = product;
        this.user = user;
        this.detailComment = detailComment;
        this.dateComment = dateComment;
    }

    public int getCommentid() {
        return commentid;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public String getDetailComment() {
        return detailComment;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDetailComment(String detailComment) {
        this.detailComment = detailComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentid=" + commentid +
                ", product=" + product +
                ", user=" + user +
                ", detailComment='" + detailComment + '\'' +
                ", dateComment=" + dateComment +
                '}';
    }
}
