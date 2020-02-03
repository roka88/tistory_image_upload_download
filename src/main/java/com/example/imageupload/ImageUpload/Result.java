package com.example.imageupload.ImageUpload;


import lombok.Getter;

@Getter
public class Result {
    private boolean isSuccess;
    private String message;

    private Result() {
    }

    public static Result createResult(boolean isSuccess, String message) {
        Result result = new Result();
        result.isSuccess = isSuccess;
        result.message = message;
        return result;
    }
}
