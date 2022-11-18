package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDice {

    @GetMapping("/roll-dice")
    public String diceRoll(){
        return "roll-dice";
    }

    @GetMapping("roll-dice/{id}")
    public String diceRoll(@PathVariable int id, Model model){
        int num = (int)(Math.random() *7);
        model.addAttribute("number", num);
        if(num != id){
            model.addAttribute("answer", "wrong");
        }else {
            model.addAttribute("answer", "winner,winner, chicken dinner");
        }
        return "roll-dice";
    }
}
