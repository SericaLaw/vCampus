/**
 * @apiGroup Bank    
 * @api {post} /bank/:CampusCardID/ CreateBankAccount
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusCardID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Bank account already created."
 *     }
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "User not found."
 *     }
 */


/**
 * @apiGroup Bank    
 * @api {delete} /bank/:CampusCardID DeleteBankAccount
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "User does not exist."
 *     }
 */

/**
 * @apiGroup Bank    
 * @api {patch} /bank/:CampusCardID FreezeBankAccount 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
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
 * @apiGroup Bank    
 * @api {get} /bank/:CampusCardID GetBankInfo 
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusCardID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *          "CampusCardID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "BudgetList":[
 *              {
 *                  "BudgetID":"123",
 *                  "Asset":"10000",
 *                  "Time":"2018-7-3 12:00:00",
 *                  "BudgetInfo":""
 *              },
 *              {
 *                  "BudgetID":"189",
 *                  "Asset":"-1000",
 *                  "Time":"2018-7-4 12:00:00",
 *                  "BudgetInfo":""
 *              }
 *          ]
 *      }
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
 * @api {get} /account/user/:id logout
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


/**
 * @apiGroup Bank    
 * @api {POST} /budget/:CampusCardID Save/Withdraw/Loan
* @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusCardID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "Asset":"1000",
 *          "OperationType":"Save"  
 *      }
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
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



