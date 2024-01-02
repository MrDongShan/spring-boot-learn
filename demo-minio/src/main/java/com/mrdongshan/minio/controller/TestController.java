package com.mrdongshan.minio.controller;

import com.mrdongshan.minio.util.MinioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final MinioUtils minioUtils;

    // 是否存在桶，不存在就创建
    @GetMapping("exist/bucket")
    public void existBucket() {
        boolean flag = minioUtils.existBucket("test-bucket", false);
        System.err.println("是否存在桶：" + flag);
    }

    // 创建桶
    @GetMapping("make/bucket")
    public void makeBucket() {
        minioUtils.makeBucket("test-bucket");
    }

    // 删除桶
    @GetMapping("remove/bucket")
    public void removeBucket() {
        Boolean b = minioUtils.removeBucket("test-bucket");
    }

    // 上传文件
    @PostMapping("upload")
    public void upload(@RequestBody MultipartFile[] multipartFile) {
        List<String> rest = minioUtils.upload(multipartFile);
        rest.forEach(System.err::println);
    }

    // 下载文件
    @GetMapping("download")
    public ResponseEntity<byte[]> download(String fileName) {
        return minioUtils.download(fileName);
    }

    // 查看文件对象
    @GetMapping("listObjects")
    public void listObjects(String fileName) {
        minioUtils.listObjects(fileName);
    }

    // 批量删除文件
    @GetMapping("removeObjects")
    public void removeObjects(String bucketName, List<String> fileNameList) {
        minioUtils.removeObjects(bucketName, fileNameList);
    }
}
