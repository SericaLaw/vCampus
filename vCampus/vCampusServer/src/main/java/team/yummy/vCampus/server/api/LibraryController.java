package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.BookViewModel;
import team.yummy.vCampus.models.viewmodel.BorrowRecordViewModel;
import team.yummy.vCampus.server.annotation.*;
import team.yummy.vCampus.server.framework.Controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class LibraryController extends Controller {

    /**
     * @apiGroup Library
     * @api {get} /library/book GetBookList
     * @apiDescription 获取图书列表
     * @apiPermission student admin
     * @apiParamExample Code Snippets
     *
     * WebResponse res = api.get("/library/book");
     * List<BookViewModel> bookList = res.dataList(BookViewModel.class);
     *
     * @apiSuccessExample Success-Response:
     *     200 OK
     *     ......
     *
     * @apiErrorExample Error-Response:
     *     404 "Book not found."
     *
     */
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

    /**
     * @apiGroup Library
     * @api {post} /library/book SearchForBooks
     * @apiDescription 按书名模糊搜索图书
     * @apiPermission student admin
     * @apiParamExample Code Snippets
     *
     * WebResponse res = api.post("/library/book", keyword);
     * List<BookViewModel> bookList = res.dataList(BookViewModel.class);
     *
     * @apiSuccessExample Success-Response:
     *     200 OK
     *     ......
     *
     * @apiErrorExample Error-Response:
     *     404 "Book not found."
     *
     */
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

    /**
     * @apiGroup Library
     * @api {get} /library/borrow GetBorrowRecords
     * @apiDescription 获取图书列表
     * @apiPermission student admin
     * @apiParamExample Code Snippets
     *
     * WebResponse res = api.get("/library/book");
     * List<BookViewModel> bookList = res.dataList(BookViewModel.class);
     *
     * @apiSuccessExample Success-Response:
     *     200 OK
     *     ......
     *
     * @apiErrorExample Error-Response:
     *     404 "Book not found."
     *
     */
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

    /**
     * @apiGroup Library
     * @api {post} /library/book NewBorrowRecords
     * @apiDescription 借书
     * @apiPermission student
     * @apiParamExample Code Snippets
     *
     * WebResponse res = api.post("/library/borrow", bookViewModel.getBookId());
     *
     * @apiSuccessExample Success-Response:
     *     201 "Successfully created"
     *     ......
     *
     * @apiErrorExample Error-Response:
     *     400 "Incorrect body data";
     *     400 "No corresponding book found"
     *     400 "No available book now"
     */
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


    /**
     * @apiGroup Library
     * @api {delete} /library/borrow DeleteBorrowRecords
     * @apiDescription 还书
     * @apiPermission student
     * @apiParamExample Code Snippets
     * WebResponse res = api.delete("/library/borrow/" + borrowRecordViewModel.getId());
     *
     * @apiSuccessExample Success-Response:
     *     200 "Entry Successfully deleted"
     *
     * @apiErrorExample Error-Response:
     *     404 "Entry does not exist!"
     */
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
            webContext.response.setStatusCode("404");
            return "Entry does not exist!";
        }
    }

    /**
     * 增加图书、搜索图书、修改图书信息、删除图书可以使用默认方法
     */

    /**
     * @apiGroup Library
     * @api {post} /book CreateBook
     * @apiDescription 添加图书
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * BookViewModel newBook = new BookViewModel();
     * newBook.setAvailableCount(5);
     * newBook.setBookId("404");
     * newBook.setBookName("C++从入门到入土");
     * newBook.setPublisher("东南大学出版社");
     * newBook.setTotalCount(5);
     * newBook.setWriter("叶神");
     *
     * api.post("/book", JSON.toJSONString(newBook));
     *
     * @apiSuccessExample Success-Response:
     *     201 OK
     *
     * @apiErrorExample Error-Response:
     *     403 "Book already exists."
     *
     */

    /**
     * @apiGroup Library
     * @api {get} /book/bookId/{bookId} GetBookById
     * @apiDescription 按ID查询图书
     * @apiPermission student admin
     * @apiParamExample Code Snippets
     * String bookId = "101";
     * WebResponse res = api.get("/book/bookId/" + bookId);
     * BookViewModel book = res.dataList(BookViewModel.class, 0);
     * @apiSuccessExample Success-Response:
     *     200 OK
     *     [{
     *          "TotalCount":"10",
     *          "BookID":"101",
     *          "BookName":"数据结构",
     *          "Publisher":"清华大学出版社",
     *          "AvailableCount":"10",
     *          "Writer":"邓俊辉"
     *     }]
     *
     * @apiErrorExample Error-Response:
     *     404 "Book not found."
     */

    /**
     * @apiGroup Library
     * @api {patch} /book/bookId/{bookId} ModifyBook
     * @apiDescription 修改图书
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
     * BookViewModel bookToModify = new BookViewModel();
     * bookToModify.setBookId("101");
     * bookToModify.setBookName("数据结构");
     * bookToModify.setWriter("邓俊辉");
     * bookToModify.setPublisher("清华大学出版社");
     * bookToModify.setAvailableCount(5);
     * bookToModify.setTotalCount(5);
     *
     * // 下面开始更新
     * bookToModify.setAvailableCount(10);
     * bookToModify.setTotalCount(10);
     *
     * api.patch("/book/bookId/" + bookToModify.getBookId(), JSON.toJSONString(bookToModify));
     * @apiSuccessExample Success-Response:
     *     200 OK
     *
     * @apiErrorExample Error-Response:
     *     404 "Book not found."
     */

    /**
     * @apiGroup Library
     * @api {delete} /book/bookId/{bookId} DeleteBook
     * @apiDescription 删除图书
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
     * BookViewModel bookToDelete = new BookViewModel();
     * bookToDelete.setBookId("404");
     * // 下面开始删除
     * api.delete("/book/bookId/" + bookToDelete.getBookId());
     * @apiSuccessExample Success-Response:
     *     200 OK
     *
     * @apiErrorExample Error-Response:
     *     404 "Book not found."
     */
}
