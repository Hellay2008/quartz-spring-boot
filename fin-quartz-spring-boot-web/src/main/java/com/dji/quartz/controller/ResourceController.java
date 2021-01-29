package com.dji.quartz.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
public class ResourceController {
    @GetMapping("/quartz/{path}")
    public void htmlResource(@PathVariable(name = "path") String path, HttpServletResponse response) throws IOException {
        responseResourceByPath("templates/html/" + path, "text/html",response);
    }

    @GetMapping("/quartz/static/js/{path}")
    public void jsResource(@PathVariable(name = "path") String path, HttpServletResponse response) throws IOException {
        responseResourceByPath("static/js/" + path, "text/javascript", response);
    }

    @GetMapping("/quartz/static/css/{path}")
    public void cssResource(@PathVariable(name = "path") String path, HttpServletResponse response) throws IOException {
        responseResourceByPath("static/css/" + path, "text/css", response);
    }

    @GetMapping("/quartz/static/css/fonts/{path}")
    public void woffResource(@PathVariable(name = "path") String path, HttpServletResponse response) throws IOException {
        responseResourceByPath("static/css/fonts/" + path, "font/woff", response);
    }

    private void responseResourceByPath(@PathVariable(name = "path") String path, String contentType, HttpServletResponse response) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        OutputStream outStream = response.getOutputStream();
        response.setContentType(contentType + ";charset=utf-8");
        response.setCharacterEncoding("utf-8");
        if (inputStream != null) {
            IOUtils.copy(inputStream, outStream);
            response.flushBuffer();
            outStream.close();
        }
    }
}
