package team.yummy.vCampus.models.viewmodel;

import java.util.Date;

/**
 * 在借图书的视图模型类，用于前端展示在借图书信息
 * @author Serica
 */
public class BorrowRecordViewModel {
    private String Id;
    private String campusCardId;
    private String bookId;
    private String bookName;
    private String writer;
    private String publisher;
    private Date borrowDate;
    private Date expiryDate;


    public BorrowRecordViewModel() {}

    public BorrowRecordViewModel(String bookId, String campusCardId) {
        this.bookId = bookId;
        this.campusCardId = campusCardId;
    }

    public BorrowRecordViewModel(String Id, String campusCardId, String bookId, String bookName, String writer, String publisher, Date borrowDate, Date expiryDate) {
        this.Id = Id;
        this.campusCardId = campusCardId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.writer = writer;
        this.publisher = publisher;
        this.borrowDate = borrowDate;
        this.expiryDate = expiryDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
