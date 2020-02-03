package com.example.imageupload.ImageUpload;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/image")
public class ImageUploadController {

    private ImageUploadService imageUploadService;

    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping
    public Result uploadImage(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse httpServletResponse) {
        Result result = imageUploadService.uploadImage(multipartHttpServletRequest);
        if (!result.isSuccess()) {
            httpServletResponse.setStatus(400);
        }
        return result;
    }
}
