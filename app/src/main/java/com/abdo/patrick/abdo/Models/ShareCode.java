package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ShareCode {

    private String Code;
    private String CreatedTime;
    private String ModifiedTime;
    private Long GeneratedTime;


    public String getCode() {
        return Code == null ? "" : Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public String getModifiedTime() {
        return ModifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        ModifiedTime = modifiedTime;
    }

    public Long getGeneratedTime() {
        return GeneratedTime == null ? Long.MIN_VALUE : GeneratedTime;
    }

    public void setGeneratedTime(Long generatedTime) {
        GeneratedTime = generatedTime;
    }
}
