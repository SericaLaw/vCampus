/**
 * @apiGroup Library    
 * @api {get} /Book ListBooks/SearchBooks 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *      {
 *          "BookList":[
 *              {
 *                  "BookID":"1",
 *                  "BookName":"How to become a lousy programmer",
 *                  "Writer":"BetaCat",
 *                  "Publisher":"Southeast University",
 *                  "BookStatus":"Borrowed"
 *              },
 *              {
 *                  "BookID":"2",
 *                  "BookName":"How to become a very lousy programmer",
 *                  "Writer":"BetaCat",
 *                  "Publisher":"Southeast University",
 *                  "BookStatus":"UnBorrowed"
 *              }        
 *          ]
 *      }
 * 
 */

/**
 * @apiGroup Library    
 * @api {post} /Book/:id BorrowBook
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"trialpwd"
 *      }
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Book does not exist."
 *     }
 */


/**
 * @apiGroup Library    
 * @api {delete} /Book/:id ReturnBook
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"trialpwd"
 *      }
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Book does not exist."
 *     }
 */
