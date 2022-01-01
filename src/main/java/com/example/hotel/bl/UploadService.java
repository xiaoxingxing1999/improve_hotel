package com.example.hotel.bl;

import com.example.hotel.util.Response.SimpleResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author stormbroken
 * Create by 2020/06/11
 * @Version 1.0
 **/

public interface UploadService {
    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    SimpleResponse upload(MultipartFile multipartFile);

    /**
     * 删除图片
     * @param url
     * @return
     */
    SimpleResponse delete(String url);
}
