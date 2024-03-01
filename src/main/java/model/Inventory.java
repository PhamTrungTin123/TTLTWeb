package model;

import java.util.Date;

public class Inventory {
    private int inventoryId;
    private Product product;
    private int quantityInStock; //so luong trong kho
    private int quantitySold; //so luong da ban
    private Date importDate; //ngay nhap

    public Inventory(int inventoryId, Product product, int quantityInStock, int quantitySold, Date importDate) {
        this.inventoryId = inventoryId;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.quantitySold = quantitySold;
        this.importDate = importDate;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", product=" + product +
                ", quantityInStock=" + quantityInStock +
                ", quantitySold=" + quantitySold +
                ", importDate=" + importDate +
                '}';
    }
}
