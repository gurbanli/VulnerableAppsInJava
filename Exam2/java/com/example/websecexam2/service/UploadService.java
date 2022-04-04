package com.example.websecexam2.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;

@Service
public class UploadService {
    private final String UPLOAD_DIR = "/Users/gurbanli/IdeaProjects/WebSecExam2/src/main/resources/static/";
    public boolean store(MultipartFile file) {
        String filename = URLDecoder.decode(file.getOriginalFilename());
        OutputStream outFileStream = null;
        InputStream inputFileStream = null;
        try{
            File fileInDestination = new File(UPLOAD_DIR + filename);
            outFileStream = new FileOutputStream(fileInDestination);
            inputFileStream = file.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];
            while((read = inputFileStream.read(bytes)) != -1){
                outFileStream.write(bytes,0,read);
            }
        }catch (IOException e){
            return false;
        } finally {
            IOUtils.closeQuietly(outFileStream);
            IOUtils.closeQuietly(inputFileStream);
        }
        return true;
    }
}
