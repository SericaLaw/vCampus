define({ "api": [
  {
    "group": "Account",
    "type": "post",
    "url": "/account/login",
    "title": "Login ( passed )",
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
          "content": "200 OK\n{\n  \"CampusCardID\": \"213180000\",\n  \"Username\": \"client\",\n  \"Password\": \"123\",\n  \"FirstName\":\"Foo\",\n  \"LastName\": \"Bar\",\n  \"Role\": \"student\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "403 \"Wrong password.\"\n\n404 \"Account not found.\"",
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
    "type": "get",
    "url": "/account/user/:id",
    "title": "Logout",
    "name": "LogoutAPI",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Account not found.\"",
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
    "type": "post",
    "url": "/account",
    "title": "Register ( passed )",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusCardID\":\"213160000\",\n    \"Username\":\"trial\",\n    \"Password\":\"trialpwd\",\n    \"FirstName\":\"trialfirstname\",\n    \"LastName\":\"triallastname\",\n    \"role\":\"student\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "201 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "403 \"Account already exists.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Account.cpp",
    "groupTitle": "Account",
    "name": "PostAccount"
  },
  {
    "group": "Account",
    "type": "delete",
    "url": "/account/campusCardID/:id",
    "title": "UnRegister ( passed )",
    "name": "UnRegisterAPI",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Account not found.\"",
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
    "url": "/course/courseID/:id",
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
    "name": "GetCourseCourseidId"
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
    "url": "/borrowBook/bookID/:bid/campusCardID/:cid",
    "title": "ReturnBook ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>还书</p>",
    "parameter": {
      "examples": [
        {
          "title": "Request Code Snippets",
          "content": "res = api.delete(\"/borrowBook/bookID/101/campusCardID/213170000\");",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response",
          "content": "200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response",
          "content": "404 BorrowBook Not Found",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Library.cpp",
    "groupTitle": "Library",
    "name": "DeleteBorrowbookBookidBidCampuscardidCid"
  },
  {
    "group": "Library",
    "type": "get",
    "url": "/book",
    "title": "GetBookList ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>获得图书数据表中前20条信息，用以展示图书馆书目信息，查询结果为空则返回&quot;[]&quot;</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_Book",
            "description": "<p>book list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "200 OK\n[   \n    {\n        \"Publisher\":\"清华大学出版社\",\n        \"BookID\":\"101\",\n        \"BookName\":\"数据结构\",\n        \"TotalCount\":\"5\",\n        \"Writer\":\"邓俊辉\",\n        \"AvailableCount\":\"4\"\n    },\n    {\n        \"Publisher\":\"清华大学出版社\",\n        \"BookID\":\"102\",\"BookName\":\"机器学习\",\n        \"TotalCount\":\"4\",\n        \"Writer\":\"周志华\",\n        \"AvailableCount\":\"2\"\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Request Code Snippets",
          "content": "res = api.get(\"/book\");",
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
    "type": "get",
    "url": "/book/bookName/:keyword/like",
    "title": "SearchBook ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>模糊搜索图书，传入书名关键词keyword</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_Book",
            "description": "<p>book list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "200 OK\n[\n    {\n        \"Publisher\":\"清华大学出版社\",\n        \"BookID\":\"102\",\n        \"BookName\":\"机器学习\",\n        \"TotalCount\":\"4\",\n        \"Writer\":\"周志华\",\n        \"AvailableCount\":\"2\"\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Request Code Snippets",
          "content": "res = api.get(\"/book/bookName/机器/like\");",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Library.cpp",
    "groupTitle": "Library",
    "name": "GetBookBooknameKeywordLike"
  },
  {
    "group": "Library",
    "type": "get",
    "url": "/borrowBook",
    "title": "GetBorrowedBook ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>获取在借图书列表</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_BorroedBook",
            "description": "<p>在借图书list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "200 OK\n[\n    {\n        \"Publisher\":\"清华大学出版社\",\n        \"BookID\":\"102\",\n        \"BorrowDate\":\"2018-09-01 14:26:34.0\",\n        \"ExpiryDate\":\"2018-10-01 14:26:34.0\",\n        \"BookName\":\"机器学习\",\"Writer\":\"周志华\"\n    },\n    {\n        \"Publisher\":\"南海出版公司\",\n        \"BookID\":\"103\",\n        \"BorrowDate\":\"2018-09-01 14:26:44.0\",\n        \"ExpiryDate\":\"2018-10-01 14:26:44.0\",\n        \"BookName\":\"白夜行\",\n        \"Writer\":\"东野圭吾\"\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Request Code Snippets",
          "content": "res = api.get(\"/borrowBook\");",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Library.cpp",
    "groupTitle": "Library",
    "name": "GetBorrowbook"
  },
  {
    "group": "Library",
    "type": "post",
    "url": "/borrowBook",
    "title": "BorrowBook ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>借书：注意后端不会判断书籍能否借阅（即availableCount是否大于0），提交请求前前端必须进行验证，不能借阅的书不得出请求</p>",
    "parameter": {
      "examples": [
        {
          "title": "Request Code Snippets",
          "content": "BorrowBookRecord borrowBookRecord = new BorrowBookRecord(\"101\",\"213170000\", new Date());\njsonData = JSON.toJSONString(borrowBookRecord, SerializerFeature.WriteDateUseDateFormat);\nres = api.post(\"/borrowBook\", jsonData);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response",
          "content": "404 BorrowBook Not Found",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Library.cpp",
    "groupTitle": "Library",
    "name": "PostBorrowbook"
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
    "url": "~/stuInfo/campusCardID/:id",
    "title": "DeleteStuInfo ( passed )",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404  \"StuInfo not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/StuInfo.cpp",
    "groupTitle": "StuInfo",
    "name": "DeleteStuinfoCampuscardidId"
  },
  {
    "group": "StuInfo",
    "type": "get",
    "url": "~/stuInfo/campusCardID/:id",
    "title": "GetStuInfo ( passed )",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n {\n     \"campusCardID\":\"213160000\",\n     \"studentID\":\"09016101\"\n     \"seniorHigh\":\"a\",\n     \"IDNum\":\"320121199800000000\",\n     \"birthplace\":\"Mars\",\n     \"sex\":\"male\",\n     \"department\":\"School of CS\",\n     \"major\":\"Computer Science\"\n }",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"StuInfo not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/StuInfo.cpp",
    "groupTitle": "StuInfo",
    "name": "GetStuinfoCampuscardidId"
  },
  {
    "group": "StuInfo",
    "type": "patch",
    "url": "~/stuInfo/campusCardID/:id",
    "title": "ModifyStuInfo ( passed )",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"CampusCardID\":\"213160000\",\n    \"SeniorHigh\":\"a\",\n    \"IDNum\":\"320121199800000000\",\n    \"Birthplace\":\"Mars\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"StuInfo not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/StuInfo.cpp",
    "groupTitle": "StuInfo",
    "name": "PatchStuinfoCampuscardidId"
  },
  {
    "group": "StuInfo",
    "type": "post",
    "url": "~/stuInfo",
    "title": "CreateStuInfo ( passed )",
    "parameter": {
      "examples": [
        {
          "title": "JSON-Request:",
          "content": "{\n    \"campusCardID\":\"213160000\",\n    \"studentID\":\"09016101\"\n    \"seniorHigh\":\"a\",\n    \"IDNum\":\"320121199800000000\",\n    \"birthplace\":\"Mars\",\n    \"sex\":\"男\",\n    \"department\":\"School of CS\",\n    \"major\":\"Computer Science\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "201 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "403  \"StuInfo already exists.\"\n\n404  \"StuInfo not found.\"",
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
