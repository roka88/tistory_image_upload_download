package com.example.imageupload.ImageDownload;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/images")
public class ImageDownloadController {

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping(value = "{filename}")
    public void downloadImage(
            @PathVariable("filename") String filename,
            HttpServletResponse response) {

        try (InputStream inputStream = new FileInputStream(new File(String.format("%s%s", "src/main/resources/", filename)))){
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            response.getOutputStream().write(buffer);
        } catch (IOException ex) {
            response.setStatus(404);
        }
    }
}
