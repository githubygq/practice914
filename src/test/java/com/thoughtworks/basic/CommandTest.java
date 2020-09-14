package com.thoughtworks.basic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandTest {
    @Test
    public void command_test_right() {
        //given
        String command = "-l true -p 8080 -d /usr/logs";
        Analyser analyser = new ThreeTypeAnalyser();
        Commander commander = new Commander(command, analyser);
        //when
        String resault = commander.showCommand();
        //then
        assertEquals(resault,"p:8080 d:/usr/logs l:true ");
    }

    @Test
    public void command_test_no_l_command() {
        //given
        String command = " -p 8080 -d /usr/logs";
        Analyser analyser = new ThreeTypeAnalyser();
        Commander commander = new Commander(command, analyser);
        //when
        String resault = commander.showCommand();
        //then
        assertEquals(resault,"p:8080 d:/usr/logs l:false ");
    }

    @Test
    public void command_test_no_p_command() {
        //given
        String command = "-l true  -d /usr/logs";
        Analyser analyser = new ThreeTypeAnalyser();
        Commander commander = new Commander(command, analyser);
        //when
        String resault = commander.showCommand();
        //then
        assertEquals(resault,"p:0 d:/usr/logs l:true ");
    }

    @Test
    public void command_test_no_d_command() {
        //given
        String command = "-l true -p 8080";
        Analyser analyser = new ThreeTypeAnalyser();
        Commander commander = new Commander(command, analyser);
        //when
        String resault = commander.showCommand();
        //then
        assertEquals(resault,"p:8080 d: l:true ");
    }

    @Test
    public void command_test_wrong_command() {
        //given
        String command = "-l true -w 8080 -d /usr/logs";
        Analyser analyser = new ThreeTypeAnalyser();
        Commander commander = new Commander(command, analyser);
        //when
        String resault = commander.showCommand();
        //then
        assertEquals(resault,"存在非规定内的参数类型！");
    }

    @Test
    public void command_test_remind_l_command() {
        //given
        String command = "-l 121 -p 8080 -d /usr/logs";
        Analyser analyser = new ThreeTypeAnalyser();
        Commander commander = new Commander(command, analyser);
        //when
        String resault = commander.showCommand();
        //then
        assertEquals(resault,"-l 命令为非布尔类型！");
    }

    @Test
    public void command_test_remind_p_command() {
        //given
        String command = "-l true -p sss -d /usr/logs";
        Analyser analyser = new ThreeTypeAnalyser();
        Commander commander = new Commander(command, analyser);
        //when
        String resault = commander.showCommand();
        //then
        assertEquals(resault,"-p 命令为非数字类型！");
    }

}
