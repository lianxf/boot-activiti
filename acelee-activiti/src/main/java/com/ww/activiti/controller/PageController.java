package com.ww.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className PageController
 * @description 打开activiti编辑器
 * @author beyond09.hik
 * @date 9:45 2019/12/25
 * @version 1.0
 */
@Controller
public class PageController {
    @GetMapping("editor")
    public String modelOpen(){
        return "/modeler";
    }
}
