/**
 * @apiGroup Account    
 * @api {post} /account/user/ Register
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "FirstName":"trialfirstname",
 *          "LastName":"triallastname"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "User already created."
 *     }
 */

/**
 * @apiGroup Account    
 * @api {get} /account/user/ UnRegister
 * @apiName UnRegisterAPI
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "User already created."
 *     }
 */

/**
 * @apiGroup Account    
 * @api {post} /account/user/:id Login
 * @apiName LoginAPI
 * 
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"trialpwd"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "CampusCardID": "213160000",
 *       "FirstName":"张",
 *       "LastName": "三"
 *     }
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "WrongUsernameOrPassword"
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "UserNotFound"
 *     }
 */

/**
 * @apiGroup Account    
 * @api {get} /account/user/:id Logout
 * @apiName LogoutAPI

 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "User not found."
 *     }
 */
