package team.yummy.vCampus.models.viewmodel;

import team.yummy.vCampus.models.entity.BookEntity;

public class BookViewModel {
    private String bookId;
    private String bookName;
    private String writer;
    private String publisher;
    private Short availableCount;
    private Integer totalCount;

    public BookViewModel(BookEntity book) {
        this.bookId = book.getBookId();
        this.bookName = book.getBookName();
        this.writer = book.getWriter();
        this.publisher = book.getPublisher();
        this.availableCount = book.getAvailableCount();
        this.totalCount = book.getTotalCount();
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

    public Short getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Short availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
