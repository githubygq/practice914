package com.thoughtworks.basic;

import java.util.Map;
/*
处理命令的抽象接口类
 */
public interface Analyser {
    //记录错误信息
    StringBuilder worngMessage = new StringBuilder();
    //get方法
    StringBuilder getWorngMessage();
    //把命令分成map
    Map analyse(String s);
    //判断是否符合schema
    boolean judge(Map<String,Object> piceMap);
    //处理少命令、错误命令
    Map handle(Map<String,Object> targetMap);
}