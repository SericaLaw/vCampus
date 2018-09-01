# vCampus

一个没有用任何框架的原始app，前端采用[Java Swing](https://www.yiibai.com/swing/home.html)编程，后端目前纯Java手撸，使用Java Socket进行前后端通信，使用Access数据库。

按照项目要求，目录结构如下组织：

| 路径                     | 说明                                     |
| ------------------------ | ---------------------------------------- |
| `vCampusClient`            | 前端                                     |
| `vCampusServer `           | 后端                                     |
| `vCampusDatabase`          | 数据库                                   |
|  `vCampusCommon`         | 项目依赖 |
| `docs/api`               | 不存在的API文档                          |
| `docs`                   | 开发日志、设计说明书以及其他要提交的文件 |
| `java-app-template-demo` | 一个包含前后端的完整java程序示例         |

## 构想

前端采用MVC模式开发，如果你不清楚MVC模式的话，可以[参考教程](http://www.runoob.com/design-pattern/mvc-pattern.html)。

前端中的视图层主要专注界面，要开发出一个大体的界面框架，方便代码复用。

通过调用后端接口，对数据库进行增删改查，并把结果返回给前端，前端需要相应地更新视图，或给出其他交互信息。

## 开发环境

编辑器：demo里用的是idea…要求用eclipse的话还是尽量用eclipse吧…

[maven](https://www.yiibai.com/maven/)相关：还木有开始考虑orz...

### 关于git

如果你还不够熟悉git，可以查看[这个教程](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)。

如果你使用git bash，下面有一些常用命令：

```bash
git clone git@github.com:SericaLaw/vCampus.git
git add . 
git status
git commit -m "comment"
git push origin master (or other branch)
git pull
```

用Git进行多人协作的原则：每个人应明确自己的责任区，尽量避免提交时出现冲突；对自己的commit负责，先进行测试后再提交

## 资源列表

### Java Swing 教程

1. [极客学院](http://wiki.jikexueyuan.com/project/swing/)
2. [易百教程](https://www.yiibai.com/swing/home.html)

### Java教程

1. [MVC设计模式](http://www.runoob.com/design-pattern/mvc-pattern.html)

### Git教程

1. [廖雪峰](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

### 规范

1. [数据库命名规范](https://blog.csdn.net/yu757371316/article/details/54602545)

