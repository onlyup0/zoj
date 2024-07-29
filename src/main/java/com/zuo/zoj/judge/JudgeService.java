package com.zuo.zoj.judge;

import com.zuo.zoj.model.entity.QuestionSubmit;
import com.zuo.zoj.model.vo.QuestionSubmitVO;

public interface JudgeService {
    QuestionSubmit doJudge(long questionSubmitId);
}
