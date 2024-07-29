package com.zuo.zoj.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zuo.zoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 查询请求
 *
 * @author <a href="https://github.com/lizuo">程序员鱼皮</a>
 * @from <a href="https://zuo.icu">编程导航知识星球</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {


    /**
     * 编程语言
     */
    private String language;

    /**
     * 提交类型
     */
    private Integer status;

    /**
     * 用户 id
     */
    private Long questionId;


    /**
     * 题目 id
     */
    private Long userId;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}