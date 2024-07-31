package com.zuo.zoj.judge.codesandbox.impl;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.zuo.zoj.common.ErrorCode;
import com.zuo.zoj.exception.BusinessException;
import com.zuo.zoj.judge.codesandbox.CodeSandbox;
import com.zuo.zoj.judge.codesandbox.model.CompileMessage;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zuo.zoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zuo.zoj.judge.codesandbox.util.GetReturnMessage;
import com.zuo.zoj.model.dto.questionsubmit.JudgeInfo;
import com.zuo.zoj.model.enums.QuestionSubmitStateEnum;
import com.zuo.zoj.security.DefaultSecurity;
import lombok.extern.slf4j.Slf4j;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

@Slf4j
public class ExampleCodeSandBox implements CodeSandbox {
    private final static String fileName="Main.java";
    private final static Long TIMEOUT=1000L;
    private static String flag="";
    private static final List<String> blackList= Arrays.asList("Files","exec");
    @Override
    public ExecuteCodeResponse excuteCode(ExecuteCodeRequest executeCodeRequest) {
//        System.setSecurityManager(new DefaultSecurity());
        ExecuteCodeResponse executeCodeResponse=new ExecuteCodeResponse();
        CompileMessage compileMessage;
        //校验代码 TODO 字典树
        for(String s:blackList){
            if(executeCodeRequest.getCode().contains(s)){
                executeCodeResponse.setMessage("恶意代码");
                executeCodeResponse.setStatus(QuestionSubmitStateEnum.RunFail.getValue());
                return  executeCodeResponse;
            }
        }
        String path = getProperty("user.dir") + File.separator + "compileCode";
        if(!FileUtil.exist(path)){
            FileUtil.mkdir(path);
        }
        String userCodePath=path+File.separator+ UUID.randomUUID();
        FileUtil.writeString(executeCodeRequest.getCode(),userCodePath+File.separator+fileName, StandardCharsets.UTF_8);
        //编译代码
        String compileCmd="javac -encoding UTF-8 " + userCodePath + File.separator + fileName;
        try {
            Process process = Runtime.getRuntime().exec(compileCmd);
            compileMessage = GetReturnMessage.returnMessage(process,"编译");
            System.out.println(compileMessage);
            if(compileMessage.getErrMessage()!=null){
                executeCodeResponse.setMessage(compileMessage.getErrMessage());
                executeCodeResponse.setStatus(QuestionSubmitStateEnum.CompileFail.getValue());
                delFile(userCodePath);
                return executeCodeResponse;
            }
        } catch (Exception  e) {
           e.printStackTrace();
        }
        //执行代码
        List<String> answer=new ArrayList<>();
        Long maxTime=0l;
        Long maxMemory=0l;
        for(String input:executeCodeRequest.getInputList()){
            String executeCmd=String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main %s",userCodePath,input);
            try {
                long begin = Runtime.getRuntime().totalMemory();
                Process process = Runtime.getRuntime().exec(executeCmd);
                new Thread(()->{
                    try {
                        Thread.sleep(TIMEOUT);
                        if(process.isAlive()){
                            process.destroy();
                            flag="超时";
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                 compileMessage = GetReturnMessage.returnMessage(process, "运行");
                if (flag == "超时") {
                    executeCodeResponse.setMessage("运行超时");
                    executeCodeResponse.setStatus(QuestionSubmitStateEnum.RunFail.getValue());
                    break;
                }
                long end = Runtime.getRuntime().totalMemory();
                if(compileMessage.getSuccessMessage()==null){
                     executeCodeResponse.setMessage(compileMessage.getErrMessage());
                     executeCodeResponse.setStatus(QuestionSubmitStateEnum.RunFail.getValue());
                     break;
                 }else{
                     answer.add(compileMessage.getSuccessMessage());
                     if(compileMessage.getTime()>maxTime){
                         maxTime= compileMessage.getTime();
                     }
                     if(end-begin>maxMemory){
                         maxMemory=end-begin;
                     }
                 }
                System.out.println(compileMessage);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //设置返回值
        if(answer.size()==executeCodeRequest.getInputList().size()){
            executeCodeResponse.setOutputList(answer);
            executeCodeResponse.setStatus(QuestionSubmitStateEnum.SUCCEED.getValue());
        }
        JudgeInfo judgeInfo=new JudgeInfo();
        judgeInfo.setTime(maxTime);
        judgeInfo.setMemoryLimit(maxMemory);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        delFile(userCodePath);
        System.out.println(executeCodeResponse.getMessage());
        return executeCodeResponse;


    }
    public void delFile(String userCodePath){
        //删除文件
        if(FileUtil.exist(userCodePath)){
            FileUtil.del(userCodePath);
        }
    }
}
