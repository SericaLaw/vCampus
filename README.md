# vCampus

一个没有用任何框架的原始app，前端采用[Java Swing](https://www.yiibai.com/swing/home.html)编程，后端目前纯Java手撸，使用Java Socket进行前后端通信，使用Access数据库。

按照项目要求，目录结构如下组织：

| 路径                     | 说明                                     |
| ------------------------ | ---------------------------------------- |
| vCampusClient            | 前端                                     |
| vCampusServer            | 后端                                     |
| vCampus                  | 数据库                                   |
| `docs/api`               | 不存在的API文档                          |
| `docs`                   | 开发日志、设计说明书以及其他要提交的文件 |
| `java-app-template-demo` | 一个包含前后端的完整java程序示例         |

## 构想

前端采用MVC模式开发，如果你不清楚MVC模式的话，可以参考[教程](http://www.runoob.com/design-pattern/mvc-pattern.html)。

前端视图层主要专注界面，要开发出一个大体的界面框架，方便代码复用。

通过调用后端接口，对数据库进行增删改查，并把结果返回给前端，前端需要相应地更新视图，或给出其他交互信息。

编辑器：demo里用的是idea…