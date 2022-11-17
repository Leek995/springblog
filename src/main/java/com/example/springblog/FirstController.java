package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FirstController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "Hello from springy";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/bubbles")
    @ResponseBody
    public String bubbles(){
        return "bubbles";
    }

    @GetMapping("/greeting/{name}/{last_name}")
    @ResponseBody
    public String greeting(@PathVariable String name, @PathVariable String last_name){
        return "Greetings " + name + " " + last_name;
    }
}
