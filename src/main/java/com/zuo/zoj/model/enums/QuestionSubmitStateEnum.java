package com.zuo.zoj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目提交类型枚举
 *
 * @author <a href="https://github.com/lizuo">zzh</a>
 * 
 */
public enum QuestionSubmitStateEnum {

    WAITING("待判题", 0),
    RUNING("运行中", 1),
    SUCCEED("成功",2),
    RunFail("运行失败",3),
    CompileFail("编译失败",4);


    private final String text;

    private final Integer value;

    QuestionSubmitStateEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionSubmitStateEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitStateEnum anEnum : QuestionSubmitStateEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
