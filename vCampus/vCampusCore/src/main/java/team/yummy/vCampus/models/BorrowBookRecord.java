package team.yummy.vCampus.models;

import java.util.Calendar;
import java.util.Date;

/**
 * 对应BorrowBook表
 */
public class BorrowBookRecord {
    private String bookID;
    private String campusCardID;
    private Date borrowDate;
    private Date expiryDate;

    public BorrowBookRecord() {}

    public BorrowBookRecord(String bookID, String campusCardID, Date borrowDate) {
        this.bookID = bookID;
        this.campusCardID = campusCardID;
        this.borrowDate = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(borrowDate);
        now.add(Calendar.MONTH, 1);
        this.expiryDate = now.getTime();
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getCampusCardID() {
        return campusCardID;
    }

    public void setCampusCardID(String campusCardID) {
        this.campusCardID = campusCardID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
