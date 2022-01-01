package com.example.hotel.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author stormbroken
 * Create by 2020/06/11
 * @Version 1.0
 **/

@Component
public class OSSClientUtil {
    /**
    阿里云OSS地址，这里看根据你的oss选择
     **/
    protected static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    /**
     * 阿里云OSS账号
     */
    protected static String accessKeyId = "LTAI4FzdS9Y19Ls9Ro82QGTZ";
    /**
     * 阿里云OSS密钥
     */
    protected static String accessKeySecret = "lTzk5UXCCHyjghU2AxFKbd2syFOFQi";
    /**
     * 阿里云OSS上的存储块bucket名字
     */
    protected static String bucketName = "software3";

    private static String filename = "";
    //不进行序列化
    private volatile static OSS ossClient = null;

    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception {
        //读入对应密码
        initOSS();
        String[] fileWords = file.getContentType().split("/");
        String time = TimeHelper.dateToString(LocalDateTime.now());
        String filename = time + "/" + System.currentTimeMillis()
                + "-" + UUID.randomUUID().toString().replace("-","").substring(0, 18)
                + "." + fileWords[fileWords.length - 1];
        System.out.println(filename);
        try {
            ossClient.putObject(new PutObjectRequest(bucketName, filename , file.getInputStream()));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            throw oe;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            throw ce;
        }
        return "https://" + bucketName + "." + endpoint.split("//")[1] + "/" + filename;
    }

    /**
     * 删除文件
     * @param url
     * @return
     * @throws Exception
     */
    public boolean delete(String url) throws Exception{
        //读入对应密码
        initOSS();
        try {
            ossClient.deleteObject(bucketName,url);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            throw oe;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            throw ce;
        }
        return true;
    }

    /**
     * 单件OSSClient
     * @return
     */
    private static OSS initOSS(){
        if(ossClient == null){
            synchronized (OSSClientUtil.class){
                if(ossClient == null){
                    ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }
}
