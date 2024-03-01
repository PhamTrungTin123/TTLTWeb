package model;

import java.util.Date;
import java.util.List;

public class Import {
    private int importId;
    private String supplier; //nha cung cap
    private String note;
    private Date importDate; //ngay nhap
    private List<ImportDetail> importDetailList;

    public Import(int importId, String supplier, String note, Date importDate) {
        this.importId = importId;
        this.supplier = supplier;
        this.note = note;
        this.importDate = importDate;
    }

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public List<ImportDetail> getImportDetailList() {
        return importDetailList;
    }

    public void setImportDetailList(List<ImportDetail> importDetailList) {
        this.importDetailList = importDetailList;
    }

    @Override
    public String toString() {
        return "Import{" +
                "importId=" + importId +
                ", supplier='" + supplier + '\'' +
                ", note='" + note + '\'' +
                ", importDate=" + importDate +
                ", importDetailList=" + importDetailList +
                '}';
    }
}
