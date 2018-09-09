package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "BorrowRecord", schema = "\".\"", catalog = "\".\"")
public class BorrowRecordEntity {
    private String id = UUID.randomUUID().toString();
    private Timestamp borrowDate;
    private Timestamp expiryDate;
    private AccountEntity accountByCampusCardId;
    private BookEntity bookByBookId;

    @Id
    @Column(name = "ID", unique=true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    @Basic
    @Column(name = "BorrowDate")
    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Basic
    @Column(name = "ExpiryDate")
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowRecordEntity that = (BorrowRecordEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null) return false;
        if (expiryDate != null ? !expiryDate.equals(that.expiryDate) : that.expiryDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID", nullable = false, insertable = false, updatable = false)
    public AccountEntity getAccountByCampusCardId() {
        return accountByCampusCardId;
    }

    public void setAccountByCampusCardId(AccountEntity accountByCampusCardId) {
        this.accountByCampusCardId = accountByCampusCardId;
    }

    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "BookID", nullable = false, insertable = false, updatable = false)
    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}
