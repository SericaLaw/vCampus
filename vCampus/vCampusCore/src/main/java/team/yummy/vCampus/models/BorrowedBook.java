package team.yummy.vCampus.models;

import java.util.Date;

/**
 * @author Serica
 * 在借图书类
 * 存放关联查询的结果，用于前端展示在借图书信息
 */
public class BorrowedBook {
    private String bookID;
    private String bookName;
    private String writer;
    private String publisher;
    private Date borrowDate;
    private Date expiryDate;


    public BorrowedBook() {}
    public BorrowedBook(String bookID, String bookName, String writer, String publisher, Date borrowDate, Date expiryDate) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.writer = writer;
        this.publisher = publisher;
        this.borrowDate = borrowDate;
        this.expiryDate = expiryDate;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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