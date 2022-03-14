package com.example.directorytraversal.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Service
public class CommandService {
    public String executeCommand(String host){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "ping -c 1 "+ host);
        processBuilder.redirectErrorStream(true);
        try{
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            String line;

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));


            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            return output.toString();
        }catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
