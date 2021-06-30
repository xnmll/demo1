package cn.xnmll.sbdemo.contronller;

import cn.xnmll.sbdemo.dto.PaginationDTO;
import cn.xnmll.sbdemo.dto.QuestionDTO;
import cn.xnmll.sbdemo.mapper.QuestionMapper;
import cn.xnmll.sbdemo.mapper.UserMapper;
import cn.xnmll.sbdemo.model.Question;
import cn.xnmll.sbdemo.model.User;
import cn.xnmll.sbdemo.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@Controller 注释可以识别，http请求由控制器@Controller处理
//In Spring’s approach to building web sites, HTTP requests are handled by a controller.
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    //该@GetMapping注释确保 HTTP GET 请求/hello被映射到该hello()方法。
    @GetMapping("/")
//@RequestParam将查询字符串参数的值绑定name到方法的name参数中greeting()。此查询字符串参数不是required。如果请求中不存在defaultValue，World则使用of 。name参数的值被添加到一个Model对象中，最终使其可被视图模板访问。
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
