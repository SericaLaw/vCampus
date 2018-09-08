package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.BorrowRecordViewModel;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.api.annotation.*;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.time.Period;
import java.util.ArrayList;

public class LibraryController extends Controller {

    AccountEntity account;

    @Override
    public void init(WebContext webContext) {
        super.init(webContext);
        Transaction tx = dbSession.beginTransaction();
        AccountEntity account = dbSession.get(AccountEntity.class, webContext.session.getString("campusCardId"));
        tx.commit();
    }


    @Get(route = "book")
    public void getBookList() {

    }

    @Get(route = "borrow")
    public void getBorrowRecords() {
        webContext.response.setJsonData(JSON.toJSONString(
            account.getBorrowRecordsByCampusCardId()
                .stream()
                .map(record -> new BorrowRecordViewModel(
                    record.getCampusCardId(),
                    record.getBookId(),
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
        BorrowRecordEntity record = webContext.request.deserialize(BorrowRecordEntity.class);
        if (record == null) {
            webContext.response.setStatusCode("400");
            return "Incorrect json data";
        }

        BookEntity book = dbSession.load(BookEntity.class, record.getBookId());
        if (book == null) {
            webContext.response.setStatusCode("400");
            return "No corresponding book found";
        }

        if (book.getAvailableCount() == 0) {
            webContext.response.setStatusCode("400");
            return "No available book now";
        }

        // 正式更新
        record.setExpiryDate(record.getBorrowDate());
        book.decAvailableCount();

        dbSession.save(record);
        dbSession.update(book);

        tx.commit();
        webContext.response.setStatusCode("201");
        return "Successfully created";
    }

    // DELETE library/borrow/{borrowRecordId}
    @Delete(route = "borrow")
    public String deleteBorrowRecords(String borrowRecordId) {
        Transaction tx = dbSession.beginTransaction();
        BorrowRecordEntity record = dbSession.load(BorrowRecordEntity.class, Integer.parseInt(borrowRecordId));
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
}
