package com.wangjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description:
 * @Author : 王俊
 * @date :  2020/11/5
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String test(){
        return "index";
    }

    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public void pdfStreamHandler(HttpServletRequest request, HttpServletResponse response) {
        //PDF文件地址
        File file = new File("C:\\Users\\Acer\\Desktop\\王俊.pdf");
        if (file.exists()) {
            byte[] data = null;
            FileInputStream input=null;
            try {
                input= new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
            } catch (Exception e) {
                System.out.println("pdf文件处理异常：" + e);
            }finally{
                try {
                    if(input!=null){
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
