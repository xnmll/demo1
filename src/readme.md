### 做什么：在线交流（论坛）系统

### 用什么：

1. SpringBoot

2. IDEA / Shotcut

3. Refactor

4. Git

5. Bootstrap

6. Github Authorization

7. Properties

8. Session / cookies

9. Mysql / h2

10. Mybatis

11. Flyway

12. Lombok

13. Dev Tools

14. Pagination

15. Interceptor

16. MGB

17. fastjson 类快速转json

18. okhttp 模拟http请求 带参get post

    

### 踩的坑：

1. mac m1启动项目很慢： 修改hosts文件
2. 配置文件有变化





### 快捷键使用：

command + p 提示要传入什么参数

cmd shift f12 最大化

cmd shift f6 重命名

⌘ + ⌥ + V 提取变量

cmd e 切换最近的编辑窗口

option + command + L  格式化

cmd + e 快速切换





### **Thymeleaf**的使用：

pom导入依赖

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

```
//@Controller 注释可以识别，http请求由控制器@Controller处理，把当前的类变成路由api的承载者
//In Spring’s approach to building web sites, HTTP requests are handled by a controller.
@Controller
public class helloController {

    //该@GetMapping注释确保 HTTP GET 请求/hello被映射到该hello()方法。
    @GetMapping("/hello")//@RequestParam将查询字符串参数的值绑定name到方法的name参数中greeting()。此查询字符串参数不是required。如果请求中不存在defaultValue，World则使用of 。name参数的值被添加到一个Model对象中，最终使其可被视图模板访问。
    public String hello(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello"; //自动在templates里找hello模版
    }
}
@Component//仅仅把当前类初始化到spring容器的上下文
ioc的体现，可以自动实例化对象到池子里

@autowired//把spring容器里写好的一个实例加载到当前使用的上下文

@Value("${a}") //去配置文件里读key为a的value ， 把它赋值到id
private String id;
```



### h2数据库的使用







### github授权登陆

官方文档：https://docs.github.com/en/developers/apps

流程：

The web application flow to authorize users for your app is:

1. Users are redirected to request their GitHub identity（点击第三方登录后，重定向到github以请求他们的github身份）
2. Users are redirected back to your site by GitHub（用户被重定向到项目站点）
3. Your app accesses the API with the user's access token（站点使用用户的访问令牌访问api）



1. A 网站让用户跳转到 GitHub。
2. GitHub 要求用户登录，然后询问"A 网站要求获得 xx 权限，你是否同意？"
3. 用户同意，GitHub 就会重定向回 A 网站，同时发回一个授权码。
4. A 网站使用授权码，向 GitHub 请求令牌。
5. GitHub 返回令牌.
6. A 网站使用令牌，向 GitHub 请求用户数据。

![截屏2021-06-28 下午8.39.11](/Users/langchunyang/Library/Application Support/typora-user-images/截屏2021-06-28 下午8.39.11.png)

1. 登陆：

```
<li><a href="https://github.com/login/oauth/authorize?client_id=c12ba731ee2598f7e0c6&redirect_uri=http://localhost:8887/callback&scope=user&state=1">登陆</a></li>
```

2. 确认授权后 github 返回 http://localhost:8887/callback?code=060d8498e8add7c13cad&state=1AuthorizeController 
3. 处理/callback请求 ， 由RequestParam获取参数code和state ，由@Value获取配置文件中的其他参数，把信息封装到accessDTO中，调用githubProvider.
4. 使用getAccessToken，getUser模拟http请求，用okhttp把信息传进去。获得user信息

5. 把创建必要的字段把信息保存到数据库中，实现持久登陆（使用h2数据库）







### session和cookie的区别

### service层：不同模块间的组合

### 查询首页列表的流程和实现



### 分页业务逻辑

1. indexcontroller里面加入requestparam的参数 page size 并且设置默认值























