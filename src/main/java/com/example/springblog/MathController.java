package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{three}/and/{four}")
    @ResponseBody
    public String addition(@PathVariable int three, @PathVariable int four){
        int addition = three + four;
        return "sum of " + addition;
    }
}
