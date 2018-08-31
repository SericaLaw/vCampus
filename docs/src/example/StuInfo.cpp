/**
 * @apiGroup StuInfo    
 * @api {post} ~/stuInfo CreateStuInfo
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "campusCardID":"213160000",
 *          "studentID":"09016101"
 *          "seniorHigh":"a",
 *          "IDNum":"320121199800000000",
 *          "birthplace":"Mars",
 *          "sex":"男",
 *          "department":"School of CS",
 *          "major":"Computer Science"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 201 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 Bad Request
 *     {
 *       "Error": "StuInfo already exist."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "CampusID does not exist"
 *     } 
 */

/**
 * @apiGroup StuInfo    
 * @api {get} ~/stuInfo/campusCardID/:id GetStuInfo

 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *      {
 *          "campusCardID":"213160000",
 *          "studentID":"09016101"
 *          "seniorHigh":"a",
 *          "IDNum":"320121199800000000",
 *          "birthplace":"Mars",
 *          "sex":"male",
 *          "department":"School of CS",
 *          "major":"Computer Science"
 *      }
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "StuInfo not found."
 *     } 
 */
 

/**
 * @apiGroup StuInfo    
 * @api {patch} /StuInfo/campusCardID/:id ModifyStuInfo
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusCardID":"213160000",
 *          "SeniorHigh":"a",
 *          "IDNum":"320121199800000000",
 *          "Birthplace":"Mars"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "StuInfo not found."
 *     } 
 */

/**
 * @apiGroup StuInfo    
 * @api {delete} /StuInfo/campusCardID/:id DeleteStuInfo
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "StuInfo not found."
 *     } 
 */
