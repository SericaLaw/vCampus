/**
 * @apiGroup Course    
 * @api {post} /course CreateNewCourse
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "ProfCampusCardID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "CourseIntro":"null",
 *          "StuLimit":"20"
 *      }
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Professor does not exist."
 *     }
 */

/**
 * @apiGroup Course    
 * @api {patch} /course/:CourseID ModifyCourseInfo
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "ProfCampusCardID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "CourseIntro":"null",
 *          "StuLimit":"20"
 *      }
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Professor does not exist."
 *     }
 */

/**
 * @apiGroup Course    
 * @api {get} /course/courseID/:id GetCourseInfo
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *      {
 *          "ProfLastName":"李",
 *          "ProfFirstName":"四",
 *          "CourseIntro":"null",
 *          "StuLimit":"20",
 *          "StuList":[
 *              {
 *                  "CampusCardID":"213160000",
 *                  "LastName":"张"，
 *                  "FirstName":"三"
 *              },
 *              {
 *                  "CampusCardID":"213160001",
 *                  "LastName":"王"，
 *                  "FirstName":"五"
 *              }
 *          ] 
 *      }
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Course does not exist."
 *     }
 */

/**
 * @apiGroup Course    
 * @api {post} /course/:CourseID SelectCourse
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusCardID":"213160000",
 *          "CourseID":"101"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK

 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Course does not exist."
 *     }
 */

/**
 * @apiGroup Course    
 * @api {delete} /course/:CourseID DropCourse
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
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Course does not exist."
 *     }
 */
