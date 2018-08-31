/**
 * @apiGroup StuInfo
 * @api {post} ~/stuInfo CreateStuInfo ( passed )
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
 *     201 OK
 * 
 * @apiErrorExample Error-Response:
 *     403  "StuInfo already exists."
 *     
 *     404  "StuInfo not found."
 *     
 */

/**
 * @apiGroup StuInfo    
 * @api {get} ~/stuInfo/campusCardID/:id GetStuInfo ( passed )

 * @apiSuccessExample Success-Response:
 *     200 OK
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
 *     404 "StuInfo not found."
 *     
 */
 

/**
 * @apiGroup StuInfo    
 * @api {patch} ~/stuInfo/campusCardID/:id ModifyStuInfo ( passed )
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusCardID":"213160000",
 *          "SeniorHigh":"a",
 *          "IDNum":"320121199800000000",
 *          "Birthplace":"Mars"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     200 OK
 * 
 * @apiErrorExample Error-Response:
 *     404 "StuInfo not found."
 *     
 */

/**
 * @apiGroup StuInfo    
 * @api {delete} ~/stuInfo/campusCardID/:id DeleteStuInfo ( passed )
 * @apiSuccessExample Success-Response:
 *     200 OK
 * @apiErrorExample Error-Response:
 *     404  "StuInfo not found."
 *     
 */
