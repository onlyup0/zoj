package com.zuo.zoj.judge.codesandbox;

import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {
    ExecuteCodeResponse excuteCode(ExecuteCodeRequest requst);
}
