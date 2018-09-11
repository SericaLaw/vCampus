# vCampus

~~一个没有用任何框架的原始app（屈服，真香）~~使用Hibernate ORM的Java App，前端采用[Java FX](https://docs.oracle.com/javase/8/javase-clienttechnologies.htm)编程，后端~~目前纯Java手撸~~是不会使用Spring的，不会使用Spring的。使用Java Socket进行前后端通信，使用Access数据库。

按照项目要求，目录结构如下组织：

| 路径                     | 说明                                     |
| ------------------------ | ---------------------------------------- |
| `vCampus/vCampusClient`    | 前端                                     |
| `vCampus/vCampusServer `   | 后端                                     |
| `vCampus/vCampusCore` | 前后端公用模块                           |
|`vCampus/vCampusCore/data`|DBHelper工具类|
|`vCampus/vCampusCore/models`|entity和viewmodel|
|`vCampus/vCampusCore/util`|Api类和Logger工具类|
|`vCampus/vCampusCore/web`|基于Socket封装的前后端通信工具类|
| `vCampus/third-party-lib` | 项目依赖 |
| `docs`               | 不存在的API文档                          |
| `java-app-template-demo` | 一个采用MVC模式的Java示例程序 |
| `vCampus/vCampusServer/database` | 数据库 |

## 构想

前端采用MVC模式开发，如果你不清楚MVC模式的话，可以[参考教程](http://www.runoob.com/design-pattern/mvc-pattern.html)。

前端中的视图层主要专注界面，要开发出一个大体的界面框架，方便代码复用。

通过调用后端接口，对数据库进行增删改查，并把结果返回给前端，前端需要相应地更新视图，或给出其他交互信息。

## 开发环境

编辑器：使用IntelliJ IDEA

[maven](https://www.yiibai.com/maven/)相关：使用maven简单配置

## 关于git

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

## apidoc

在仓库根目录打开终端，输入命令：

```bash
apidoc -i ./vCampus/vCampusServer/src/main/java/team/yummy/vCampus/server/api -o ./docs -t ./docs/src/template/
```

即可创建api文档。

## 资源列表

### Java FX 文档

1. [官方文档主页](https://docs.oracle.com/javase/8/javase-clienttechnologies.htm)
2. [Java FX API文档](https://docs.oracle.com/javase/8/javafx/api/toc.htm)
3. [Java FX CSS Reference](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html)
4. [Java FX FXML Reference](https://docs.oracle.com/javase/8/javafx/api/javafx/fxml/doc-files/introduction_to_fxml.html)
5. [Java FX中的数据绑定](https://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm#JFXBD107)
6. [JavaFX Collections](https://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm#JFXCL107)
7. 一个不错的JavaFX Material Design库，我们的项目也将用到它：[JFoenix](https://github.com/jfoenixadmin/JFoenix)
8. [JFoenix文档](http://www.jfoenix.com/documentation.html)

### 规范

1. [数据库命名规范](https://blog.csdn.net/yu757371316/article/details/54602545)
2. [CSS编码规范](https://codeguide.bootcss.com/#css-syntax)
3. [CSS BEM命名规范](https://www.jianshu.com/p/287a89b364f0)

### UI设计参考

1. [主程序](https://uiiiuiii.com/inspiration/161652661.html)
2. [登录界面](https://uiiiuiii.com/inspiration/161693155.html)
3. [什么都能找到（配色、图标、素材、UI灵感…）的设计导航](http://hao.shejidaren.com/)

### Java教程

1. [MVC设计模式](http://www.runoob.com/design-pattern/mvc-pattern.html)

### Git教程

1. [廖雪峰](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

