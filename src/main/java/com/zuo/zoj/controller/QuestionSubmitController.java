//package com.zuo.zoj.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.zuo.zoj.common.BaseResponse;
//import com.zuo.zoj.common.ErrorCode;
//import com.zuo.zoj.common.ResultUtils;
//import com.zuo.zoj.exception.BusinessException;
//import com.zuo.zoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
//import com.zuo.zoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
//import com.zuo.zoj.model.entity.Question;
//import com.zuo.zoj.model.entity.QuestionSubmit;
//import com.zuo.zoj.model.entity.User;
//import com.zuo.zoj.model.vo.QuestionSubmitVO;
//import com.zuo.zoj.service.QuestionSubmitService;
//import com.zuo.zoj.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 題目提交接口
// *
// * @author <a href="https://github.com/lizuo">程序员鱼皮</a>
// * @from <a href="https://zuo.icu">编程导航知识星球</a>
// */
//@RestController
//@RequestMapping("/question_submit")
//@Slf4j
//public class QuestionSubmitController {
//
//    @Resource
//    private QuestionSubmitService questionSubmitService;
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 题目提交
//     *
//     * @param questionSubmitAddRequest
//     * @param request
//     * @return resultNum 本次点赞变化数
//     */
//    @PostMapping("/")
//    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//            HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 登录才能提交
//        final User loginUser = userService.getLoginUser(request);
//        long questionId = questionSubmitAddRequest.getQuestionId();
//        Long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(result);
//    }
//    /**
//     * 分页获取提交题目列表（仅管理员和本人能看到）
//     *
//     * @param questionSubmitQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
//                                                                         HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        User loginUser = userService.getLoginUser(request);
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser));
//    }
//
//}
