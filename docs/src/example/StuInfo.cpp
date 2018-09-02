/**
 * @apiGroup StuInfo    
 * @api {get} /stuInfo/campusCardID/:id GetStuInfo ( passed )
 * @apiPermission student
 * @apiDescription 获取学生信息
 * @apiSuccess List_StuInfo 只含有一个项的StuInfo list
 * @apiParamExample Code Snippets
 * WebResponse res = api.get("/stuInfo/campusCardID/213180000");
 * StuInfo stuInfoGot = res.dataList(StuInfo.class, 0);
 * @apiSuccessExample Success-Response:
 *      200 OK
 *      [
 *          {
 *              "LectureAttendCount":"20",
 *              "Phone":"110",
 *              "Major":"计算机科学与技术",
 *              "SeniorHigh":"霍格沃茨魔法学校",
 *              "IDNum":"1234567",
 *              "GPA":"4.81",
 *              "EnrollmentYear":"2018",
 *              "StudentID":"09018101",
 *              "Birthplace":"临冬城",
 *              "Email":"boss@microsoft.com#mailto:boss@microsoft.com#",
 *              "SRTP":"100.0",
 *              "Address":"贝克街221B",
 *              "Birthdate":"1998-06-04 00:00:00.0",
 *              "Department":"计算机科学与工程学院",
 *              "CampusCardID":"213180000",
 *              "Sex":"男"
 *          }
 *      ]
 * @apiErrorExample Error-Response:
 *     404 "StuInfo not found."
 *     
 */

/**
 * @apiGroup StuInfo    
 * @api {patch} /stuInfo/campusCardID/:id ModifyStuInfo ( passed )
 * @apiDescription 修改学生信息，该API不会返回修改后的StuInfo，若服务器告知修改成功，则前端自行对数据进行修改然后展示到界面上
 * @apiPermission student
 * @apiParamExample Code Snippets
 * JSONObject infoToModify = new JSONObject();
 * infoToModify.put("Phone", "120");
 * infoToModify.put("Sex","女");
 * api.patch("/stuInfo/campusCardID/213180000", infoToModify.toJSONString());
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
 * @api {post} /stuInfo CreateStuInfo ( passed )
 * @apiPermission admin
 * @apiDescription 创建学籍信息，这主要是初始化的时候用的
 * @apiDeprecated 现在不建议前端调用这条api
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
 * @api {delete} /stuInfo/campusCardID/:id DeleteStuInfo ( passed )
 * @apiPermission admin
 * @apiDescription 删除学籍信息
 * @apiDeprecated 现在不建议前端调用这条api
 * @apiSuccessExample Success-Response:
 *     200 OK
 * @apiErrorExample Error-Response:
 *     404  "StuInfo not found."
 *     
 */
