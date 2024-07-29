package com.zuo.zoj.model.dto.questionsubmit;

import lombok.Data;

import java.util.List;

@Data
public class JudgeInfo {
    private String message;
    private long memoryLimit;
    private long time;
}
