package cn.xnmll.sbdemo.dto;

import lombok.Data;

/**
 * @author langchunyang
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
