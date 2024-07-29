package com.zuo.zoj.judge.codesandbox.impl;

import com.zuo.zoj.judge.codesandbox.CodeSandbox;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zuo.zoj.model.dto.questionsubmit.JudgeInfo;
import com.zuo.zoj.model.enums.JudgeInfoMessageEnum;
import com.zuo.zoj.model.enums.QuestionSubmitStateEnum;

import java.util.List;

public class ExampleCodeSandBox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse excuteCode(ExecuteCodeRequest executeCodeRequest) {
//        List<String> inputList = executeCodeRequest.getInputList();
//        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
//        executeCodeResponse.setOutputList(inputList);
//        executeCodeResponse.setMessage("测试执行成功");
//        executeCodeResponse.setStatus(QuestionSubmitStateEnum.SUCCEED.getValue());
//        JudgeInfo judgeInfo = new JudgeInfo();
//        judgeInfo.setMessage(JudgeInfoMessageEnum.Accepted.getText());
//        judgeInfo.setMemoryLimit(100L);
//        judgeInfo.setTime(100L);
//        executeCodeResponse.setJudgeInfo(judgeInfo);
//        return executeCodeResponse;
        return null;
    }
}
