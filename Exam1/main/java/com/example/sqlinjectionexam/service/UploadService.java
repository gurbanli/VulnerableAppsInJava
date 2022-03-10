package com.example.sqlinjectionexam.service;

import com.example.sqlinjectionexam.repository.SettingRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class UploadService {

    private SettingRepository settingRepository;

    public UploadService(SettingRepository settingRepository){
        this.settingRepository = settingRepository;
    }

    public boolean store(MultipartFile file) {
        OutputStream outFileStream = null;
        InputStream inputFileStream = null;
        try{
            File fileInDestination = new File("/home/gurbanli/IdeaProjects/sqlinjectionexam/src/main/webapp/WEB-INF/" + settingRepository.findSettingBySettingKey("uploadPath") .getSettingValue() + file.getOriginalFilename());
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
