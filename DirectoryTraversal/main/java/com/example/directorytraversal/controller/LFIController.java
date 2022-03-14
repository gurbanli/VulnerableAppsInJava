package com.example.directorytraversal.controller;

import com.example.directorytraversal.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class LFIController {

    private FileService fileService;

    public LFIController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping(value = "/lfi1")
    public @ResponseBody byte[] lfi1(@RequestParam(defaultValue = "1") String logName) throws IOException {
        InputStream in = fileService.readFile(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/logs/" + logName);
        if(in != null){
            return IOUtils.toByteArray(in);
        }
        return "Log File Not Found".getBytes();
    }

    @GetMapping(value = "/lfi2")
    public @ResponseBody byte[] lfi2(@RequestParam(defaultValue = "1") String logName) throws IOException {
        if(logName.contains("../")) logName = logName.replace("../","");
        InputStream in = fileService.readFile(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/logs/" + logName);
        if(in != null){
            return IOUtils.toByteArray(in);
        }
        return "Log File Not Found".getBytes();
    }

    @GetMapping(value = "/lfi3")
    public @ResponseBody byte[] lfi3(@RequestParam(defaultValue = "1") String logName) throws IOException {
        while(logName.contains("../")) logName = logName.replace("../","");
        InputStream in = fileService.readFile(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/logs/" + logName);
        if(in != null){
            return IOUtils.toByteArray(in);
        }
        return "Log File Not Found".getBytes();
    }

    @GetMapping(value = "/lfi4")
    public @ResponseBody byte[] lfi4(@RequestParam(defaultValue = "1") String logName) throws IOException {
        if(!logName.contains(System.getProperty("user.dir"))) return "Hacking Attempt Detected !".getBytes();
        InputStream in = fileService.readFile(logName);
        if(in != null){
            return IOUtils.toByteArray(in);
        }
        return "Log File Not Found".getBytes();
    }

}
