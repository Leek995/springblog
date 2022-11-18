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
        int dice1 = (int)(Math.random() * 7);
        int dice2 = (int)(Math.random() * 7);
        int num = dice1 + dice2;
        model.addAttribute("number", num);
        model.addAttribute("dice1", dice1);
        model.addAttribute("dice2", dice2);
        if(num != id){
            model.addAttribute("answer", "wrong");
        }else {
            model.addAttribute("answer", "winner,winner, chicken dinner");
        }
        return "roll-dice";
    }
}
