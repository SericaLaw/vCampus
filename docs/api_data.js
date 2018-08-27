define({ "api": [
  {
    "group": "Account",
    "type": "post",
    "url": "/account/user/:id",
    "title": "Login",
    "name": "LoginAPI",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"CampusCardID\": \"213160000\",\n  \"FirstName\":\"张\",\n  \"LastName\": \"三\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"WrongUsernameOrPassword\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"UserNotFound\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Account.cpp",
    "groupTitle": "Account"
  },
  {
    "group": "Account",
    "type": "get",
    "url": "/account/user/:id",
    "title": "Logout",
    "name": "LogoutAPI",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"User not found.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Account.cpp",
    "groupTitle": "Account"
  },
  {
    "group": "Account",
    "type": "get",
    "url": "/account/user/:id",
    "title": "logout",
    "name": "LogoutAPI",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"User not found.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Bank.cpp",
    "groupTitle": "Account"
  },
  {
    "group": "Account",
    "type": "post",
    "url": "/account/user/",
    "title": "Register",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"FirstName\":\"trialfirstname\",\n    \"LastName\":\"triallastname\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"User already created.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Account.cpp",
    "groupTitle": "Account",
    "name": "PostAccountUser"
  },
  {
    "group": "Account",
    "type": "get",
    "url": "/account/user/",
    "title": "UnRegister",
    "name": "UnRegisterAPI",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"User already created.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Account.cpp",
    "groupTitle": "Account"
  },
  {
    "group": "Bank",
    "type": "delete",
    "url": "/bank/:CampusCardID",
    "title": "DeleteBankAccount",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"User does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Bank.cpp",
    "groupTitle": "Bank",
    "name": "DeleteBankCampuscardid"
  },
  {
    "group": "Bank",
    "type": "get",
    "url": "/bank/:CampusCardID",
    "title": "GetBankInfo",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusCardID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n     \"CampusCardID\":\"213160000\",\n     \"Username\":\"trial\",\n     \"Password\":\"trialpwd\",\n     \"BudgetList\":[\n         {\n             \"BudgetID\":\"123\",\n             \"Asset\":\"10000\",\n             \"Time\":\"2018-7-3 12:00:00\",\n             \"BudgetInfo\":\"\"\n         },\n         {\n             \"BudgetID\":\"189\",\n             \"Asset\":\"-1000\",\n             \"Time\":\"2018-7-4 12:00:00\",\n             \"BudgetInfo\":\"\"\n         }\n     ]\n }",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"WrongUsernameOrPassword\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"UserNotFound\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Bank.cpp",
    "groupTitle": "Bank",
    "name": "GetBankCampuscardid"
  },
  {
    "group": "Bank",
    "type": "patch",
    "url": "/bank/:CampusCardID",
    "title": "FreezeBankAccount",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"WrongUsernameOrPassword\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"UserNotFound\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Bank.cpp",
    "groupTitle": "Bank",
    "name": "PatchBankCampuscardid"
  },
  {
    "group": "Bank",
    "type": "post",
    "url": "/bank/:CampusCardID/",
    "title": "CreateBankAccount",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusCardID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Bank account already created.\"\n}\nHTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"User not found.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Bank.cpp",
    "groupTitle": "Bank",
    "name": "PostBankCampuscardid"
  },
  {
    "group": "Bank",
    "type": "POST",
    "url": "/budget/:CampusCardID",
    "title": "Save/Withdraw/Loan",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusCardID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"Asset\":\"1000\",\n    \"OperationType\":\"Save\"  \n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"WrongUsernameOrPassword\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"UserNotFound\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Bank.cpp",
    "groupTitle": "Bank",
    "name": "PostBudgetCampuscardid"
  },
  {
    "group": "Course",
    "type": "delete",
    "url": "/course/:CourseID",
    "title": "DropCourse",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusCardID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"Course does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Course.cpp",
    "groupTitle": "Course",
    "name": "DeleteCourseCourseid"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course/:CourseID",
    "title": "GetCourseInfo",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n {\n     \"ProfLastName\":\"李\",\n     \"ProfFirstName\":\"四\",\n     \"CourseIntro\":\"null\",\n     \"StuLimit\":\"20\",\n     \"StuList\":[\n         {\n             \"CampusCardID\":\"213160000\",\n             \"LastName\":\"张\"，\n             \"FirstName\":\"三\"\n         },\n         {\n             \"CampusCardID\":\"213160001\",\n             \"LastName\":\"王\"，\n             \"FirstName\":\"五\"\n         }\n     ] \n }",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"Course does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Course.cpp",
    "groupTitle": "Course",
    "name": "GetCourseCourseid"
  },
  {
    "group": "Course",
    "type": "patch",
    "url": "/course/:CourseID",
    "title": "ModifyCourseInfo",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"ProfCampusCardID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"CourseIntro\":\"null\",\n    \"StuLimit\":\"20\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"Professor does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Course.cpp",
    "groupTitle": "Course",
    "name": "PatchCourseCourseid"
  },
  {
    "group": "Course",
    "type": "post",
    "url": "/course",
    "title": "CreateNewCourse",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"ProfCampusCardID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"CourseIntro\":\"null\",\n    \"StuLimit\":\"20\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"Professor does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Course.cpp",
    "groupTitle": "Course",
    "name": "PostCourse"
  },
  {
    "group": "Course",
    "type": "post",
    "url": "/course/:CourseID",
    "title": "SelectCourse",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusCardID\":\"213160000\",\n    \"CourseID\":\"101\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"Course does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Course.cpp",
    "groupTitle": "Course",
    "name": "PostCourseCourseid"
  },
  {
    "group": "Dorm",
    "type": "post",
    "url": "/dorm/:DormID/",
    "title": "GetDormExpenditure/GetDormScoring....",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"DormID\":\"M5D633\",\n  \"CampusCardID\": \"213160000\",\n  \"DormMem\":[\n     {\n        \"CampusCardID\": \"213160000\",\n        \"FirstName\":\"张\",\n        \"LastName\": \"三\",\n         \"ExpenditureList\":[\n             {\n                 \"ID\":\"101334\",\n                 \"CommodityID\":\"732\",\n                 \"PurchaseTime\":\"2018-7-22 09:01:23\",\n                 \"Price\":\"21\"        \n             },\n             {\n                 \"ID\":\"101337\",\n                 \"CommodityID\":\"719\",\n                 \"PurchaseTime\":\"2018-7-28 09:01:23\",\n                 \"Price\":\"25\"        \n             }    \n         ]\n     },\n     {\n        \"CampusCardID\": \"213160001\",\n        \"FirstName\":\"李\",\n        \"LastName\": \"四\"\n        \"ExpenditureList\":\"\"\n     },\n     {\n        \"CampusCardID\": \"213160002\",\n        \"FirstName\":\"王\",\n        \"LastName\": \"五\"\n        \"ExpenditureList\":\"\"\n     }\n ]，\n \"ScoringList\":[\n     {\n         \"ID\":\"101334\",\n         \"ScoringDate\":\"2018-7-22 09:01:23\",\n         \"Score\":\"95\"\n     },\n     {\n         \"ID\":\"101339\",\n         \"ScoringDate\":\"2018-7-29 09:01:23\",\n         \"Score\":\"96\"\n     }\n ]\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Dorm.cpp",
    "groupTitle": "Dorm",
    "name": "PostDormDormid"
  },
  {
    "group": "Library",
    "type": "delete",
    "url": "/Book/:id",
    "title": "ReturnBook",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"Book does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Library.cpp",
    "groupTitle": "Library",
    "name": "DeleteBookId"
  },
  {
    "group": "Library",
    "type": "get",
    "url": "/Book",
    "title": "ListBooks/SearchBooks",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n {\n     \"BookList\":[\n         {\n             \"BookID\":\"1\",\n             \"BookName\":\"How to become a lousy programmer\",\n             \"Writer\":\"BetaCat\",\n             \"Publisher\":\"Southeast University\",\n             \"BookStatus\":\"Borrowed\"\n         },\n         {\n             \"BookID\":\"2\",\n             \"BookName\":\"How to become a very lousy programmer\",\n             \"Writer\":\"BetaCat\",\n             \"Publisher\":\"Southeast University\",\n             \"BookStatus\":\"UnBorrowed\"\n         }        \n     ]\n }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Library.cpp",
    "groupTitle": "Library",
    "name": "GetBook"
  },
  {
    "group": "Library",
    "type": "post",
    "url": "/Book/:id",
    "title": "BorrowBook",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"Book does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Library.cpp",
    "groupTitle": "Library",
    "name": "PostBookId"
  },
  {
    "group": "Store",
    "type": "delete",
    "url": "/Cart/:CommodityID",
    "title": "RemoveFromCart",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CommodityID\":\"10203\",\n    \"CommodityCnt\":\"1\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Store.cpp",
    "groupTitle": "Store",
    "name": "DeleteCartCommodityid"
  },
  {
    "group": "Store",
    "type": "delete",
    "url": "/Purchase/:CommodityID",
    "title": "CancelOrder",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"pwd\",\n    \"CommodityID\":\"10203\",\n    \"CommodityCnt\":\"1\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n} \nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"Commodity does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Store.cpp",
    "groupTitle": "Store",
    "name": "DeletePurchaseCommodityid"
  },
  {
    "group": "Store",
    "type": "get",
    "url": "/Commodity",
    "title": "ListGoods/SearchGoods",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n {\n     \"List\":[\n         {\n             \"CommodityID\":\"1\",\n             \"CommodityProperties\":\"abcdefghijklmn\",\n             \"ProductionDate\":\"2018-8-10\",\n             \"CommodityTot\":\"100\",\n             \"Price\":\"12\"\n         },\n         {\n             \"CommodityID\":\"2\",\n             \"CommodityProperties\":\"abcdefghijklmn\",\n             \"ProductionDate\":\"2018-8-12\",\n             \"CommodityTot\":\"101\",\n             \"Price\":\"5\"\n         }        \n     ]\n }",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Store.cpp",
    "groupTitle": "Store",
    "name": "GetCommodity"
  },
  {
    "group": "Store",
    "type": "post",
    "url": "/Cart/:CommodityID",
    "title": "AddToCart",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CommodityID\":\"10203\",\n    \"CommodityCnt\":\"1\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Store.cpp",
    "groupTitle": "Store",
    "name": "PostCartCommodityid"
  },
  {
    "group": "Store",
    "type": "post",
    "url": "/Commodity",
    "title": "CreateGoods",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"CommodityProperties\":\"abcdefghijklmn\",\n    \"ProductionDate\":\"2018-8-10\",\n    \"CommodityTot\":\"100\",\n    \"Price\":\"12\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Store.cpp",
    "groupTitle": "Store",
    "name": "PostCommodity"
  },
  {
    "group": "Store",
    "type": "post",
    "url": "/Purchase/:CommodityID",
    "title": "CreateOrder",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"pwd\",\n    \"CommodityID\":\"10203\",\n    \"CommodityCnt\":\"1\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password.\"\n} \nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"Commodity does not exist.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Store.cpp",
    "groupTitle": "Store",
    "name": "PostPurchaseCommodityid"
  },
  {
    "group": "StuInfo",
    "type": "delete",
    "url": "/StuInfo/:CampusCardID",
    "title": "DeleteStuInfo",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"CampusID\":\"213160000\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"Stuinfo not yet created\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/StuInfo.cpp",
    "groupTitle": "StuInfo",
    "name": "DeleteStuinfoCampuscardid"
  },
  {
    "group": "StuInfo",
    "type": "get",
    "url": "/StuInfo/:CampusCardID",
    "title": "GetStuInfo",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n {\n     \"CampusID\":\"213160000\",\n     \"SeniorHigh\":\"a\",\n     \"IDNum\":\"320121199800000000\",\n     \"Birthplace\":\"Mars\",\n     \"Sex\":\"0\"\n }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/StuInfo.cpp",
    "groupTitle": "StuInfo",
    "name": "GetStuinfoCampuscardid"
  },
  {
    "group": "StuInfo",
    "type": "patch",
    "url": "/StuInfo/:CampusCardID",
    "title": "ModifyStuInfo",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"CampusID\":\"213160000\",\n    \"SeniorHigh\":\"a\",\n    \"IDNum\":\"320121199800000000\",\n    \"Birthplace\":\"Mars\",\n    \"Sex\":\"0\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Wrong username or password\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"Stuinfo not yet created\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/StuInfo.cpp",
    "groupTitle": "StuInfo",
    "name": "PatchStuinfoCampuscardid"
  },
  {
    "group": "StuInfo",
    "type": "post",
    "url": "/StuInfo",
    "title": "CreateStuInfo",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusID\":\"213160000\",\n    \"SeniorHigh\":\"a\",\n    \"IDNum\":\"320121199800000000\",\n    \"Birthplace\":\"Mars\",\n    \"Sex\":\"0\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"Error\": \"Student info already created.\"\n}\nHTTP/1.1 404 Not Found\n{\n  \"Error\": \"CampusID does not exist\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/StuInfo.cpp",
    "groupTitle": "StuInfo",
    "name": "PostStuinfo"
  }
] });