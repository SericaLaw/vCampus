# vCampusServer

## vCampus后端目录结构

| 路径                            | 说明                             |
| ------------------------------- | -------------------------------- |
| `./lib`                         | 后端依赖                         |
|  `./test`       	              |测试程序所在目录，里面有一个模拟的数据库。`ApiTest.java`文件里包括所有已经测试过可用的API。|
| `./src/vCampus/models`          | 暂时使用的模型，相关技术（如是否使用JavaFX的数据绑定）确定后可以放到根目录下的`vCampusCommon/models`中，以便前后端共同使用 |
| `./src/vCampus/server/api`      | api源代码                        |
| `./src/vCampus/server/database` | 数据库交互相关工具类             |
| `./src/vCampus/server/http`     | http请求包装类：`HttpRequest`, `HttpResponse`, `RequestMethod` |
| `./src/vCampus/server`          | 服务器主程序和服务器线程执行程序 |

## 目前支持的API

详见`ApiTest.java`，写程序的时候发现这样比较合理一些，和胡大佬的文档有点出入orz：

**下面的方法经测试都可以跑通，你可以编写代码自己进行测试，先运行`Server.java`开启服务器，然后再运行``ApiTest.java`**

```java
// login
HttpResponse login = Api.post("/account/login", "\"username\":\"Foo\",\"password\":\"Bar\"}");

// register
Account newAccount = new Account("213160002", "Daisy","123","Daisy", "Johnson");
HttpResponse register = Api.post("/account", JSON.toJSONString(newAccount));
// 这些方法都返回HttpResponse对象，可以得到服务器传来的状态码、message和JSON形式的数据（JSON数据可以通过封装的方法转换为类的实例），里面具体用法可以查看 HttpResponse.java
```

**注：为了简化后端流程，目前服务器不进行用户鉴权流程，只要收到前端请求就会返回，故服务器端没有真正意义上的“用户登陆和注销”**。

