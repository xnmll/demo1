package cn.xnmll.sbdemo.service;

import cn.xnmll.sbdemo.dto.PaginationDTO;
import cn.xnmll.sbdemo.dto.QuestionDTO;
import cn.xnmll.sbdemo.mapper.QuestionMapper;
import cn.xnmll.sbdemo.mapper.UserMapper;
import cn.xnmll.sbdemo.model.Question;
import cn.xnmll.sbdemo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) totalPage = totalCount / size;
        else totalPage = totalCount / size + 1;
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        if (totalCount % size == 0) totalPage = totalCount / size;
        else totalPage = totalCount / size + 1;
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

}
