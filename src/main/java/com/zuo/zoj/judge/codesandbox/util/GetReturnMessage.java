package com.zuo.zoj.judge.codesandbox.util;

import com.zuo.zoj.judge.codesandbox.model.CompileMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 返回沙箱执行信息
 */
@Slf4j
public class GetReturnMessage {
    public static CompileMessage returnMessage(Process process,String name) throws InterruptedException, IOException {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        CompileMessage compileMessage=new CompileMessage();
        int waitFor = process.waitFor();
        compileMessage.setWaitFor(waitFor);
        if(waitFor==0){
            log.info(name+"成功");
            InputStream inputStream=process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
            String s;
            StringBuilder stringBuilder=new StringBuilder();
            while ((s=bufferedReader.readLine())!=null){
                stringBuilder.append(s);
            }
            compileMessage.setSuccessMessage(stringBuilder.toString());
            System.out.println(stringBuilder);
            inputStream.close();
            process.destroy();

        }else{
            log.info(name+"失败");
//            //获取成功值
//            InputStream inputStream=process.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
//            String s;
//            StringBuilder stringBuilder=new StringBuilder();
//            while ((s=bufferedReader.readLine())!=null){
//                stringBuilder.append(s);
//            }
//            compileMessage.setSuccessMessage(stringBuilder.toString());
            //运行出错时获取
            InputStream errinputStream=process.getErrorStream();
            BufferedReader errbufferedReader = new BufferedReader(new InputStreamReader(errinputStream, StandardCharsets.UTF_8));
            String serr;
            StringBuilder errstringBuilder=new StringBuilder();
            while ((serr=errbufferedReader.readLine())!=null){
                errstringBuilder.append(serr);
            }
            compileMessage.setErrMessage(String.valueOf(errstringBuilder));
            System.out.println(errbufferedReader);
        }
        stopWatch.stop();
        compileMessage.setTime(stopWatch.getLastTaskTimeMillis());
        return compileMessage;
    }
}
