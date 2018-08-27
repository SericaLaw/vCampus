define({ "api": [
  {
    "group": "Account",
    "type": "post",
    "url": "/account/user/:id",
    "title": "login",
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
          "content": "HTTP/1.1 200 OK\n{\n  \"CampusID\": \"213160000\",\n  \"Lastname\": \"Doe\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"Error\": \"UserNotFound\"\n}",
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
    "filename": "example/Account.cpp",
    "groupTitle": "Account"
  },
  {
    "group": "Account",
    "type": "post",
    "url": "/account/user/",
    "title": "register",
    "name": "RegisterAPI",
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
          "content": "HTTP/1.1 400 Forbidden\n{\n  \"Error\": \"User already created.\"\n}",
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
    "url": "/account/user/",
    "title": "unregister",
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
          "content": "HTTP/1.1 400 Forbidden\n{\n  \"Error\": \"User already created.\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "example/Account.cpp",
    "groupTitle": "Account"
  }
] });
