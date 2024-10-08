package com.zuo.zoj.judge.codesandbox.model;

import com.zuo.zoj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteCodeResponse {
    private List<String> outputList;
    /**
     * 接口信息
     */
    private String message;
    private JudgeInfo judgeInfo;
    /**
     * 执行状态
     */
    private Integer status;
}
