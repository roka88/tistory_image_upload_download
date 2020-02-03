package com.example.imageupload.ImageUpload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.Iterator;

@Service
public class ImageUploadService {

    public Result uploadImage(MultipartHttpServletRequest multipartHttpServletRequest) {

        /**
         * 업로드 된 이미지가 한 장일 경우만 고려한다.
         */

        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();

        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(fileName);

            // TODO : 바이너리를 통한 Mime-Type 체크는 생략
            String mimeType = multipartFile.getOriginalFilename().split("\\.")[1];

            String newFileName = String.format("%d%d.%s", System.currentTimeMillis(), System.nanoTime(), mimeType);

            try (InputStream initialStream = new BufferedInputStream(multipartFile.getInputStream())){

                byte[] buffer = new byte[initialStream.available()];
                initialStream.read(buffer);

                // TODO : 로컬에 저장해보자.
                String path = String.format("%s%s", "src/main/resources/", newFileName);

                File targetFile = new File(path);
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);

                outStream.close();

                return Result.createResult(true, newFileName);

            } catch (IOException e) {
                System.out.println(e);
                return Result.createResult(false, "이미지 업로드에 실패했어요.");
            }
        }
        return Result.createResult(false, "업로드된 이미지가 존재하지 않습니다.");
    }
}
