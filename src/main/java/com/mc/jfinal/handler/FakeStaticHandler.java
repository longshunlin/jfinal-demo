package com.mc.jfinal.handler;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现伪静态功能
 * Created by leslie on 2018/4/23.
 */
public class FakeStaticHandler extends Handler {
    private String viewPostfix;

    public FakeStaticHandler() {
        this.viewPostfix = ".html";
    }

    public FakeStaticHandler(String viewPostfix) {
        if(StrKit.isBlank(viewPostfix)) {
            throw new IllegalArgumentException("viewPostfix can not be blank.");
        } else {
            this.viewPostfix = viewPostfix;
        }
    }

    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        if("/".equals(target)) {
            this.next.handle(target, request, response, isHandled);
        } else {
            int index = target.lastIndexOf(this.viewPostfix);
            if(index != -1) {
                target = target.substring(0, index);
            }

            this.next.handle(target, request, response, isHandled);
        }
    }
}