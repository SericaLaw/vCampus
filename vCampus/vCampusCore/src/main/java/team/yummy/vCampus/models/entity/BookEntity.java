package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Book", schema = "\".\"", catalog = "\".\"")
public class BookEntity {
    private String bookName;
    private String writer;
    private String publisher;
    private Short availableCount;
    private Integer totalCount;
    private String bookId;
    private Collection<BorrowRecordEntity> borrowRecordsByBookId;

    @Basic
    @Column(name = "BookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "Writer")
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Basic
    @Column(name = "Publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "AvailableCount")
    public Short getAvailableCount() {
        return availableCount;
    }

    public void incAvailableCount() { ++availableCount; }

    public void decAvailableCount() { --availableCount; }

    public void setAvailableCount(Short availableCount) {
        this.availableCount = availableCount;
    }

    @Basic
    @Column(name = "TotalCount")
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Id
    @Column(name = "BookID")
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (writer != null ? !writer.equals(that.writer) : that.writer != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (availableCount != null ? !availableCount.equals(that.availableCount) : that.availableCount != null)
            return false;
        if (totalCount != null ? !totalCount.equals(that.totalCount) : that.totalCount != null) return false;
        if (bookId != null ? !bookId.equals(that.bookId) : that.bookId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookName != null ? bookName.hashCode() : 0;
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (availableCount != null ? availableCount.hashCode() : 0);
        result = 31 * result + (totalCount != null ? totalCount.hashCode() : 0);
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<BorrowRecordEntity> getBorrowRecordsByBookId() {
        return borrowRecordsByBookId;
    }

    public void setBorrowRecordsByBookId(Collection<BorrowRecordEntity> borrowRecordsByBookId) {
        this.borrowRecordsByBookId = borrowRecordsByBookId;
    }
}
