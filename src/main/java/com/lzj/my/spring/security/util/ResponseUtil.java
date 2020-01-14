package com.lzj.my.spring.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzj.my.spring.security.result.ApiResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Zijian Liao
 * @date 2020/1/5
 * @description:
 */
public class ResponseUtil {

    private static String CONTENT_TYPE="application/json;charset=utf-8";

    public static void  sendMessage(HttpServletResponse response, ApiResponse apiResponse){
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        try(PrintWriter out = response.getWriter()){
            out.write(new ObjectMapper().writeValueAsString(apiResponse));
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
