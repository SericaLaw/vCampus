package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.BookViewModel;
import team.yummy.vCampus.web.WebResponse;

public class LibraryApiTest extends ApiTest {
    /**
     * @apiGroup Library
     * @api {post} /book
     * @apiDescription 添加图书 CreateBook
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
    @Test
    public void createBook() {
        BookViewModel newBook = new BookViewModel();
        newBook.setAvailableCount(5);
        newBook.setBookId("404");
        newBook.setBookName("C++从入门到入土");
        newBook.setPublisher("东南大学出版社");
        newBook.setTotalCount(5);
        newBook.setWriter("叶神");

        api.post("/book", JSON.toJSONString(newBook));
    }

    /**
     * @apiGroup Library
     * @api {patch} /book/bookId/{bookId}
     * @apiDescription 修改图书 ModifyBook
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
    @Test
    public void modifyBook() {
        // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
        BookViewModel bookToModify = new BookViewModel();
        bookToModify.setBookId("101");
        bookToModify.setBookName("数据结构");
        bookToModify.setWriter("邓俊辉");
        bookToModify.setPublisher("清华大学出版社");
        bookToModify.setAvailableCount(5);
        bookToModify.setTotalCount(5);

        // 下面开始更新
        bookToModify.setAvailableCount(10);
        bookToModify.setTotalCount(10);

        api.patch("/book/bookId/" + bookToModify.getBookId(), JSON.toJSONString(bookToModify));
    }

    /**
     * @apiGroup Library
     * @api {delete} /book/bookId/{bookId}
     * @apiDescription 删除图书 DeleteBook
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
    @Test
    public void deleteBook() {
        // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
        BookViewModel bookToDelete = new BookViewModel();
        bookToDelete.setBookId("404");
        // 下面开始删除
        api.delete("/book/bookId/" + bookToDelete.getBookId());
    }


    /**
     * @apiGroup Library
     * @api {get} /book/bookId/{bookId}
     * @apiDescription 按ID查询图书 GetBookById
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
    @Test
    public void getBookById() {
        String bookId = "101";
        WebResponse res = api.get("/book/bookId/" + bookId);
        BookViewModel book = res.dataList(BookViewModel.class, 0);
    }

}
