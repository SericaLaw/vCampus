/**
 * @apiGroup StuInfo    
 * @api {post} /StuInfo CreateStuInfo
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusID":"213160000",
 *          "SeniorHigh":"a",
 *          "IDNum":"320121199800000000",
 *          "Birthplace":"Mars",
 *          "Sex":"0"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Student info already created."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "CampusID does not exist"
 *     } 
 */

/**
 * @apiGroup StuInfo    
 * @api {get} /StuInfo/:CampusCardID GetStuInfo

 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *      {
 *          "CampusID":"213160000",
 *          "SeniorHigh":"a",
 *          "IDNum":"320121199800000000",
 *          "Birthplace":"Mars",
 *          "Sex":"0"
 *      }
 */

/**
 * @apiGroup StuInfo    
 * @api {patch} /StuInfo/:CampusCardID ModifyStuInfo
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "CampusID":"213160000",
 *          "SeniorHigh":"a",
 *          "IDNum":"320121199800000000",
 *          "Birthplace":"Mars",
 *          "Sex":"0"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password"
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Stuinfo not yet created"
 *     } 
 */

/**
 * @apiGroup StuInfo    
 * @api {delete} /StuInfo/:CampusCardID DeleteStuInfo
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "CampusID":"213160000"
 *      }
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Stuinfo not yet created"
 *     } 
 */
