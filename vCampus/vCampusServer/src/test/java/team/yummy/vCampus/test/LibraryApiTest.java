package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.BookViewModel;

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
}
