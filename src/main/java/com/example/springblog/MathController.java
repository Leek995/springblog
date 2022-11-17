package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{three}/and/{four}")
    @ResponseBody
    public String addition(@PathVariable int num1, @PathVariable int num2){
        int addition = num1 + num2;
        return "sum of " + addition;
    }

    @GetMapping("/subtract/3/from/10")
    @ResponseBody
    public String subtraction(@PathVariable int num1, int num2){
        int subtraction = num1 - num2;
        return  "sum of " + subtraction;
    }
}
