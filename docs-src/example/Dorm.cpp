/**
 * @apiGroup Dorm    
 * @api {post} /dorm/:DormID/ GetDormExpenditure/GetDormScoring....
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CampusID":"213160000",
 *          "Username":"trial",
 *          "Password":"trialpwd"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "DormID":"M5D633",
 *       "CampusCardID": "213160000",
 *       "DormMem":[
 *          {
 *             "CampusCardID": "213160000",
 *             "FirstName":"张",
 *             "LastName": "三",
 *              "ExpenditureList":[
 *                  {
 *                      "ID":"101334",
 *                      "CommodityID":"732",
 *                      "PurchaseTime":"2018-7-22 09:01:23",
 *                      "Price":"21"        
 *                  },
 *                  {
 *                      "ID":"101337",
 *                      "CommodityID":"719",
 *                      "PurchaseTime":"2018-7-28 09:01:23",
 *                      "Price":"25"        
 *                  }    
 *              ]
 *          },
 *          {
 *             "CampusCardID": "213160001",
 *             "FirstName":"李",
 *             "LastName": "四"
 *             "ExpenditureList":""
 *          },
 *          {
 *             "CampusCardID": "213160002",
 *             "FirstName":"王",
 *             "LastName": "五"
 *             "ExpenditureList":""
 *          }
 *      ]，
 *      "ScoringList":[
 *          {
 *              "ID":"101334",
 *              "ScoringDate":"2018-7-22 09:01:23",
 *              "Score":"95"
 *          },
 *          {
 *              "ID":"101339",
 *              "ScoringDate":"2018-7-29 09:01:23",
 *              "Score":"96"
 *          }
 *      ]
 *     } 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 */


