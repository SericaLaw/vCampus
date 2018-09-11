package team.yummy.vCampus.models.viewmodel;

import team.yummy.vCampus.models.entity.BookEntity;

import java.util.UUID;

/**
 * @author Serica
 * BookViewModel 前端展示类
 * 对应Book表?
 */
public class BookViewModel {
    private String bookId = UUID.randomUUID().toString();
    private String bookName;
    private String writer;
    private String publisher;
    private int availableCount;
    private int totalCount;

    public BookViewModel() {}

    public BookViewModel(BookEntity book) {
        this.bookId = book.getBookId();
        this.bookName = book.getBookName();
        this.writer = book.getWriter();
        this.publisher = book.getPublisher();
        this.availableCount = book.getAvailableCount();
        this.totalCount = book.getTotalCount();
    }

    public BookViewModel(String bookId, String bookName, String writer, String publisher, int availableCount, int totalCount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.writer = writer;
        this.publisher = publisher;
        this.availableCount = availableCount;
        this.totalCount = totalCount;
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

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
