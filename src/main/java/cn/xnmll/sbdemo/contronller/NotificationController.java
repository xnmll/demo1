package cn.xnmll.sbdemo.contronller;


import cn.xnmll.sbdemo.dto.NotificationDTO;
import cn.xnmll.sbdemo.enums.NotificationTypeEnum;
import cn.xnmll.sbdemo.model.User;
import cn.xnmll.sbdemo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (notificationDTO.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()
                || notificationDTO.getType() == NotificationTypeEnum.REPLY_QUESTION.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }

}
