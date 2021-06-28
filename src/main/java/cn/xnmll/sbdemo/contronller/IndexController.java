package cn.xnmll.sbdemo.contronller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller 注释可以识别，http请求由控制器@Controller处理
//In Spring’s approach to building web sites, HTTP requests are handled by a controller.
@Controller
public class IndexController {
    //该@GetMapping注释确保 HTTP GET 请求/hello被映射到该hello()方法。
    @GetMapping("/")//@RequestParam将查询字符串参数的值绑定name到方法的name参数中greeting()。此查询字符串参数不是required。如果请求中不存在defaultValue，World则使用of 。name参数的值被添加到一个Model对象中，最终使其可被视图模板访问。
    public String index(){return "index";}
}
