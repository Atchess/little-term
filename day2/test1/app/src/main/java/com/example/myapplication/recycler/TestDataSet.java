package com.example.myapplication.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png","字节跳动", "524.6w", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","Google", "欢迎体验Android studio", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","李华", "转发[视频]", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","刘明", "test1：转发[直播]", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","张飞", "我是张飞，长坂坡见", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","孙权", "生子当如孙仲谋", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","曹操", "啊，你疯了", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","苏东坡", "竹杖芒鞋轻胜马...", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","王介甫", "尽吾志也...", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","司马光", "小事不糊涂之谓能...", "19:30"));
        result.add(new TestData("@android:mipmap/sym_def_app_icon","刘秀", "22.2w", "19:30"));
        return result;
    }

}