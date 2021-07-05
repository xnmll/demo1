package cn.xnmll.sbdemo.mapper;

import cn.xnmll.sbdemo.model.Comment;
import cn.xnmll.sbdemo.model.CommentExample;
import cn.xnmll.sbdemo.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {

    int incCommentCount(Comment comment);
}