package com.thoughtworks.basic;

import java.util.Map;
/*
命令处理的工具类
 */
public class Commander {
    String command;
    Analyser analyser;
    public Commander(String command, Analyser analyser){
        this.command = command;
        this.analyser = analyser;
    }

    public String showCommand(){
        String result="";
        Map<String,Object> target = analyser.analyse(command);
        if(analyser.judge(target)){
            target = analyser.handle(target);
            for (String key:target.keySet()) {
                result+=key+":"+target.get(key)+" ";
            }
        }else {
            result = analyser.getWorngMessage().toString();
            analyser.getWorngMessage().delete(0,analyser.getWorngMessage().length());
        }
        return result;
    }
}
