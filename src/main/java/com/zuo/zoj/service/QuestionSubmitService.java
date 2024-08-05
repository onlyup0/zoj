package com.zuo.zoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zuo.zoj.model.dto.question.QuestionQueryRequest;
import com.zuo.zoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zuo.zoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zuo.zoj.model.entity.Question;
import com.zuo.zoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.zoj.model.entity.User;
import com.zuo.zoj.model.vo.QuestionSubmitVO;
import com.zuo.zoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 左振宏
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-07-21 15:33:22
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交代码
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);



    /**
     * 获取问题封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取问题封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmit, User loginUser);



}
