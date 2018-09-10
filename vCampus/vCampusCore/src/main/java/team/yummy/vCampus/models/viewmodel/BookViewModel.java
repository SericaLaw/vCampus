package team.yummy.vCampus.models.viewmodel;

/**
 * @author Serica
 * BookViewModel 前端展示类
 * 对应Book表?
 */
public class BookViewModel {
    private String bookID;
    private String bookName;
    private String writer;
    private String publisher;
    private int availableCount;
    private int totalCount;

    public BookViewModel() {}

    public BookViewModel(String bookID, String bookName, String writer, String publisher, int availableCount, int totalCount) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.writer = writer;
        this.publisher = publisher;
        this.availableCount = availableCount;
        this.totalCount = totalCount;
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
