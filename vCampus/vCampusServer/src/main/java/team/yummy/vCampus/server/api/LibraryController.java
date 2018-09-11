package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.BookViewModel;
import team.yummy.vCampus.models.viewmodel.BorrowRecordViewModel;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.annotation.*;

import java.awt.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class LibraryController extends Controller {

    AccountEntity account;

    @Override
    public void init(WebContext webContext) {
        super.init(webContext);
        Transaction tx = dbSession.beginTransaction();
        account = dbSession.get(AccountEntity.class, webContext.session.getString("campusCardId"));
        tx.commit();
    }


    @Get(route = "book")
    public void getBookList() {
        Transaction tx = dbSession.beginTransaction();
        List<BookEntity> books = (List<BookEntity>)dbSession.createQuery(
            "select book from BookEntity book"
        ).setFirstResult(0).setMaxResults(20).list();
        tx.commit();

        webContext.response.setBody(JSON.toJSONString(
            books.stream().map(book -> new BookViewModel(book)).toArray()
        ));
    }

    @Post(route = "book")
    public void searchForBooks() {
        String keyword = webContext.request.getBody();
        Transaction tx = dbSession.beginTransaction();
        List<BookEntity> books = (List<BookEntity>)dbSession.createQuery(
            "select book from BookEntity book where book.bookName like ?1"
        ).setParameter(1, "%" + keyword + "%").list();
        tx.commit();

        webContext.response.setBody(JSON.toJSONString(
            books.stream().map(book -> new BookViewModel(book)).toArray()
        ));
    }

    @Get(route = "borrow")
    public void getBorrowRecords() {
        webContext.response.setBody(JSON.toJSONString(
            account.getBorrowRecordsByCampusCardId()
                .stream()
                .map(record -> new BorrowRecordViewModel(
                    record.getId(),
                    account.getCampusCardId(),
                    record.getBookByBookId().getBookId(),
                    record.getBookByBookId().getBookName(),
                    record.getBookByBookId().getWriter(),
                    record.getBookByBookId().getPublisher(),
                    record.getBorrowDate(),
                    record.getExpiryDate()
                ))
                .toArray()
        ));
    }

    @Post(route = "borrow")
    public String newBorrowRecords() {
        Transaction tx = dbSession.beginTransaction();
        String bookId = webContext.request.getBody();
        if (bookId == null) {
            webContext.response.setStatusCode("400");
            return "Incorrect body data";
        }

        BookEntity book = dbSession.get(BookEntity.class, bookId);
        if (book == null) {
            webContext.response.setStatusCode("400");
            return "No corresponding book found";
        }

        if (book.getAvailableCount() == 0) {
            webContext.response.setStatusCode("400");
            return "No available book now";
        }

        // 正式更新
        book.decAvailableCount();
        dbSession.update(book);

        BorrowRecordEntity record = new BorrowRecordEntity();
        record.setAccountByCampusCardId(account);
        record.setBookByBookId(book);

        Calendar calendar = Calendar.getInstance();
        record.setBorrowDate(new Timestamp(calendar.getTimeInMillis()));
        calendar.add(Calendar.MONTH, 1);
        record.setExpiryDate(new Timestamp(calendar.getTimeInMillis()));
        dbSession.save(record);

        tx.commit();
        webContext.response.setStatusCode("201");
        return "Successfully created";
    }

    // DELETE library/borrow/{borrowRecordId}
    @Delete(route = "borrow")
    public String deleteBorrowRecords(String borrowRecordId) {
        Transaction tx = dbSession.beginTransaction();
        BorrowRecordEntity record = dbSession.load(BorrowRecordEntity.class, borrowRecordId);
        if (record != null) {
            BookEntity book = record.getBookByBookId();
            book.incAvailableCount();
            dbSession.update(book);
            dbSession.delete(record);
            tx.commit();
            return "Entry Successfully deleted";
        } else {
            return "Entry does not exist!";
        }
    }

    /**
     * 增加图书、搜索图书、修改图书信息、删除图书可以使用默认方法
     */
}
