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
    "filename": "vCampus/vCampusClient/src/test/java/Account.cpp",
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
    "filename": "vCampus/vCampusClient/src/test/java/Account.cpp",
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
    "filename": "vCampus/vCampusClient/src/test/java/Account.cpp",
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
    "parameter": {
      "examples": [
        {
          "title": "@apiErrorExample Error-Response:",
          "content": "@apiErrorExample Error-Response:\n    404 \"Account not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusClient/src/test/java/Account.cpp",
    "groupTitle": "Account"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course/register",
    "title": "GetCourseRegister ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>获取当前学期的选课列表</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "CourseRegister",
            "description": "<p>CourseRegister对象</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n{\n    \"courseList\":[{\n        \"courseID\":\"2001\",\n        \"courseName\":\"高等数学\",\n        \"courseSchedule\":[{\n            \"spanEnd\":2,\n            \"spanStart\":1,\n            \"weekDay\":2\n         },{\n            \"spanEnd\":4,\n            \"spanStart\":3,\n            \"weekDay\":4\n         }],\n         \"courseVenue\":\"J8-103\",\n         \"credit\":\"5\",\n         \"profName\":\"牛顿\",\n         \"status\":\"SELECTED\",\n         \"stuAttendCount\":2,\n         \"stuLimitCount\":40\n     },{\n        \"courseID\":\"3001\",\n        \"courseName\":\"大学物理\",\n        \"courseSchedule\":[{\n            \"spanEnd\":4,\n            \"spanStart\":3,\n            \"weekDay\":1\n        },{\n            \"spanEnd\":7,\n            \"spanStart\":6,\n            \"weekDay\":3\n        }],\n        \"courseVenue\":\"J6-101\",\n        \"credit\":\"3\",\n        \"profName\":\"亚里士多德\",\n        \"status\":\"SELECTED\",\n        \"stuAttendCount\":2,\n        \"stuLimitCount\":40\n    },{\n        \"courseID\":\"5001\",\n        \"courseName\":\"中国古建筑鉴赏\",\n        \"courseSchedule\":[{\n            \"spanEnd\":13,\n            \"spanStart\":11,\n            \"weekDay\":1\n        },{\n            \"spanEnd\":5,\n            \"spanStart\":3,\n            \"weekDay\":3\n        }],\n            \"courseVenue\":\"J6-102\",\n            \"credit\":\"2\",\n            \"profName\":\"梁思成\",\n            \"status\":\"AVAILABLE\",\n            \"stuAttendCount\":1,\n            \"stuLimitCount\":60\n        },{\n            \"courseID\":\"5002\",\n            \"courseName\":\"果壳中的宇宙\",\n            \"courseSchedule\":[{\n                \"spanEnd\":13,\n                \"spanStart\":11,\n                \"weekDay\":4\n            }],\n            \"courseVenue\":\"J6-103\",\n            \"credit\":\"2\",\"profName\":\"霍金\",\n            \"status\":\"AVAILABLE\",\n            \"stuAttendCount\":0,\n            \"stuLimitCount\":60\n        },{\n            \"courseID\":\"5003\",\n            \"courseName\":\"时装艺术鉴赏\",\n            \"courseSchedule\":[{\n                \"spanEnd\":5,\n                \"spanStart\":3,\n                \"weekDay\":4\n            }],\n            \"courseVenue\":\"J3-203\",\n            \"credit\":\"2\",\n            \"profName\":\"哥斯拉\",\n            \"status\":\"CONFLICT\",\n            \"stuAttendCount\":0,\n            \"stuLimitCount\":60\n        }\n    ]}\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/course/register\");\nCourseRegister courseRegister = res.data(CourseRegister.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusClient/src/test/java/CourseApiTest.java",
    "groupTitle": "Course",
    "name": "GetCourseRegister"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course/report",
    "title": "GetCourseReport ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>获取成绩信息，直接返回所有学期的成绩信息，不做按学期的单独查询；默认0分为未给分的课程，不会包含在返回的列表里</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_CourseReportItem",
            "description": "<p>装着课程表表项的list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[\n    {\n        \"Semester\":\"17-18-3\",\n        \"Credit\":\"2\",\n        \"CourseName\":\"中国古建筑鉴赏\",\n        \"ScoreType\":\"首修\",\n        \"Score\":\"90\"\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/course/report\");\nList<CourseReportItem> reportItems = res.dataList(CourseReportItem.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusClient/src/test/java/CourseApiTest.java",
    "groupTitle": "Course",
    "name": "GetCourseReport"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course/schedule",
    "title": "GetCourseSchedule ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>获取本学期课程表，暂不支持不同学期课程表的查询</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_CourseScheduleItem",
            "description": "<p>装着课程表表项的list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[\n    {\n        \"SpanEnd\":\"2\",\n        \"ProfName\":\"牛顿\",\n        \"CourseID\":\"2001\",\n        \"CourseName\":\"高等数学\",\n        \"CourseVenue\":\"J8-103\",\n        \"WeekDay\":\"2\",\n        \"SpanStart\":\"1\"\n    },\n    {\n        \"SpanEnd\":\"4\",\n        \"ProfName\":\"牛顿\",\n        \"CourseID\":\"2001\",\n        \"CourseName\":\"高等数学\",\n        \"CourseVenue\":\"J8-103\",\n        \"WeekDay\":\"4\",\n        \"SpanStart\":\"3\"\n    },\n    {\n        \"SpanEnd\":\"4\",\n        \"ProfName\":\"亚里士多德\",\n        \"CourseID\":\"3001\",\n        \"CourseName\":\"大学物理\",\n        \"CourseVenue\":\"J6-101\",\n        \"WeekDay\":\"1\",\n        \"SpanStart\":\"3\"\n    },\n    {\n        \"SpanEnd\":\"7\",\n        \"ProfName\":\"亚里士多德\",\n        \"CourseID\":\"3001\",\n        \"CourseName\":\"大学物理\",\n        \"CourseVenue\":\"J6-101\",\n        \"WeekDay\":\"3\",\n        \"SpanStart\":\"6\"\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/course/schedule\");\nList<CourseScheduleItem> scheduleItems = res.dataList(CourseScheduleItem.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusClient/src/test/java/CourseApiTest.java",
    "groupTitle": "Course",
    "name": "GetCourseSchedule"
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
    "filename": "vCampus/vCampusClient/src/test/java/Library.cpp",
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
    "filename": "vCampus/vCampusClient/src/test/java/Library.cpp",
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
    "filename": "vCampus/vCampusClient/src/test/java/Library.cpp",
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
    "filename": "vCampus/vCampusClient/src/test/java/Library.cpp",
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
    "filename": "vCampus/vCampusClient/src/test/java/Library.cpp",
    "groupTitle": "Library",
    "name": "PostBorrowbook"
  },
  {
    "group": "StuInfo",
    "type": "get",
    "url": "/stuInfo/campusCardID/:id",
    "title": "GetStuInfo ( passed )",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>获取学生信息</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_StuInfo",
            "description": "<p>只含有一个项的StuInfo list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[\n    {\n        \"LectureAttendCount\":\"20\",\n        \"Phone\":\"110\",\n        \"Major\":\"计算机科学与技术\",\n        \"SeniorHigh\":\"霍格沃茨魔法学校\",\n        \"IDNum\":\"1234567\",\n        \"GPA\":\"4.81\",\n        \"EnrollmentYear\":\"2018\",\n        \"StudentID\":\"09018101\",\n        \"Birthplace\":\"临冬城\",\n        \"Email\":\"boss@microsoft.com#mailto:boss@microsoft.com#\",\n        \"SRTP\":\"100.0\",\n        \"Address\":\"贝克街221B\",\n        \"Birthdate\":\"1998-06-04 00:00:00.0\",\n        \"Department\":\"计算机科学与工程学院\",\n        \"CampusCardID\":\"213180000\",\n        \"Sex\":\"男\"\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/stuInfo/campusCardID/213180000\");\nStuInfo stuInfoGot = res.dataList(StuInfo.class, 0);",
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
    "filename": "vCampus/vCampusClient/src/test/java/StuInfoApiTest.java",
    "groupTitle": "StuInfo",
    "name": "GetStuinfoCampuscardidId"
  },
  {
    "group": "StuInfo",
    "type": "patch",
    "url": "/stuInfo/campusCardID/:id",
    "title": "ModifyStuInfo ( passed )",
    "description": "<p>修改学生信息，该API不会返回修改后的StuInfo，若服务器告知修改成功，则前端自行对数据进行修改然后展示到界面上</p>",
    "permission": [
      {
        "name": "student"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "JSONObject infoToModify = new JSONObject();\ninfoToModify.put(\"Phone\", \"120\");\ninfoToModify.put(\"Sex\",\"女\");\napi.patch(\"/stuInfo/campusCardID/213180000\", infoToModify.toJSONString());",
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
    "filename": "vCampus/vCampusClient/src/test/java/StuInfoApiTest.java",
    "groupTitle": "StuInfo",
    "name": "PatchStuinfoCampuscardidId"
  }
] });
