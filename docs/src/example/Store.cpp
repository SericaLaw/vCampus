/**
 * @apiGroup Store    
 * @api {post} /Commodity CreateGoods
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"trialpwd",
 *          "CommodityProperties":"abcdefghijklmn",
 *          "ProductionDate":"2018-8-10",
 *          "CommodityTot":"100",
 *          "Price":"12"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 */



/**
 * @apiGroup Store    
 * @api {get} /Commodity ListGoods/SearchGoods
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *      {
 *          "List":[
 *              {
 *                  "CommodityID":"1",
 *                  "CommodityProperties":"abcdefghijklmn",
 *                  "ProductionDate":"2018-8-10",
 *                  "CommodityTot":"100",
 *                  "Price":"12"
 *              },
 *              {
 *                  "CommodityID":"2",
 *                  "CommodityProperties":"abcdefghijklmn",
 *                  "ProductionDate":"2018-8-12",
 *                  "CommodityTot":"101",
 *                  "Price":"5"
 *              }        
 *          ]
 *      } 
 * 
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     }
 */

/**
 * @apiGroup Store    
 * @api {post} /Cart/:CommodityID AddToCart
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CommodityID":"10203",
 *          "CommodityCnt":"1"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 */

/**
 * @apiGroup Store    
 * @api {delete} /Cart/:CommodityID RemoveFromCart
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "CommodityID":"10203",
 *          "CommodityCnt":"1"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 */

/**
 * @apiGroup Store    
 * @api {post} /Purchase/:CommodityID CreateOrder
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"pwd",
 *          "CommodityID":"10203",
 *          "CommodityCnt":"1"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     } 
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Commodity does not exist."
 *     } 
 */

/**
 * @apiGroup Store    
 * @api {delete} /Purchase/:CommodityID CancelOrder
 * @apiParamExample {json} JSON-Request:
 *      {
 *          "Username":"trial",
 *          "Password":"pwd",
 *          "CommodityID":"10203",
 *          "CommodityCnt":"1"
 *      }
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 Bad Request
 *     {
 *       "Error": "Wrong username or password."
 *     } 
 *     HTTP/1.1 404 Not Found
 *     {
 *       "Error": "Commodity does not exist."
 *     } 
 */
