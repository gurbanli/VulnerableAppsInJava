package com.example.fileupload.controller;

import com.example.fileupload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLDecoder;

@Controller
public class UploadController {
    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    @PostMapping("/upload1")
    public String upload1(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){
        if (uploadService.store(file))
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded ! - " + file.getOriginalFilename());
        else
            redirectAttributes.addFlashAttribute("error", "File could not be uploaded! - " + file.getOriginalFilename());
        return "redirect:/";
    }

    @PostMapping("/upload2")
    public String upload2(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){
        if (!file.getContentType().equals("image/png")){
            redirectAttributes.addFlashAttribute("error", "Only png is allowed !");
            return "redirect:/";
        }
        if (uploadService.store(file))
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded ! - " + file.getOriginalFilename());
        else
            redirectAttributes.addFlashAttribute("error", "File could not be uploaded! - " + file.getOriginalFilename());
        return "redirect:/";
    }
    @PostMapping("/upload3")
    public String upload3(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){
        String extension = file.getOriginalFilename().split("\\.")[1];
        if(extension.equals("jsp")){
            redirectAttributes.addFlashAttribute("error", "JSP file is not allowed !");
            return "redirect:/";
        }
        if (uploadService.store(file))
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded ! - " + file.getOriginalFilename());
        else
            redirectAttributes.addFlashAttribute("error", "File could not be uploaded! - " + file.getOriginalFilename());
        return "redirect:/";
    }
    @PostMapping("/upload4")
    public String upload4(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if(extension.equals("jsp")){
            redirectAttributes.addFlashAttribute("error", "JSP file is not allowed !");
            return "redirect:/";
        }
        if (uploadService.store(file))
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded ! - " + file.getOriginalFilename());
        else
            redirectAttributes.addFlashAttribute("error", "File could not be uploaded! - " + file.getOriginalFilename());
        return "redirect:/";
    }
    @PostMapping("/upload5")
    public String upload5(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){
        String filename = URLDecoder.decode(file.getOriginalFilename());
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        if(extension.equals("jsp")){
            redirectAttributes.addFlashAttribute("error", "JSP file is not allowed !");
            return "redirect:/";
        }
        if (uploadService.store(file))
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded ! - " + file.getOriginalFilename());
        else
            redirectAttributes.addFlashAttribute("error", "File could not be uploaded! - " + file.getOriginalFilename());
        return "redirect:/";
    }

}
