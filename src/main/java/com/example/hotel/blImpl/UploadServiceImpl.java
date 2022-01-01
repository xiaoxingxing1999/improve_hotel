package com.example.hotel.blImpl;

import com.example.hotel.bl.UploadService;
import com.example.hotel.util.OSSClientUtil;
import com.example.hotel.util.Response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author stormbroken
 * Create by 2020/06/11
 * @Version 1.0
 **/

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    OSSClientUtil ossClientUtil;

    @Override
    public SimpleResponse upload(@RequestBody MultipartFile multipartFile){
        if(multipartFile == null){
            return SimpleResponse.error("上传文件问题");
        }
        if(multipartFile.getSize() <= 0){
            return SimpleResponse.error("文件大小有误");
        }
        if(multipartFile.getSize() > 10 * 1024 * 1024){
            return SimpleResponse.error("文件大小不能大于10M");
        }
        String url;
        try{
            url = ossClientUtil.uploadFile(multipartFile);
        }catch (Exception e){
            e.printStackTrace();
            return SimpleResponse.exception("上传失败");
        }
        return SimpleResponse.ok(url);
    }

    @Override
    public SimpleResponse delete(String url){
        try{
            ossClientUtil.delete(url);
        }catch (Exception e){
            e.printStackTrace();
            return SimpleResponse.error("删除失败");
        }

        return SimpleResponse.ok("删除成功");
    }
}
