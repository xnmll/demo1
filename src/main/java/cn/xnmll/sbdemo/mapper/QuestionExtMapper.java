package cn.xnmll.sbdemo.mapper;

import cn.xnmll.sbdemo.model.Question;
import cn.xnmll.sbdemo.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
}