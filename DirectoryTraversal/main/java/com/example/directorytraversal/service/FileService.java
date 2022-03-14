package com.example.directorytraversal.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class FileService {

    public InputStream readFile(String path){
        try{
            File file = new File(path);
            InputStream in = new FileInputStream(file);
            return in;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
