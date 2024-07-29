package com.zuo.zoj.judge;


import com.zuo.zoj.judge.strategy.DefaultJudgeStrategy;
import com.zuo.zoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.zuo.zoj.judge.strategy.JudgeContext;
import com.zuo.zoj.judge.strategy.JudgeStrategy;
import com.zuo.zoj.model.dto.questionsubmit.JudgeInfo;
import com.zuo.zoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
