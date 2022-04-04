package com.example.websecexam2.controller;

import com.example.websecexam2.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {
    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }
    @GetMapping("/upload")
    public String uploadPage(){
        return "upload";
    }
    @PostMapping("/upload")
    public String upload(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){
        if (uploadService.store(file))
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded ! - /uploads/" + file.getOriginalFilename());
        else
            redirectAttributes.addFlashAttribute("error", "File could not be uploaded! - " + file.getOriginalFilename());
        return "redirect:/upload";
    }
}
