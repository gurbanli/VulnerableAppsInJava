package com.example.sqlinjectionexam.controller;

import com.example.sqlinjectionexam.repository.SettingRepository;
import com.example.sqlinjectionexam.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/file")
public class UploadController {

    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService, SettingRepository settingRepository){
        this.uploadService = uploadService;
    }

    @PostMapping
    public String uploadFile(RedirectAttributes redirectAttributes, @RequestParam("file")MultipartFile file){
        if (uploadService.store(file))
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded ! - " + file.getOriginalFilename());
        else
            redirectAttributes.addFlashAttribute("error", "File could not be uploaded! - " + file.getOriginalFilename());
        return "redirect:/products";
    }

}
