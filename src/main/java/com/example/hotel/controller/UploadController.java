package com.example.hotel.controller;

import com.example.hotel.bl.UploadService;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.Response.SimpleResponse;
import com.example.hotel.util.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author stormbroken
 * Create by 2020/06/11
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/oss")
public class UploadController {

    @Autowired
    UploadService uploadService;

    /**
     * 上传文件
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public SimpleResponse upload(@RequestParam String token,
                                 @RequestBody MultipartFile multipartFile) throws ServiceException{
        System.out.println("Post./oss/upload?token=" + token);
        UserHelper.getByToken(token);
        return uploadService.upload(multipartFile);
    }

    /**
     * 删除文件
     * @param url url为全部文件名（无前缀）xxx.jpg
     * @return
     */
    @PostMapping("/delete")
    public SimpleResponse delete(@RequestParam String url){
        System.out.println("GET./oss/delete?url=" + url);
        //如何保证他人误删
        return uploadService.delete(url);
    }
}

