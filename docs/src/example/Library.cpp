/**
 * @apiGroup Library    
 * @api {get} /book GetBookList ( passed )
 * @apiPermission student
 * @apiDescription 获得图书数据表中前20条信息，用以展示图书馆书目信息，查询结果为空则返回"[]"
 * @apiSuccess List_Book book list
 * @apiParamExample Request Code Snippets
 * res = api.get("/book");
 * @apiSuccessExample Success-Response
 *      200 OK
 *      [   
 *          {
 *              "Publisher":"清华大学出版社",
 *              "BookID":"101",
 *              "BookName":"数据结构",
 *              "TotalCount":"5",
 *              "Writer":"邓俊辉",
 *              "AvailableCount":"4"
 *          },
 *          {
 *              "Publisher":"清华大学出版社",
 *              "BookID":"102","BookName":"机器学习",
 *              "TotalCount":"4",
 *              "Writer":"周志华",
 *              "AvailableCount":"2"
 *          }
 *      ]
 * 
 */

/**
 * @apiGroup Library    
 * @api {get} /book/bookName/:keyword/like SearchBook ( passed )
 * @apiPermission student
 * @apiDescription 模糊搜索图书，传入书名关键词keyword
 * @apiSuccess List_Book book list
 * @apiParamExample Request Code Snippets
 * res = api.get("/book/bookName/机器/like");
 * @apiSuccessExample Success-Response
 *      200 OK
 *      [
 *          {
 *              "Publisher":"清华大学出版社",
 *              "BookID":"102",
 *              "BookName":"机器学习",
 *              "TotalCount":"4",
 *              "Writer":"周志华",
 *              "AvailableCount":"2"
 *          }
 *      ]
 * 
 */

/**
 * @apiGroup Library    
 * @api {get} /borrowBook GetBorrowedBook ( passed )
 * @apiPermission student
 * @apiDescription 获取在借图书列表
 * @apiSuccess List_BorroedBook 在借图书list
 * @apiParamExample Request Code Snippets
 * res = api.get("/borrowBook");
 * @apiSuccessExample Success-Response
 *      200 OK
 *      [
 *          {
 *              "Publisher":"清华大学出版社",
 *              "BookID":"102",
 *              "BorrowDate":"2018-09-01 14:26:34.0",
 *              "ExpiryDate":"2018-10-01 14:26:34.0",
 *              "BookName":"机器学习","Writer":"周志华"
 *          },
 *          {
 *              "Publisher":"南海出版公司",
 *              "BookID":"103",
 *              "BorrowDate":"2018-09-01 14:26:44.0",
 *              "ExpiryDate":"2018-10-01 14:26:44.0",
 *              "BookName":"白夜行",
 *              "Writer":"东野圭吾"
 *          }
 *      ]
 * 
 */

/**
 * @apiGroup Library    
 * @api {post} /borrowBook BorrowBook ( passed )
 * @apiPermission student
 * @apiDescription 借书：注意后端不会判断书籍能否借阅（即availableCount是否大于0），提交请求前前端必须进行验证，不能借阅的书不得出请求
 * @apiParamExample Request Code Snippets
 * BorrowBookRecord borrowBookRecord = new BorrowBookRecord("101","213170000", new Date());
 * jsonData = JSON.toJSONString(borrowBookRecord, SerializerFeature.WriteDateUseDateFormat);
 * res = api.post("/borrowBook", jsonData);
 * @apiSuccessExample Success-Response:
 *     200 OK
 * @apiErrorExample Error-Response
 *     404 BorrowBook Not Found
 */


/**
 * @apiGroup Library    
 * @api {delete} /borrowBook/bookID/:bid/campusCardID/:cid ReturnBook ( passed )
 * @apiPermission student
 * @apiDescription 还书
 * @apiParamExample Request Code Snippets
 * res = api.delete("/borrowBook/bookID/101/campusCardID/213170000");
 * @apiSuccessExample Success-Response
 *     200 OK
 * @apiErrorExample Error-Response
 *     404 BorrowBook Not Found
 */
