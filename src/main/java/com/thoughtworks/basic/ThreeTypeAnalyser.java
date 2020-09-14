package com.thoughtworks.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
处理命令的具体实现类
 */
public class ThreeTypeAnalyser implements Analyser {
    List<String> keys = Arrays.asList("l","p","d");

    Map<String,Integer> countMap = new HashMap<>();

    public StringBuilder getWorngMessage() {
        return worngMessage;
    }

    @Override
    public Map analyse(String s) {
        List<String> results = Arrays.asList(s.split("-"));
        String[] pice;
        Map<String,Object> piceMap = new HashMap();
        for (String result : results) {
            result=result.trim();
            if (result!=null&&!"".equals(result)){
                pice = result.split(" ");
                piceMap.put(pice[0], pice[1]);
            }
        }
        return piceMap;
    }

    @Override
    public boolean judge(Map<String,Object> piceMap) {
        if (null == piceMap){
            worngMessage.append("输入为空！");
            return false;
        }
        initMap(countMap);
        for (String key:piceMap.keySet()){
            if(keys.contains(key)){
                switch (key){
                    case "l":
                        if(!Boolean.valueOf((String)piceMap.get(key))){
                            worngMessage.append("-l 命令为非布尔类型！");
                            return false;
                        }
                        countMap.put("l",1);
                        break;
                    case "p":
                        if(!((String)piceMap.get(key)).matches("[0-9]+")){
                            worngMessage.append("-p 命令为非数字类型！");
                            return false;
                        }
                        countMap.put("p",1);
                        break;
                    case "d":
                        if(!((piceMap.get(key))instanceof String)){
                            worngMessage.append("-d 命令为非字符串类型！");
                            return false;
                        }
                        countMap.put("d",1);
                        break;
                }
            }else {
                worngMessage.append("存在非规定内的参数类型！");
                return false;
            }
        }
        return true;
    }

    @Override
    public Map handle(Map<String, Object> piceMap) {
        for (String key: countMap.keySet()){
            if(0 == countMap.get(key)){
                switch (key){
                    case "l":piceMap.put("l",false);break;
                    case "p":piceMap.put("p",0);break;
                    case "d":piceMap.put("d","");break;
                }
            }
        }
        return piceMap;
    }

    public void initMap(Map map){
        for (String s:keys) {
            map.put(s,0);
        }
    }
}
