package com.lzj.my.spring.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zijian Liao
 * @date 2020/1/3 16:35
 * @description
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/findAll")
    public String findAll(){ return "product-list"; } }
