package com.bryant.practice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 專案健康檢查
 */
@RestController
public class HealthController {

	 /**
     * Normal running
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "Hello world! web app is running.";
    }
}
