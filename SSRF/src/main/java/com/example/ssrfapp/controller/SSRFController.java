package com.example.ssrfapp.controller;

import com.example.ssrfapp.model.Product;
import com.example.ssrfapp.service.SSRFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/ssrf")
public class SSRFController {

    private SSRFService ssrfService;

    public SSRFController(SSRFService ssrfService){
        this.ssrfService = ssrfService;
    }

    @PostMapping("/1")
    public ResponseEntity<String> ssrf1(@RequestParam String productApi, @CookieValue("SESSION") String sessionCookie) throws IOException {
        URL url = new URL(productApi);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Cookie", sessionCookie);
        String response = ssrfService.sendRequest(httpURLConnection);
        httpURLConnection.disconnect();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // private ip addresses which are not directly reachable by users

    @PostMapping("/2")
    public ResponseEntity<String> ssrf2(@RequestParam String productApi, @CookieValue("SESSION") String sessionCookie) throws IOException {
        if (productApi.contains("127.0.0.1")){
            return new ResponseEntity<>("forbidden", HttpStatus.FORBIDDEN);
        }
        URL url = new URL(productApi);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Cookie", sessionCookie);
        String response = ssrfService.sendRequest(httpURLConnection);
        httpURLConnection.disconnect();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //localhost
    // alternative ip representation
    // spoofed.burpcollaborator.net
    @PostMapping("/3")
    public ResponseEntity<String> ssrf3(@RequestParam String productApi, @CookieValue("SESSION") String sessionCookie) throws IOException {
        if (!productApi.contains("google.com")){
            return new ResponseEntity<>("forbidden", HttpStatus.FORBIDDEN);
        }
        URL url = new URL(productApi);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Cookie", sessionCookie);
        String response = ssrfService.sendRequest(httpURLConnection);
        httpURLConnection.disconnect();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // new domain
    // @
    // #
    // sub domain
    // encoding
    @PostMapping("/4")
    public ResponseEntity<String> ssrf4(@RequestParam String productApi, @CookieValue("SESSION") String sessionCookie) throws IOException {
        String api = "http://google.com" + productApi;
        URL url = new URL(api);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Cookie", sessionCookie);
        String response = ssrfService.sendRequest(httpURLConnection);
        httpURLConnection.disconnect();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // partial url bypass with @

    // http response with client-side vulnerabilities
    // out-of-band traffic
    // open redirection vulnerability
}
