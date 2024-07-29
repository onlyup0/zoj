package com.zuo.zoj.judge.codesandbox;

import com.zuo.zoj.judge.codesandbox.CodeSandbox;
import com.zuo.zoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.zuo.zoj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.zuo.zoj.judge.codesandbox.impl.ThirdpartCodeSandBox;

public class CodeSandBoxFactory {

    public static CodeSandbox getCodeSandbox(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandBox();
            case "remote" :
                return  new RemoteCodeSandBox();
            case "thirdpart":
                return new ThirdpartCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
