/**
 * @apiGroup Account    
 * @api {post} /account/campusCardID/:id Register
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusCardID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "FirstName":"trialfirstname",
 *          "LastName":"triallastname",
 *          "role":"student"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 201 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 Forbidden
 *     {
 *       "Error": "Account already created."
 *     }
 */

/**
 * @apiGroup Account    
 * @api {delete} /account/campusCardID/:id UnRegister
 * @apiName UnRegisterAPI
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "message": "Account not found."
 *     }
 */

/**
 * @apiGroup Account    
 * @api {post} /account/login Login
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
 *     HTTP/1.1 403 Forbidden
 *     {
 *       "message": "Wrong password."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "message": "Account not found."
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
