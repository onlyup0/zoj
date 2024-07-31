package com.zuo.zoj.judge.codesandbox.model;

import lombok.Data;

@Data
public class CompileMessage {
    private Integer waitFor;
    private String successMessage;
    private String errMessage;
    //运行时间
    private Long time;
}
