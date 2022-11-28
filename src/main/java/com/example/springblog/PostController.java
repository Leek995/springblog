package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/post")
    @ResponseBody
    public String post(){
        return "posts index page";
    }
    @GetMapping("/post/{id}")
    @ResponseBody
    public String postId(@PathVariable int id){
        return "view an individual post " + id;
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String postCreate(){
        return "view the form for creating a post";
    }



//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String postCreateSubmit(){
//        return "create new post";
//    }




}
