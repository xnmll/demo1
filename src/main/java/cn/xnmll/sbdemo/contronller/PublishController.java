package cn.xnmll.sbdemo.contronller;

import cn.xnmll.sbdemo.cache.TagCache;
import cn.xnmll.sbdemo.dto.QuestionDTO;
import cn.xnmll.sbdemo.mapper.QuestionMapper;
import cn.xnmll.sbdemo.model.Question;
import cn.xnmll.sbdemo.model.User;
import cn.xnmll.sbdemo.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute(("id"),question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @RequestMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());
        if (title == null || title == "") {
            model.addAttribute("error", "??????????????????");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "??????????????????");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "??????????????????");
            return "publish";
        }

        String filterInvalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNoneBlank(filterInvalid)){
            model.addAttribute("error","??????????????????"+filterInvalid);
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "???????????????");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());

        question.setId(id);

        questionService.createOrUpdate(question);

        return "redirect:/";
    }
}
