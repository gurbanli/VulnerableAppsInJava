package com.example.directorytraversal.controller;

import com.example.directorytraversal.service.CommandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CommandInjectionController {

    private CommandService commandService;

    public CommandInjectionController(CommandService commandService){
        this.commandService = commandService;
    }

    @GetMapping("/ping")
    public String pingPage(Model model){
        return "command";
    }

    @PostMapping("/ping1")
    public String ping1(RedirectAttributes redirectAttributes, @RequestParam String host){
        redirectAttributes.addFlashAttribute("result", commandService.executeCommand(host));
        return "redirect:/ping";
    }

    @PostMapping("/ping2")
    public String ping2(RedirectAttributes redirectAttributes, @RequestParam String host){
        if(host.contains(";")) host = host.replace(";","");
        redirectAttributes.addFlashAttribute("result", commandService.executeCommand(host));
        return "redirect:/ping";
    }

    @PostMapping("/ping3")
    public String ping3(RedirectAttributes redirectAttributes, @RequestParam String host){
        String[] blackList = {";", "`"};
        for(String ch : blackList){
            host = host.replace(ch,"");
        }
        redirectAttributes.addFlashAttribute("result", commandService.executeCommand(host));
        return "redirect:/ping";
    }

    @PostMapping("/ping4")
    public String ping4(RedirectAttributes redirectAttributes, @RequestParam String host){
        String[] blackList = {";", "`","$","(",")"};
        for(String ch : blackList){
            host = host.replace(ch,"");
        }
        redirectAttributes.addFlashAttribute("result", commandService.executeCommand(host));
        return "redirect:/ping";
    }
}
