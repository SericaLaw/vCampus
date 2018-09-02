/**
 * @apiGroup Account   
 * @api {post} /account Register ( passed )
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
 *     201 OK
 * 
 * @apiErrorExample Error-Response:
 *     403 "Account already exists."
 */

/**
 * @apiGroup Account    
 * @api {post} /account/login Login ( passed )
 * @apiName LoginAPI
 * 
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"trialpwd"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     200 OK
 *     {
 *       "CampusCardID": "213180000",
 *       "Username": "client",
 *       "Password": "123",
 *       "FirstName":"Foo",
 *       "LastName": "Bar",
 *       "Role": "student"
 *     }
 * 
 * @apiErrorExample Error-Response:
 *     403 "Wrong password."
 *     
 *     404 "Account not found."
 *     
 */

/**
 * @apiGroup Account    
 * @api {delete} /account/campusCardID/:id UnRegister ( passed )
 * @apiName UnRegisterAPI
 * @apiSuccessExample Success-Response:
 *     200 OK
 * @apiParamExample
 * @apiErrorExample Error-Response:
 *     404 "Account not found."
 */

/**
 * @apiGroup Account    
 * @api {get} /account/user/:id Logout
 * @apiName LogoutAPI

 * @apiSuccessExample Success-Response:
 *    200 OK
 * 
 * @apiErrorExample Error-Response:
 *    404 "Account not found."
 *    
 */
