package com.zuo.zoj.judge.codesandbox;

import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSanboxProxy implements CodeSandbox {
    private CodeSandbox codeSandbox;
    public CodeSanboxProxy(CodeSandbox codeSandbox){
        this.codeSandbox=codeSandbox;
    }


    @Override
    public ExecuteCodeResponse excuteCode(ExecuteCodeRequest requst) {
        log.info("沙箱输入："+requst);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.excuteCode(requst);
        log.info("沙箱输出："+ executeCodeResponse);
        return executeCodeResponse;
    }
}
