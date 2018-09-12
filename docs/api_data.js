define({ "api": [
  {
    "group": "Account",
    "type": "get",
    "url": "/account/login",
    "title": "Login",
    "permission": [
      {
        "name": "all"
      }
    ],
    "description": "<p>登录</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "AccountViewModel",
            "description": "<p>AccountViewModel</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n{\n    \"CampusCardID\": \"213180000\",\n    \"Username\": \"client\",\n    \"Password\": \"123\",\n    \"FirstName\":\"Foo\",\n    \"LastName\": \"Bar\",\n    \"Role\": \"student\"\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "LoginViewModel login = new LoginViewModel(username, password);\nWebResponse res = api.post(\"/account/login\", JSON.toJSONString(login));",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "403 \"Wrong password.\"\n404 \"Account not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/AccountController.java",
    "groupTitle": "Account",
    "name": "GetAccountLogin"
  },
  {
    "group": "Account",
    "type": "post",
    "url": "/account",
    "title": "CreateAccount",
    "description": "<p>创建账号和学生信息，注意账号的campusCardId和StuInfo表关联，所以必须先创建账号，有初始化的密码和昵称。</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "AccountViewModel newAccount = new AccountViewModel();\nnewAccount.setCampusCardId(\"213190000\");\nnewAccount.setFirstName(\"新\");\nnewAccount.setLastName(\"生\");\nnewAccount.setRole(\"student\");\nnewAccount.setNickname(\"昵称\");\nnewAccount.setPassword(\"09019000\");\n\nWebResponse res = api.post(\"/account\", JSON.toJSONString(newAccount));",
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
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/AccountController.java",
    "groupTitle": "Account",
    "name": "PostAccount"
  },
  {
    "group": "Bank",
    "type": "get",
    "url": "/bank/info",
    "title": "GetBankInfo",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>获取银行信息</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_BankInfoViewModel",
            "description": "<p>List of BankInfoViewModel</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/bank/info\");\nList<BankInfoViewModel> bankInfoList = res.dataList(BankInfoViewModel.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/BankController.java",
    "groupTitle": "Bank",
    "name": "GetBankInfo"
  },
  {
    "group": "Course",
    "type": "delete",
    "url": "/course/register/{courseId}",
    "title": "RegisterCourse",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>退课</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "200",
            "description": "<p>-&gt; OK</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": ":",
            "description": "<p>400 -&gt; Already empty 403 -&gt; Please view course list first 404 -&gt; Course not selected</p>"
          }
        ]
      }
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.delete(\"/course/register/{courseId}\");",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/CourseController.java",
    "groupTitle": "Course",
    "name": "DeleteCourseRegisterCourseid"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course",
    "title": "GetCourseList",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "description": "<p>获取课程列表</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_CourseRegisterViewModel",
            "description": "<p>List of CourseRegisterViewModel</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[{\n    \"CourseName\":\"JAVA程序设计\",\n    \"Semester\":\"2\",\n    \"CourseVenue\":\"J2-203\",\n    \"Grade\":\"3\",\n    \"StuLimitCount\":\"3\",\n    \"ExamDate\":\"2019-01-20 00:00:00.0\",\n    \"CourseID\":\"1001\",\n    \"Credit\":\"2\",\n    \"Major\":\"计算机科学与技术\",\n    \"ProfName\":\"沈傲东\",\n    \"Intro\":\"大佬的课不得不听啊\",\n    \"ExamVenue\":\"J4-302\",\n    \"StuAttendCount\":\"1\",\n    \"ProfCampusCardID\":\"1001\"\n   },{\n    \"CourseName\":\"高等数学\",\n    \"Semester\":\"2\",\n    \"CourseVenue\":\"J8-103\",\n    \"Grade\":\"1\",\n    \"StuLimitCount\":\"3\",\n    \"ExamDate\":\"2019-01-18 00:00:00.0\",\n    \"CourseID\":\"2001\",\n    \"Credit\":\"5\",\n    \"ProfName\":\"牛顿\",\n    \"Intro\":\"同上\",\n    \"ExamVenue\":\"J4-104\",\n    \"StuAttendCount\":\"2\",\n    \"ProfCampusCardID\":\"2001\"\n}]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/course\");\nList<CourseRegisterViewModel> courseRegisterViewModels = res.dataList(CourseRegisterViewModel.class);",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Course not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/CourseController.java",
    "groupTitle": "Course",
    "name": "GetCourse"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course/register",
    "title": "GetCourseRegister",
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
            "field": "CourseRegisterViewModel",
            "description": "<p>CourseRegisterViewModel对象</p>"
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
          "content": "WebResponse res = api.get(\"/course/register\");\nCourseRegisterViewModel courseRegister = res.data(CourseRegisterViewModel.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/CourseController.java",
    "groupTitle": "Course",
    "name": "GetCourseRegister"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course/report",
    "title": "GetCourseReport",
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
            "field": "List_CourseReportViewModel",
            "description": "<p>装着课程表表项的list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[\n    {\n        \"Semester\":\"17-18-3\",\n        \"Credit\":2,\n        \"CourseName\":\"中国古建筑鉴赏\",\n        \"ScoreType\":\"首修\",\n        \"Score\":90\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/course/report\");\nList<CourseReportViewModel> reportItems = res.dataList(CourseReportViewModel.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/CourseController.java",
    "groupTitle": "Course",
    "name": "GetCourseReport"
  },
  {
    "group": "Course",
    "type": "get",
    "url": "/course/schedule",
    "title": "GetCourseSchedule",
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
            "field": "List_CourseScheduleViewModel",
            "description": "<p>装着课程表表项的list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[\n    {\n        \"SpanEnd\":2,\n        \"ProfName\":\"牛顿\",\n        \"CourseID\":\"2001\",\n        \"CourseName\":\"高等数学\",\n        \"CourseVenue\":\"J8-103\",\n        \"WeekDay\":2,\n        \"SpanStart\":1\n    },\n    {\n        \"SpanEnd\":4,\n        \"ProfName\":\"牛顿\",\n        \"CourseID\":\"2001\",\n        \"CourseName\":\"高等数学\",\n        \"CourseVenue\":\"J8-103\",\n        \"WeekDay\":4,\n        \"SpanStart\":3\n    },\n    {\n        \"SpanEnd\":4,\n        \"ProfName\":\"亚里士多德\",\n        \"CourseID\":\"3001\",\n        \"CourseName\":\"大学物理\",\n        \"CourseVenue\":\"J6-101\",\n        \"WeekDay\":1,\n        \"SpanStart\":3\n    },\n    {\n        \"SpanEnd\":7,\n        \"ProfName\":\"亚里士多德\",\n        \"CourseID\":\"3001\",\n        \"CourseName\":\"大学物理\",\n        \"CourseVenue\":\"J6-101\",\n        \"WeekDay\":3,\n        \"SpanStart\":6\n    }\n]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/course/schedule\");\nList<CourseScheduleViewModel> scheduleItems = res.dataList(CourseScheduleViewModel.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/CourseController.java",
    "groupTitle": "Course",
    "name": "GetCourseSchedule"
  },
  {
    "group": "Course",
    "type": "patch",
    "url": "/course/record",
    "title": "ModifyCourseRecord",
    "permission": [
      {
        "name": "teacher"
      }
    ],
    "description": "<p>登记或改变学生成绩</p>",
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.patch(\"/course/record\", \"{\"id\":\"uuid\", \"score\":\"the score\"}\");\nList<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);",
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
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/CourseController.java",
    "groupTitle": "Course",
    "name": "PatchCourseRecord"
  },
  {
    "group": "Course",
    "type": "post",
    "url": "/course/register",
    "title": "RegisterCourse",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>选课</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "200",
            "description": "<p>-&gt; OK</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>-&gt; Course is full 403 -&gt; Please view course list first 403 -&gt; Course Conflict</p>"
          }
        ]
      }
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.post(\"/course/register\", courseId);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/CourseController.java",
    "groupTitle": "Course",
    "name": "PostCourseRegister"
  },
  {
    "group": "Dorm",
    "type": "get",
    "url": "/dorm/info",
    "title": "GetDormInfo",
    "permission": [
      {
        "name": "all"
      }
    ],
    "description": "<p>获取宿舍信息</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_DormInfoViewModel",
            "description": "<p>List of DormInfoViewModel</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/dorm/info\");\nList<DormInfoViewModel> dormInfoList = res.dataList(DormInfoViewModel.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/DormController.java",
    "groupTitle": "Dorm",
    "name": "GetDormInfo"
  },
  {
    "group": "Library",
    "type": "delete",
    "url": "/book/bookId/{bookId}",
    "title": "DeleteBook",
    "description": "<p>删除图书</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "// 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可\nBookViewModel bookToDelete = new BookViewModel();\nbookToDelete.setBookId(\"404\");\n// 下面开始删除\napi.delete(\"/book/bookId/\" + bookToDelete.getBookId());",
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
          "content": "404 \"Book not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "DeleteBookBookidBookid"
  },
  {
    "group": "Library",
    "type": "delete",
    "url": "/library/borrow",
    "title": "DeleteBorrowRecords",
    "description": "<p>还书</p>",
    "permission": [
      {
        "name": "student"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.delete(\"/library/borrow/\" + borrowRecordViewModel.getId());",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 \"Entry Successfully deleted\"",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Entry does not exist!\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "DeleteLibraryBorrow"
  },
  {
    "group": "Library",
    "type": "get",
    "url": "/book/bookId/{bookId}",
    "title": "GetBookById",
    "description": "<p>按ID查询图书</p>",
    "permission": [
      {
        "name": "student admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "String bookId = \"101\";\nWebResponse res = api.get(\"/book/bookId/\" + bookId);\nBookViewModel book = res.dataList(BookViewModel.class, 0);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[{\n     \"TotalCount\":\"10\",\n     \"BookID\":\"101\",\n     \"BookName\":\"数据结构\",\n     \"Publisher\":\"清华大学出版社\",\n     \"AvailableCount\":\"10\",\n     \"Writer\":\"邓俊辉\"\n}]",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Book not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "GetBookBookidBookid"
  },
  {
    "group": "Library",
    "type": "get",
    "url": "/library/book",
    "title": "GetBookList",
    "description": "<p>获取图书列表</p>",
    "permission": [
      {
        "name": "student admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "\nWebResponse res = api.get(\"/library/book\");\nList<BookViewModel> bookList = res.dataList(BookViewModel.class);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Book not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "GetLibraryBook"
  },
  {
    "group": "Library",
    "type": "get",
    "url": "/library/borrow",
    "title": "GetBorrowRecords",
    "description": "<p>获取图书列表</p>",
    "permission": [
      {
        "name": "student admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "\nWebResponse res = api.get(\"/library/book\");\nList<BookViewModel> bookList = res.dataList(BookViewModel.class);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Book not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "GetLibraryBorrow"
  },
  {
    "group": "Library",
    "type": "patch",
    "url": "/book/bookId/{bookId}",
    "title": "ModifyBook",
    "description": "<p>修改图书</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "// 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可\nBookViewModel bookToModify = new BookViewModel();\nbookToModify.setBookId(\"101\");\nbookToModify.setBookName(\"数据结构\");\nbookToModify.setWriter(\"邓俊辉\");\nbookToModify.setPublisher(\"清华大学出版社\");\nbookToModify.setAvailableCount(5);\nbookToModify.setTotalCount(5);\n\n// 下面开始更新\nbookToModify.setAvailableCount(10);\nbookToModify.setTotalCount(10);\n\napi.patch(\"/book/bookId/\" + bookToModify.getBookId(), JSON.toJSONString(bookToModify));",
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
          "content": "404 \"Book not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "PatchBookBookidBookid"
  },
  {
    "group": "Library",
    "type": "post",
    "url": "/book",
    "title": "CreateBook",
    "description": "<p>添加图书</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "BookViewModel newBook = new BookViewModel();\nnewBook.setAvailableCount(5);\nnewBook.setBookId(\"404\");\nnewBook.setBookName(\"C++从入门到入土\");\nnewBook.setPublisher(\"东南大学出版社\");\nnewBook.setTotalCount(5);\nnewBook.setWriter(\"叶神\");\n\napi.post(\"/book\", JSON.toJSONString(newBook));",
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
          "content": "403 \"Book already exists.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "PostBook"
  },
  {
    "group": "Library",
    "type": "post",
    "url": "/library/book",
    "title": "NewBorrowRecords",
    "description": "<p>借书</p>",
    "permission": [
      {
        "name": "student"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "\nWebResponse res = api.post(\"/library/borrow\", bookViewModel.getBookId());",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "201 \"Successfully created\"\n......",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "400 \"Incorrect body data\";\n400 \"No corresponding book found\"\n400 \"No available book now\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "PostLibraryBook"
  },
  {
    "group": "Library",
    "type": "post",
    "url": "/library/book",
    "title": "SearchForBooks",
    "description": "<p>按书名模糊搜索图书</p>",
    "permission": [
      {
        "name": "student admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "\nWebResponse res = api.post(\"/library/book\", keyword);\nList<BookViewModel> bookList = res.dataList(BookViewModel.class);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Book not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/LibraryController.java",
    "groupTitle": "Library",
    "name": "PostLibraryBook"
  },
  {
    "group": "Store",
    "type": "delete",
    "url": "/goods/goodsId/{goodsId}",
    "title": "DeleteGoods",
    "description": "<p>删除商品</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "// 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可\nGoodsViewModel goodsToDelete = new GoodsViewModel();\ngoodsToDelete.setGoodsId(\"66b20429-922e-45f9-aeb3-6c7f4fdeaff1\");\n// 下面开始删除\napi.delete(\"/goods/goodsId/\" + goodsToDelete.getGoodsId());",
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
          "content": "404 \"Goods not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "DeleteGoodsGoodsidGoodsid"
  },
  {
    "group": "Store",
    "type": "get",
    "url": "/goods",
    "title": "GetGoodsList",
    "permission": [
      {
        "name": "all"
      }
    ],
    "description": "<p>获取商品列表</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_GoodsViewModel",
            "description": "<p>List of GoodsViewModel</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[{\n    \"Price\":\"1000.0\",\n    \"Tag\":\"1\",\n    \"Info\":\"An oooooold thinkpad version.\",\n    \"ImgUrl\":\"http://nonexistent.com\",\n    \"GoodsID\":\"c14eb7df-0624-421a-8c3f-d1b8b016c5db\",\n    \"GoodsName\":\"Thinkpad T61\"\n  },{\n    \"Price\":\"100000.0\",\n    \"Tag\":\"2\",\n    \"Info\":\"Quite tough.\",\n    \"ImgUrl\":\"http://nonexistent.com\",\n    \"GoodsID\":\"dafa0250-e44b-42aa-8cfb-a5f5a9078d8f\",\n    \"GoodsName\":\"Thinkpad X1 Carbon\"\n}]",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/goods\");\nList<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "GetGoods"
  },
  {
    "group": "Store",
    "type": "get",
    "url": "/goods/goodsId/{goodsId}",
    "title": "GetGoodsById",
    "description": "<p>按ID查询商品</p>",
    "permission": [
      {
        "name": "student admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "String goodsId = \"66b20429-922e-45f9-aeb3-6c7f4fdeaff1\";\nWebResponse res = api.get(\"/goods/goodsId/\" + goodsId);\nGoodsViewModel goods = res.dataList(GoodsViewModel.class, 0);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n[{\n     \"Price\":\"10000.0\",\n     \"Tag\":\"1\",\n     \"Info\":\"罗永浩子公司荣誉出品\",\n     \"ImgUrl\":\"./images/item.png\",\n     \"GoodsID\":\"66b20429-922e-45f9-aeb3-6c7f4fdeaff1\",\n     \"GoodsName\":\"iPhone 100\"\n}]",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "404 \"Goods not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "GetGoodsGoodsidGoodsid"
  },
  {
    "group": "Store",
    "type": "get",
    "url": "/store/cart",
    "title": "GetCartList",
    "permission": [
      {
        "name": "student teacher"
      }
    ],
    "description": "<p>获取购物车列表</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "List_CartRecordViewModel",
            "description": "<p>List of CartRecordViewModel</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "WebResponse res = api.get(\"/store/cart\");\nList<CartRecordViewModel> cartList = res.dataList(CartRecordViewModel.class);",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "GetStoreCart"
  },
  {
    "group": "Store",
    "type": "patch",
    "url": "/goods/goodsId/{goodsId}",
    "title": "ModifyBook",
    "description": "<p>修改商品</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "// 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可\nGoodsViewModel goodsToModify = new GoodsViewModel();\ngoodsToModify.setGoodsId(\"66b20429-922e-45f9-aeb3-6c7f4fdeaff1\");\ngoodsToModify.setGoodsName(\"iPhone 100\");\ngoodsToModify.setPrice(10000);\ngoodsToModify.setImgUrl(\"./images/item.png\");\ngoodsToModify.setTag(1);\n\n// 下面开始更新\ngoodsToModify.setInfo(\"罗永浩子公司荣誉出品\");\napi.patch(\"/goods/goodsId/\" + goodsToModify.getGoodsId(), JSON.toJSONString(goodsToModify));",
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
          "content": "404 \"Goods not found.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "PatchGoodsGoodsidGoodsid"
  },
  {
    "group": "Store",
    "type": "post",
    "url": "/goods",
    "title": "CreateGoods",
    "description": "<p>添加商品</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "GoodsViewModel newGoods = new GoodsViewModel();\nnewGoods.setGoodsName(\"iPhone 100\");\nnewGoods.setImgUrl(\"./images/item.png\");\nnewGoods.setPrice(10000.);\nnewGoods.setTag(1);\n\nWebResponse res = api.post(\"/goods\", JSON.toJSONString(newGoods));",
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
          "content": "403 \"Goods already exists.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "PostGoods"
  },
  {
    "group": "Store",
    "type": "post",
    "url": "/store/cart",
    "title": "AddToCart",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>添加至购物车(cart record没有该商品)</p>",
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "String goodsId = goodsViewModel.getGoodsId();\nWebResponse res = api.post(\"/store/cart\", goodsId);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "PostStoreCart"
  },
  {
    "group": "Store",
    "type": "post",
    "url": "/store/clear",
    "title": "DeleteCartGoods",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>删除购物车商品</p>",
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "String uuid1 = cartRecordViewModel1.getCartRecordID();\nString uuid2 = cartRecordViewModel2.getCartRecordID();\nWebResponse res = api.post(\"/store/clear\", String.format(\"[%s, %s]\", uuid1, uuid2);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "PostStoreClear"
  },
  {
    "group": "Store",
    "type": "post",
    "url": "/store/purchase",
    "title": "Purchase",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>支付 删除CartRecord表相应购物车数据；Bank表里余额需要修改</p>",
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "String uuid1 = cartRecordViewModel1.getCartRecordID();\nString uuid2 = cartRecordViewModel2.getCartRecordID();\nList<String> purchases = new ArrayList<>();\npurchases.add(uuid1);\npurchases.add(uuid2);\nWebResponse res = api.post(\"/store/purchase\", JSON.toJSONString(purchases, String.class));",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "Store",
    "name": "PostStorePurchase"
  },
  {
    "group": "StuInfo",
    "type": "get",
    "url": "/stuInfo/campusCardID/{uid}",
    "title": "GetStuInfoById",
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
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StuInfoController.java",
    "groupTitle": "StuInfo",
    "name": "GetStuinfoCampuscardidUid"
  },
  {
    "group": "StuInfo",
    "type": "patch",
    "url": "/stuInfo/campusCardID/:id",
    "title": "ModifyStuInfo",
    "description": "<p>修改学生信息，该API不会返回修改后的StuInfo，若服务器告知修改成功，则前端自行对数据进行修改然后展示到界面上</p>",
    "permission": [
      {
        "name": "student admin"
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
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StuInfoController.java",
    "groupTitle": "StuInfo",
    "name": "PatchStuinfoCampuscardidId"
  },
  {
    "group": "StuInfo",
    "type": "post",
    "url": "/stuInfo",
    "title": "CreateStuInfo",
    "description": "<p>创建账号和学生信息，注意账号的campusCardId和StuInfo表关联，所以必须先创建账号，有初始化的密码和昵称。</p>",
    "permission": [
      {
        "name": "admin"
      }
    ],
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "AccountViewModel newAccount = new AccountViewModel();\nnewAccount.setCampusCardId(\"213190000\");\nnewAccount.setFirstName(\"新\");\nnewAccount.setLastName(\"生\");\nnewAccount.setRole(\"student\");\nnewAccount.setNickname(\"昵称\");\nnewAccount.setPassword(\"09019000\");\n\nStuInfoViewModel newStudent = new StuInfoViewModel();\nnewStudent.setEnrollmentYear(new Integer(2019));\nnewStudent.setCampusCardId(\"213190000\");\nnewStudent.setStudentId(\"09019000\");\nnewStudent.setDepartment(\"计算机科学与工程学院\");\nnewStudent.setMajor(\"计算机科学与技术\");\n\nWebResponse res = api.post(\"/account\", JSON.toJSONString(newAccount));\nif(res.getStatusCode().equals(\"201\"))\n   api.post(\"/stuInfo\", JSON.toJSONString(newStudent));",
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
          "content": "403 \"StuInfo already exists.\"",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StuInfoController.java",
    "groupTitle": "StuInfo",
    "name": "PostStuinfo"
  },
  {
    "group": "_api__patch___store_cart_ModifyCart",
    "permission": [
      {
        "name": "student"
      }
    ],
    "description": "<p>增加商品数量(cart record已有该商品)</p>",
    "parameter": {
      "examples": [
        {
          "title": "Code Snippets",
          "content": "String uuid = cartRecordViewModel.getCartRecordID();\nint count = cartRecordViewModel.getGoodsCount();\nWebResponse res = api.patch(\"/store/cart\", String.format(\"{\\\"uuid\\\": %s, \\\"count\\\":%d}\", uuid, count);",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "200 OK\n......Store",
          "type": "json"
        }
      ]
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api/StoreController.java",
    "groupTitle": "_api__patch___store_cart_ModifyCart",
    "name": ""
  }
] });
